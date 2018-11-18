package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.mapper.HardwareLogMapper;
import com.SelfServiceBarWeb.mapper.SeatMapper;
import com.SelfServiceBarWeb.mapper.TableMapper;
import com.SelfServiceBarWeb.model.Table;
import com.SelfServiceBarWeb.model.request.CreateTableRequest;
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
        return tables;
    }

    public Table createNewTable(CreateTableRequest createTableRequest) {

        Table table = new Table();
        table.setLeft_up_x_coordinate(createTableRequest.getLeft_up_x_coordinate());
        table.setLeft_up_y_coordinate(createTableRequest.getLeft_up_y_coordinate());
        table.setRight_down_x_coordinate(createTableRequest.getRight_down_x_coordinate());
        table.setRight_down_y_coordinate(createTableRequest.getRight_down_y_coordinate());
        table.setLocation(createTableRequest.getLocation());

        tableMapper.createNewTable(table);
        return table;
    }
}
