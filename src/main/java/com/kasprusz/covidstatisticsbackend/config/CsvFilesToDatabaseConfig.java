package com.kasprusz.covidstatisticsbackend.config;


import com.kasprusz.covidstatisticsbackend.mapper.HistoricalMapper;
import com.kasprusz.covidstatisticsbackend.mapper.VoivodeshipMapper;
import com.kasprusz.covidstatisticsbackend.model.HistoricalStatistics;
import com.kasprusz.covidstatisticsbackend.model.VoivodeshipStatistics;


import javax.sql.DataSource;

import com.kasprusz.covidstatisticsbackend.utils.HistoricalJobNotificationListener;
import com.kasprusz.covidstatisticsbackend.utils.HistoricalProcessor;
import com.kasprusz.covidstatisticsbackend.utils.VoivodeshipJobNotificationListener;
import com.kasprusz.covidstatisticsbackend.utils.VoivodeshipProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@EnableBatchProcessing
@Configuration
public class CsvFilesToDatabaseConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

private final DataSource dataSource;

    @Autowired
    public CsvFilesToDatabaseConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public FlatFileItemReader<VoivodeshipStatistics> csvVoivodeshipStatisticsReader(){
        FlatFileItemReader<VoivodeshipStatistics> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("powiaty.csv"));
        reader.setEncoding("Windows-1250");
        reader.setLinesToSkip(1);
        reader.setLineMapper(voivodeshipLineMapper());
        return reader;
    }

    @Bean
    public FlatFileItemReader<HistoricalStatistics> csvHistoricalStatisticsReader(){
        FlatFileItemReader<HistoricalStatistics> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("zakazenia.csv"));
        reader.setEncoding("Windows-1250");
        reader.setLinesToSkip(1);
        reader.setLineMapper(historicalLineMapper());
        return reader;
    }

    @Bean
    public LineMapper<VoivodeshipStatistics> voivodeshipLineMapper() {
        DefaultLineMapper<VoivodeshipStatistics> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new VoivodeshipMapper());
        return lineMapper;
    }


    @Bean
    public LineMapper<HistoricalStatistics> historicalLineMapper() {
        DefaultLineMapper<HistoricalStatistics> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new HistoricalMapper());
        return lineMapper;
    }


    @Bean
    public JdbcBatchItemWriter<VoivodeshipStatistics> csvVoivodeshipStatisticsWriter() {
        JdbcBatchItemWriter<VoivodeshipStatistics> csvVoivodeshipStatsWriter = new JdbcBatchItemWriter<>();
        csvVoivodeshipStatsWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        csvVoivodeshipStatsWriter.setSql("INSERT INTO VOIVODESHIP_STATISTICS(voivodeship, city, number_Of_Infection, number_For10Citizens, number_Of_Deaths" +
                ", number_Of_Deaths_With_Other_Sick, number_Of_Deaths_Without_Other_Sick) VALUES (:voivodeship, :city, :numberOfInfection," +
                ":numberFor10Citizens, :numberOfDeaths, :numberOfDeathsWithOtherSick, :numberOfDeathsWithoutOtherSick)");
        csvVoivodeshipStatsWriter.setDataSource(dataSource);
        return csvVoivodeshipStatsWriter;
    }

    @Bean
    public JdbcBatchItemWriter<HistoricalStatistics> csvHistoricalStatisticsWriter() {
        JdbcBatchItemWriter<HistoricalStatistics> csvVoivodeshipStatsWriter = new JdbcBatchItemWriter<>();
        csvVoivodeshipStatsWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        csvVoivodeshipStatsWriter.setSql("INSERT INTO HISTORICAL_STATISTICS(" +
                "     measure_Day,\n" +
                "     new_Cases,\n" +
                "     all_Cases,\n" +
                "     deaths,\n" +
                "     all_Cumulative,\n" +
                "     recovered,\n" +
                "     all_Recovered,\n" +
                "     active_Cases,\n" +
                "     hospital_Cases,\n" +
                "     quarantine_Cases,\n" +
                "     monitoring_Cases,\n" +
                "     occupied_Beds,\n" +
                "     respirators)" +
                " VALUES ( :measureDay,\n" +
                "     :newCases,\n" +
                "     :allCases,\n" +
                "     :deaths,\n" +
                "     :allCumulative,\n" +
                "     :recovered,\n" +
                "     :allRecovered,\n" +
                "     :activeCases,\n" +
                "     :hospitalCases,\n" +
                "     :quarantineCases,\n" +
                "     :monitoringCases,\n" +
                "     :occupiedBeds,\n" +
                "     :respirators)");
        csvVoivodeshipStatsWriter.setDataSource(dataSource);
        return csvVoivodeshipStatsWriter;
    }


    @Bean
    VoivodeshipProcessor getVoivodeshipStatisticProcessor() {
        return new VoivodeshipProcessor();
    }

    @Bean
    HistoricalProcessor getHistoricalStatisticProcessor() {
        return new HistoricalProcessor();
    }

    @Bean
    public Step csvVoivodeshipFileToDatabaseStep() {
        return stepBuilderFactory.get("csvVoivodeshipFileToDatabaseStep")
                .<VoivodeshipStatistics, VoivodeshipStatistics>chunk(1)
                .reader(csvVoivodeshipStatisticsReader())
                .processor(getVoivodeshipStatisticProcessor())
                .writer(csvVoivodeshipStatisticsWriter())
                .build();
    }

    @Bean
    public Step csvHistoricalFileToDatabaseStep() {
        return stepBuilderFactory.get("csvHistoricalFileToDatabaseStep")
                .<HistoricalStatistics, HistoricalStatistics>chunk(1)
                .reader(csvHistoricalStatisticsReader())
                .processor(getHistoricalStatisticProcessor())
                .writer(csvHistoricalStatisticsWriter())
                .build();
    }

    @Bean
    Job csvFileToDatabaseJob(VoivodeshipJobNotificationListener listener) {
        return jobBuilderFactory.get("csvVoivodeshipFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(csvVoivodeshipFileToDatabaseStep())
                .end()
                .build();
    }

    @Bean
    Job csvFileStatToDatabaseJob(HistoricalJobNotificationListener listener) {
        return jobBuilderFactory.get("csvHistoricalFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(csvHistoricalFileToDatabaseStep())
                .end()
                .build();
    }

}