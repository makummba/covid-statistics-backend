package com.kasprusz.covidstatisticsbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="HISTORICAL_STATISTICS")
public class HistoricalStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @DateTimeFormat(pattern="dd.MM.yyyy")
    Date measureDay;

    Integer newCases;
    Integer allCases;
    Integer deaths;
    Integer allCumulative;
    Integer recovered;
    Integer allRecovered;
    Integer activeCases;
    Integer hospitalCases;
    Integer quarantineCases;
    Integer monitoringCases;
    Integer occupiedBeds;
    Integer respirators;

}

