package com.kasprusz.covidstatisticsbackend.utils;

import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatistics;
import org.springframework.batch.item.ItemProcessor;

public class VoivodeshipProcessor implements ItemProcessor<VoivodeshipStatistics, VoivodeshipStatistics> {


    @Override
    public VoivodeshipStatistics process(final VoivodeshipStatistics VoivodeshipStatistics) {

        final Integer id = VoivodeshipStatistics.getId();
        final String voivodeship = VoivodeshipStatistics.getVoivodeship();
        final String city = VoivodeshipStatistics.getCity();
        final Integer numberOfInfection = VoivodeshipStatistics.getNumberOfInfection();
        final Double numberFor10Citizens = VoivodeshipStatistics.getNumberFor10Citizens();
        final Integer numberOfDeaths = VoivodeshipStatistics.getNumberOfDeaths();
        final Integer numberOfDeathsWithOtherSick = VoivodeshipStatistics.getNumberOfDeathsWithOtherSick();
        final Integer numberOfDeathsWithoutOtherSick = VoivodeshipStatistics.getNumberOfDeathsWithoutOtherSick();

        return new VoivodeshipStatistics(id, voivodeship, city, numberOfInfection, numberFor10Citizens, numberOfDeaths, numberOfDeathsWithOtherSick, numberOfDeathsWithoutOtherSick );
    }

}