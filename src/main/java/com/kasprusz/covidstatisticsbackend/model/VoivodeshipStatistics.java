package com.kasprusz.covidstatisticsbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="VOIVODESHIP_STATISTICS")
public class VoivodeshipStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String voivodeship;
    String city;
    Integer numberOfInfection;
    Double numberFor10Citizens;
    Integer numberOfDeaths;
    Integer numberOfDeathsWithOtherSick;
    Integer numberOfDeathsWithoutOtherSick;
}
