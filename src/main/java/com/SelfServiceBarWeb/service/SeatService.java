package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.HardwareLogMapper;
import com.SelfServiceBarWeb.mapper.HardwareStateMapper;
import com.SelfServiceBarWeb.mapper.SeatMapper;
import com.SelfServiceBarWeb.mapper.TableMapper;
import com.SelfServiceBarWeb.model.*;

import com.SelfServiceBarWeb.model.request.ChangeSeatRequest;
import com.SelfServiceBarWeb.model.request.CreateSeatRequest;
import com.SelfServiceBarWeb.model.request.SeatStateEnum;
import com.SelfServiceBarWeb.model.request.TokenTypeEnum;
import com.SelfServiceBarWeb.server.SocketServer;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Muki on 2018/11/4
 */

@Service
public class SeatService {
    private final SeatMapper seatMapper;
    private final TableMapper tableMapper;
    private final AdministratorService administratorService;
    private final HardwareStateMapper hardwareStateMapper;
    private final HardwareLogMapper hardwareLogMapper;
    private final SocketServer socketServer;

    @Autowired
    public SeatService(SeatMapper seatMapper, TableMapper tableMapper, AdministratorService administratorService, HardwareStateMapper hardwareStateMapper, HardwareLogMapper hardwareLogMapper, SocketServer socketServer) {
        this.seatMapper = seatMapper;
        this.tableMapper = tableMapper;
        this.administratorService = administratorService;
        this.hardwareStateMapper = hardwareStateMapper;
        this.hardwareLogMapper = hardwareLogMapper;
        this.socketServer = socketServer;
    }

