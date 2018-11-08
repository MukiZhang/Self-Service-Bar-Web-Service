package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.mapper.SeatMapper;
import com.SelfServiceBarWeb.model.Seat;

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

    public List<Seat> getByTableId(String tableId) throws Exception {
        List<Seat> seats = seatMapper.getByTableId(tableId);
        return seats;
    }
}
