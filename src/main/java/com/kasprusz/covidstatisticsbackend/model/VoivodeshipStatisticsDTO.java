package com.kasprusz.covidstatisticsbackend.model;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoivodeshipStatisticsDTO {
    String voivodeship;
    Integer numberOfInfection;

    @Column(precision = 2, scale = 2)
    Double numberFor10Citizens;
    Integer numberOfDeaths;
    Integer numberOfDeathsWithOtherSick;
    Integer numberOfDeathsWithoutOtherSick;

}
