package com.SelfServiceBarWeb.controller;

import com.SelfServiceBarWeb.model.Table;
import com.SelfServiceBarWeb.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/tables")
@RestController
@EnableAutoConfiguration
@Api(tags = "Table", description = "桌子相关操作")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @ApiOperation(value = "获取所有桌椅信息")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Table> getAllTable(@RequestParam(value = "token") String token) throws Exception {
        return tableService.getAllTable(token);
    }


}
