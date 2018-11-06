package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.mapper.SeatMapper;
import com.SelfServiceBarWeb.mapper.TableMapper;
import com.SelfServiceBarWeb.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    private final TableMapper tableMapper;
    private final SeatMapper seatMapper;

    @Autowired
    public TableService(TableMapper tableMapper, SeatMapper seatMapper) {
        this.tableMapper = tableMapper;
        this.seatMapper = seatMapper;
    }

    public List<Table> getAllTable() throws Exception {
        List<Table> tables = tableMapper.getAll();
        for(Table table: tables) {
            table.setSeats(seatMapper.getByTableId(table.getId()));
        }
        return tables;
    }
}
