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
    private final AdministratorService administratorService;
    private final SeatService seatService;

    @Autowired
    public TableService(TableMapper tableMapper, AdministratorService administratorService, SeatService seatService) {
        this.tableMapper = tableMapper;
        this.administratorService = administratorService;
        this.seatService = seatService;
    }

    public List<Table> getAllTable(String token) throws Exception {
        List<Table> tables = tableMapper.getAll();
        for(Table table: tables) {
            table.setSeats(seatService.getByTableId(table.getId()));
        }
        return tables;
    }
}
