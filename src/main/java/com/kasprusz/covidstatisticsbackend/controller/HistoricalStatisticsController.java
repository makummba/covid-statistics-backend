package com.kasprusz.covidstatisticsbackend.controller;

import java.util.List;

import com.kasprusz.covidstatisticsbackend.model.HistoricalStatistics;
import com.kasprusz.covidstatisticsbackend.services.HistoricalStatisticService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/historical")
public class HistoricalStatisticsController {

    private final HistoricalStatisticService service;

    public HistoricalStatisticsController(HistoricalStatisticService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<HistoricalStatistics> getHistoricalStatistics() { return service.findAll(); }
}