package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.SeatMapper;
import com.SelfServiceBarWeb.mapper.TableMapper;
import com.SelfServiceBarWeb.model.Seat;

import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.model.Table;
import com.SelfServiceBarWeb.model.request.ChangeSeatRequest;
import com.SelfServiceBarWeb.model.request.CreateSeatRequest;
import com.SelfServiceBarWeb.model.request.SeatStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Muki on 2018/11/4
 */

@Service
public class SeatService {
    private final SeatMapper seatMapper;
    private final TableMapper tableMapper;

    @Autowired
    public SeatService(SeatMapper seatMapper, TableMapper tableMapper) {
        this.seatMapper = seatMapper;
        this.tableMapper = tableMapper;
    }

    public List<Seat> getAllSeats(String token) throws Exception {
        List<Seat> seats = seatMapper.getAllSeats();
        for (Seat seat : seats) {
            seat.setState("获取中");
        }
        return seats;
    }

    public List<Seat> getByTableId(String tableId) throws Exception {
        List<Seat> seats = seatMapper.getByTableId(tableId);
        for (Seat seat : seats) {
            seat.setState("获取中");
        }
        return seats;
    }

    public Seat getBySeatId(String seatId, String token) throws Exception {
        //验证token，用户或者管理员
        Seat seat = seatMapper.getBySeatId(seatId);
        seat.setState("获取中");

        if (seat == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_SEAT_INFO_ERROR);
        return seat;
    }

    public Seat createNewSeat(CreateSeatRequest createSeatRequest) {
        //createSeatRequest.getLoginToken();

        Table table = tableMapper.getTableById(createSeatRequest.getTable_id());
        if (table == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.CREATE_SEAT_ERROR);

        Seat seat = new Seat();
        seat.setHardwareId(createSeatRequest.getHardwareId());
        seat.setIpAddress(createSeatRequest.getIpAddress());
        seat.setTable_id(createSeatRequest.getTable_id());
        seat.setPosition_x(createSeatRequest.getPosition_x());
        seat.setPosition_y(createSeatRequest.getPosition_y());
        seat.setState("获取中");
        seat.setLocation(createSeatRequest.getLocation());

        seatMapper.createNewSeat(seat);
        return seat;
    }

    public Seat changeSeatState(String seatId, ChangeSeatRequest changeSeatRequest) {
        Seat seat = seatMapper.getBySeatId(seatId);
        if (seat == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_SEAT_INFO_ERROR);
        //修改seat状态
        seat.setState(changeSeatRequest.getMode().getValue() + "");
        return seat;
    }
}