    public List<Seat> getAllSeats(String token) throws Exception {
        Administrator administrator = administratorService.getAdministratorIdFromToken(token);

        List<Seat> seats = seatMapper.getAllSeats(administrator.getBar_id());
        for (Seat seat : seats) {
            Hardware seatState = hardwareStateMapper.getByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());
            seat.setState(HardwareStateEnum.getHardwareStateEnum(seatState.getState()));
            seat.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue()));
        }
        return seats;
    }

    public List<Seat> getByTableId(String tableId) throws Exception {
        List<Seat> seats = seatMapper.getByTableId(tableId);
        for (Seat seat : seats) {
            Hardware seatState = hardwareStateMapper.getByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());
            seat.setState(HardwareStateEnum.getHardwareStateEnum(seatState.getState()));
            seat.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue()));
        }
        return seats;
    }

    public Seat getBySeatId(String seatId, String token, TokenTypeEnum tokenTypeEnum) throws Exception {
        Seat seat = seatMapper.getBySeatId(seatId);
        switch (tokenTypeEnum) {
            case administrator: {
                administratorService.getAdministratorIdFromToken(token);
                break;
            }
            case user: {
                DecodedJWT jwt = CommonUtil.phraseJWT(token, "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
                if (!Objects.equals(barId, seat.getBar_id()))
                    throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);
                break;
            }
            case pad: {
                DecodedJWT jwt = CommonUtil.phraseJWT(token, "padControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String id = JSONObject.parseObject(jwt.getSubject()).getString("seatId");
                if (seatId != id)
                    throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.LIGHT_NOT_NOT_FOUND);
                break;
            }
        }
        if (seat == null)
            throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.LIGHT_NOT_NOT_FOUND);

        //根据ip地址+座位位置定位座位硬件信息
        Hardware seatState = hardwareStateMapper.getByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());
        seat.setState(HardwareStateEnum.getHardwareStateEnum(seatState.getState()));
        seat.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue()));

        return seat;
    }

    public Seat createNewSeat(CreateSeatRequest createSeatRequest) throws Exception {
        Administrator administrator = administratorService.getAdministratorIdFromToken(createSeatRequest.getLoginToken());

        Table table = tableMapper.getTableById(createSeatRequest.getTable_id());
        if (table == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.CREATE_SEAT_ERROR);

        //TODO：检查座位位置情况

        Seat seat = new Seat();
        seat.setTable_id(createSeatRequest.getTable_id());
        seat.setPosition_x(createSeatRequest.getPosition_x());
        seat.setPosition_y(createSeatRequest.getPosition_y());
        seat.setLocation(createSeatRequest.getLocation());
        seat.setProducer(createSeatRequest.getProducer());
        seat.setCreate_at(createSeatRequest.getCreate_at());
        seat.setUse_at(createSeatRequest.getUse_at());
        seat.setBar_id(administrator.getBar_id());

        //自动生成IP地址, 硬件编号
        List<Seat> seats = seatMapper.getAllSeats(administrator.getBar_id());
        long maxIp = CommonUtil.ipToLong("192.168.0.0");
        long maxHardwareID = 0;
        for (Seat tempseat : seats) {
            long temp = CommonUtil.ipToLong(tempseat.getIpAddress());
            if (temp > maxIp)
                maxIp = temp;

            long tempID = Integer.parseInt(tempseat.getHardwareId());
            if (tempID > maxHardwareID)
                maxHardwareID = tempID;

        }
        seat.setIpAddress(CommonUtil.longToIP(maxIp + 1));
        seat.setHardwareId(String.valueOf(maxHardwareID));

        seatMapper.createNewSeat(seat);
        Hardware hardware = new Hardware(seat.getId(), HardwareTypeEnum.seat.getValue());
        hardwareStateMapper.createNewHardwareState(hardware);
        seat.setState(HardwareStateEnum.close);

        //加入日志
        HardwareLog hardwareLog = new HardwareLog(seat.getId(), HardwareTypeEnum.seat.getValue(), ResponseMessage.ADMINISTER, HardwareStateEnum.create.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        seat.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue()));

        return seat;
    }

    public Seat changeSeatState(String seatId, ChangeSeatRequest changeSeatRequest) throws Exception {
        Seat seat = seatMapper.getBySeatId(seatId);
        HardwareLog hardwareLog;
        String identity = "";
        switch (changeSeatRequest.getTokenTypeEnum()) {
            case administrator: {
                administratorService.getAdministratorIdFromToken(changeSeatRequest.getToken());
                identity = ResponseMessage.ADMINISTER;
                break;
            }
            case user: {
                DecodedJWT jwt = CommonUtil.phraseJWT(changeSeatRequest.getToken(), "userControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String barId = JSONObject.parseObject(jwt.getSubject()).getString("barId");
                if (!Objects.equals(barId, seat.getBar_id()))
                    throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.INVALID_CONTROL_TOKEN);
                identity = "user:" + JSONObject.parseObject(jwt.getSubject()).getString("uid");
                break;
            }
            case pad: {
                DecodedJWT jwt = CommonUtil.phraseJWT(changeSeatRequest.getToken(), "padControlToken", ResponseMessage.INVALID_CONTROL_TOKEN);
                String id = JSONObject.parseObject(jwt.getSubject()).getString("seatId");
                if (seatId != id)
                    throw new SelfServiceBarWebException(404, ResponseMessage.ERROR, ResponseMessage.LIGHT_NOT_NOT_FOUND);
                identity = "pad:" + JSONObject.parseObject(jwt.getSubject()).getString("padId");
                break;
            }
        }

        if (seat == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_SEAT_INFO_ERROR);

        //Integer hardwareAvailability = hardwareStateMapper.getAvailabilityByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());

        //修改seat状态
        switch (changeSeatRequest.getMode()) {
            case open:
                //模拟数据库操作
                hardwareStateMapper.openByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());
                //硬件控制
                socketServer.openById(Integer.parseInt(seat.getHardwareId()));
                //加入日志
                hardwareLog = new HardwareLog(seat.getId(), HardwareTypeEnum.seat.getValue(), identity, HardwareStateEnum.open.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
            case close:
                hardwareStateMapper.closeByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());
                //硬件控制
                socketServer.closeById(Integer.parseInt(seat.getHardwareId()));
                //加入日志
                hardwareLog = new HardwareLog(seat.getId(), HardwareTypeEnum.seat.getValue(), identity, HardwareStateEnum.close.getValue(), "");
                hardwareLogMapper.createNewLog(hardwareLog);
                break;
        }
        seat.setHardwareLogs(hardwareLogMapper.getRecentByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue()));

        return seat;
    }

    public boolean changeAllSeatState(String token, SeatStateEnum modeEnum) throws Exception {
        //验证管理员或用户的身份
        Administrator administrator = administratorService.getAdministratorIdFromToken(token);
        List<Seat> seats = seatMapper.getAllSeats(administrator.getBar_id());

        for (Seat seat : seats) {
            //更改灯的状态
            switch (modeEnum) {
                case close: {
                    socketServer.closeById(Integer.valueOf(seat.getHardwareId()));
                    hardwareStateMapper.closeByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());
                    break;
                }
                case open: {
                    socketServer.openById(Integer.valueOf(seat.getHardwareId()));
                    hardwareStateMapper.openByIdAndType(seat.getId(), HardwareTypeEnum.seat.getValue());
                    break;
                }
            }
        }
        HardwareLog hardwareLog;
        if (modeEnum == SeatStateEnum.close)
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.seat.getValue(), "admin", HardwareStateEnum.close.getValue(), "");
        else
            hardwareLog = new HardwareLog("99999", HardwareTypeEnum.seat.getValue(), "admin", HardwareStateEnum.open.getValue(), "");
        hardwareLogMapper.createNewLog(hardwareLog);

        return true;
    }
}
