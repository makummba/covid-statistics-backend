package com.kasprusz.covidstatisticsbackend.utils;

import java.util.Date;

import com.kasprusz.covidstatisticsbackend.model.HistoricalStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class HistoricalProcessor implements ItemProcessor<HistoricalStatistics, HistoricalStatistics> {


    @Override
    public HistoricalStatistics process(final HistoricalStatistics historicalStatistics) {

        final Integer id = historicalStatistics.getId();
        final Date date = historicalStatistics.getMeasureDay();
        final Integer newCases = historicalStatistics.getNewCases();
        final Integer allCases = historicalStatistics.getAllCases();
        final Integer deaths = historicalStatistics.getDeaths();
        final Integer allCumulative = historicalStatistics.getAllCumulative();
        final Integer recovered = historicalStatistics.getRecovered();
        final Integer allRecovered = historicalStatistics.getAllRecovered();
        final Integer activeCases = historicalStatistics.getActiveCases();
        final Integer hospitalCases = historicalStatistics.getHospitalCases();
        final Integer quarantineCases = historicalStatistics.getQuarantineCases();
        final Integer monitoringCases = historicalStatistics.getMonitoringCases();
        final Integer occupiedBeds = historicalStatistics.getOccupiedBeds();
        final Integer respirators = historicalStatistics.getRespirators();

        return new HistoricalStatistics(id, date, newCases, allCases, deaths, allCumulative, recovered, allRecovered, activeCases, hospitalCases, quarantineCases, monitoringCases, occupiedBeds, respirators);
    }

}