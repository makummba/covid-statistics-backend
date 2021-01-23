package com.kasprusz.covidstatisticsbackend.mapper;

import com.kasprusz.covidstatisticsbackend.model.HistoricalStatistics;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


public class HistoricalMapper implements FieldSetMapper {

    @Override
    public Object mapFieldSet(FieldSet fieldSet) {


        // POPRAWIC DANE JESLI MAJA PRZERWE W STRINGU
        HistoricalStatistics historicalStats = new HistoricalStatistics();
        historicalStats.setMeasureDay(fieldSet.readDate(1, "dd.MM.yyyy"));
        historicalStats.setNewCases(NumberUtils.toInt(fieldSet.readString(2), 0));
        historicalStats.setAllCases(NumberUtils.toInt(fieldSet.readString(3), 0));
        historicalStats.setDeaths(NumberUtils.toInt(fieldSet.readString(4), 0));
        historicalStats.setAllCumulative(NumberUtils.toInt(fieldSet.readString(5), 0));
        historicalStats.setRecovered(NumberUtils.toInt(fieldSet.readString(6), 0));
        historicalStats.setAllRecovered(NumberUtils.toInt(fieldSet.readString(7), 0));
        historicalStats.setActiveCases(NumberUtils.toInt(fieldSet.readString(8), 0));
        historicalStats.setHospitalCases(NumberUtils.toInt(fieldSet.readString(9), 0));
        historicalStats.setQuarantineCases(NumberUtils.toInt(fieldSet.readString(10), 0));
        historicalStats.setMonitoringCases(NumberUtils.toInt(fieldSet.readString(11), 0));
        historicalStats.setOccupiedBeds(NumberUtils.toInt(fieldSet.readString(12), 0));
        historicalStats.setRespirators(NumberUtils.toInt(fieldSet.readString(13), 0));

        return historicalStats;
    }
}