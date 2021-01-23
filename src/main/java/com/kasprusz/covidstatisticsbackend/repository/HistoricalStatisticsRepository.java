package com.kasprusz.covidstatisticsbackend.repository;

import com.kasprusz.covidstatisticsbackend.model.HistoricalStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricalStatisticsRepository extends JpaRepository<HistoricalStatistics, Integer> {

}