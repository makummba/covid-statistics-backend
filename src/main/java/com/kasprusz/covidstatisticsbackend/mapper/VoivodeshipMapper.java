package com.kasprusz.covidstatisticsbackend.mapper;

import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatistics;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class VoivodeshipMapper implements FieldSetMapper {

    @Override
    public Object mapFieldSet(FieldSet fieldSet) {

        VoivodeshipStatistics voivodeship = new VoivodeshipStatistics();
        voivodeship.setVoivodeship(fieldSet.readString(0));
        voivodeship.setCity(fieldSet.readString(1));
        voivodeship.setNumberOfInfection(fieldSet.readInt(2));
        // maybe to change this later
        voivodeship.setNumberFor10Citizens(Double.parseDouble(fieldSet.readString(3).replace(",", ".")));
        voivodeship.setNumberOfDeaths(fieldSet.readInt(4));
        voivodeship.setNumberOfDeathsWithOtherSick(fieldSet.readInt(5));
        voivodeship.setNumberOfDeathsWithoutOtherSick(fieldSet.readInt(6));

        return voivodeship;
    }
}