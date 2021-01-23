package com.kasprusz.covidstatisticsbackend.repository;

import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoivodeshipStatisticsRepository extends JpaRepository<VoivodeshipStatistics, Integer> {

}