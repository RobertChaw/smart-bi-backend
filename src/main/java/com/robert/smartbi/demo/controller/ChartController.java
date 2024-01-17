package com.robert.smartbi.demo.controller;

import com.robert.smartbi.demo.model.entity.Chart;
import com.robert.smartbi.demo.service.ChartService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

    @Resource
    private ChartService chartService;

    @GetMapping("/{id}")
    public Chart getChartById(@PathVariable Long id) {
        return chartService.getById(id);
    }

    @GetMapping
    public List<Chart> getAllCharts() {
        return chartService.list();
    }

    @PostMapping
    public void createChart(@RequestBody Chart chart) {
        chartService.save(chart);
    }

    @PutMapping("/{id}")
    public void updateChart(@PathVariable Long id, @RequestBody Chart chart) {
        chart.setId(id);
        chartService.updateById(chart);
    }

    @DeleteMapping("/{id}")
    public void deleteChartById(@PathVariable Long id) {
        chartService.removeById(id);
    }
}
