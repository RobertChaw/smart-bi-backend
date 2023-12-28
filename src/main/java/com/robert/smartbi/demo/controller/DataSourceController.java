package com.robert.smartbi.demo.controller;

import com.robert.smartbi.demo.model.entity.DataSource;
import com.robert.smartbi.demo.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dataSources")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @GetMapping("/{id}")
    public DataSource getDataSourceById(@PathVariable Long id) {
        return dataSourceService.getById(id);
    }

    @GetMapping
    public List<DataSource> getAllDataSources() {
        return dataSourceService.list();
    }

    @PostMapping
    public void createDataSource(@RequestBody DataSource dataSource) {
        dataSourceService.save(dataSource);
    }

    @PutMapping("/{id}")
    public void updateDataSource(@PathVariable Long id, @RequestBody DataSource dataSource) {
        dataSource.setId(id);
        dataSourceService.updateById(dataSource);
    }

    @DeleteMapping("/{id}")
    public void deleteDataSourceById(@PathVariable Long id) {
        dataSourceService.removeById(id);
    }
}
