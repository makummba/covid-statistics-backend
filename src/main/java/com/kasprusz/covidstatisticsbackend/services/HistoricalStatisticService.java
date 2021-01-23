package com.kasprusz.covidstatisticsbackend.services;

import java.util.List;
import com.kasprusz.covidstatisticsbackend.model.HistoricalStatistics;
import com.kasprusz.covidstatisticsbackend.repository.HistoricalStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoricalStatisticService {

    private final HistoricalStatisticsRepository repository;

    public HistoricalStatisticService(HistoricalStatisticsRepository repository) {
        this.repository = repository;
    }

    public List<HistoricalStatistics> findAll() {
        return this.repository.findAll();
    }

}
