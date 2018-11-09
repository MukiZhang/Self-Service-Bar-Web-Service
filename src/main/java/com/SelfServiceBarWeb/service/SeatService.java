package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.SeatMapper;
import com.SelfServiceBarWeb.model.Seat;

import com.SelfServiceBarWeb.model.SelfServiceBarWebException;
import com.SelfServiceBarWeb.model.request.ChangeSeatRequest;
import com.SelfServiceBarWeb.model.request.CreateSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Muki on 2018/11/4
 */

@Service
public class SeatService {
    private final SeatMapper seatMapper;

    @Autowired
    public SeatService(SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }

    public List<Seat> getByTableId(String tableId, String token) throws Exception {
        List<Seat> seats = seatMapper.getByTableId(tableId);
        return seats;
    }

    public Seat getBySeatId(String seatId, String token) throws Exception {
        //验证token，用户或者管理员
        Seat seat = seatMapper.getBySeatId(seatId);
        if (seat == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_SEAT_INFO_ERROR);
        return seat;
    }

    public Seat createNewSeat(CreateSeatRequest createSeatRequest) {
        //createSeatRequest.getLoginToken();

        Seat seat = new Seat();
        seat.setId(String.valueOf(seatMapper.findMaxId() + 1));
        seat.setHardwareId(createSeatRequest.getHardwareId());
        seat.setIpAddress(createSeatRequest.getIpAddress());
        seat.setTable_id(createSeatRequest.getTable_id());
        seat.setPosition_x(createSeatRequest.getPosition_x());
        seat.setPosition_y(createSeatRequest.getPosition_y());

        seatMapper.createNewSeat(seat);
        return seat;
    }

    public Seat changeSeatState(String seatId, ChangeSeatRequest changeSeatRequest) {
        Seat seat = seatMapper.getBySeatId(seatId);
        if (seat == null)
            throw new SelfServiceBarWebException(400, ResponseMessage.ERROR, ResponseMessage.GET_SEAT_INFO_ERROR);
        //修改seat状态
        return seat;
    }
}
