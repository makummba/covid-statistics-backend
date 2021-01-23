package com.kasprusz.covidstatisticsbackend.services;


import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatistics;
import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatisticsDTO;
import com.kasprusz.covidstatisticsbackend.repository.VoivodeshipStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class VoivodeshipStatisticService {

    private final VoivodeshipStatisticsRepository voivodeshipStatisticsRepository;

    public VoivodeshipStatisticService(VoivodeshipStatisticsRepository voivodeshipStatisticsRepository) {
        this.voivodeshipStatisticsRepository = voivodeshipStatisticsRepository;
    }

    public List<VoivodeshipStatistics> findAll() {
        return this.voivodeshipStatisticsRepository.findAll();
    }



    private VoivodeshipStatistics sumValues(VoivodeshipStatistics voivodeshipStatisticsa, VoivodeshipStatistics voivodeshipStatisticsb) {
        return new VoivodeshipStatistics(
                voivodeshipStatisticsa.getId(),
                voivodeshipStatisticsa.getCity(),
                voivodeshipStatisticsa.getVoivodeship(),
                voivodeshipStatisticsa.getNumberOfInfection() + voivodeshipStatisticsb.getNumberOfInfection(),
                Math.round(((voivodeshipStatisticsa.getNumberFor10Citizens() + voivodeshipStatisticsb.getNumberFor10Citizens())/2)*100.0)/ 100.0,
                voivodeshipStatisticsa.getNumberOfDeaths() + voivodeshipStatisticsb.getNumberOfDeaths(),
                voivodeshipStatisticsa.getNumberOfDeathsWithOtherSick() + voivodeshipStatisticsb.getNumberOfDeathsWithOtherSick(),
                voivodeshipStatisticsa.getNumberOfDeathsWithoutOtherSick() + voivodeshipStatisticsb.getNumberOfDeathsWithoutOtherSick()

        );
    }

    public List<VoivodeshipStatisticsDTO> findGroupedByVoivodeship() {
        return this.findAll()
                .stream()
                .collect(Collectors.toMap(VoivodeshipStatistics::getVoivodeship,
                        Function.identity(),
                        this::sumValues))
                .entrySet()
                .stream()
                .map(e -> new VoivodeshipStatisticsDTO(e.getKey(),
                        e.getValue().getNumberOfInfection(),
                        e.getValue().getNumberFor10Citizens(),
                        e.getValue().getNumberOfDeaths(),
                        e.getValue().getNumberOfDeathsWithOtherSick(),
                        e.getValue().getNumberOfDeathsWithoutOtherSick()))
                .sorted(Comparator.comparing(VoivodeshipStatisticsDTO::getVoivodeship).reversed())
                .collect(Collectors.toList());
    }

}
