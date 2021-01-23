package com.kasprusz.covidstatisticsbackend.controller;

import java.util.List;

import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatistics;
import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatisticsDTO;
import com.kasprusz.covidstatisticsbackend.services.VoivodeshipStatisticService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/voivodeship")
public class VoivodeshipStatisticsController {

    private final VoivodeshipStatisticService service;

    public VoivodeshipStatisticsController(VoivodeshipStatisticService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public List<VoivodeshipStatistics> getVoivodeshipStatistics() {
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/grouped")
    public List<VoivodeshipStatisticsDTO> getGroupedVoivodeshipStatistics() {
        return service.findGroupedByVoivodeship();
    }
}