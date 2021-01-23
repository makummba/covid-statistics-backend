package com.kasprusz.covidstatisticsbackend.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kasprusz.covidstatisticsbackend.CovidStatisticsApplication;
import com.kasprusz.covidstatisticsbackend.services.HistoricalStatisticService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CovidStatisticsApplication.class })
@WebAppConfiguration
@SpringBootTest(properties = { "spring.batch.job.enabled=true" })
public class VoivodeshipStatisticsControllerIT {
    private final String VOIVODESHIP_ALL_URL = "/voivodeship/all";
    private final String VOIVODESHIP_GROUPED_URL = "/voivodeship/grouped";

    @Autowired
    HistoricalStatisticService service;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getAllVoivodeshipStatistics() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(VOIVODESHIP_ALL_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(381)))
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());

    }

    @Test
    public void getGroupedVoivodeshipStatistics() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(VOIVODESHIP_GROUPED_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(17)))
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());

    }
}