package com.valea.inditextechtest.infrastructure.price.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Controller e2e test. Hooked with H2 in memory DB.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getExpectedApplicablePriceTest1() throws Exception {

        this.mockMvc.perform(get("/api/price")
                                     .queryParam("productId", "35455")
                                     .queryParam("brandId", "1")
                                     .queryParam("applicableDateTime", "2020-06-14T10:00:00Z"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("productId").value(35455))
                    .andExpect(jsonPath("brandId").value(1))
                    .andExpect(jsonPath("priceList").value(1))
                    .andExpect(jsonPath("pvp.value").value(35.50))
                    .andExpect(jsonPath("pvp.currency").value("EUR"))
                    .andExpect(jsonPath("startDate").value("2020-06-14T00:00:00"))
                    .andExpect(jsonPath("endDate").value("2020-12-31T23:59:59"));
    }


    @Test
    public void getExpectedApplicablePriceTest2() throws Exception {

        this.mockMvc.perform(get("/api/price")
                                     .queryParam("productId", "35455")
                                     .queryParam("brandId", "1")
                                     .queryParam("applicableDateTime", "2020-06-14T16:00:00Z"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("productId").value(35455))
                    .andExpect(jsonPath("brandId").value(1))
                    .andExpect(jsonPath("priceList").value(2))
                    .andExpect(jsonPath("pvp.value").value(25.45))
                    .andExpect(jsonPath("pvp.currency").value("EUR"))
                    .andExpect(jsonPath("startDate").value("2020-06-14T15:00:00"))
                    .andExpect(jsonPath("endDate").value("2020-06-14T18:30:00"));
    }


    @Test
    public void getExpectedApplicablePriceTest3() throws Exception {

        this.mockMvc.perform(get("/api/price")
                                     .queryParam("productId", "35455")
                                     .queryParam("brandId", "1")
                                     .queryParam("applicableDateTime", "2020-06-14T21:00:00Z"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("productId").value(35455))
                    .andExpect(jsonPath("brandId").value(1))
                    .andExpect(jsonPath("priceList").value(1))
                    .andExpect(jsonPath("pvp.value").value(35.50))
                    .andExpect(jsonPath("pvp.currency").value("EUR"))
                    .andExpect(jsonPath("startDate").value("2020-06-14T00:00:00"))
                    .andExpect(jsonPath("endDate").value("2020-12-31T23:59:59"));
    }


    @Test
    public void getExpectedApplicablePriceTest4() throws Exception {

        this.mockMvc.perform(get("/api/price")
                                     .queryParam("productId", "35455")
                                     .queryParam("brandId", "1")
                                     .queryParam("applicableDateTime", "2020-06-15T10:00:00Z"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("productId").value(35455))
                    .andExpect(jsonPath("brandId").value(1))
                    .andExpect(jsonPath("priceList").value(3))
                    .andExpect(jsonPath("pvp.value").value(30.50))
                    .andExpect(jsonPath("pvp.currency").value("EUR"))
                    .andExpect(jsonPath("startDate").value("2020-06-15T00:00:00"))
                    .andExpect(jsonPath("endDate").value("2020-06-15T11:00:00"));
    }


    @Test
    public void getExpectedApplicablePriceTest5() throws Exception {

        this.mockMvc.perform(get("/api/price")
                                     .queryParam("productId", "35455")
                                     .queryParam("brandId", "1")
                                     .queryParam("applicableDateTime", "2020-06-16T21:00:00Z"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("productId").value(35455))
                    .andExpect(jsonPath("brandId").value(1))
                    .andExpect(jsonPath("priceList").value(4))
                    .andExpect(jsonPath("pvp.value").value(38.95))
                    .andExpect(jsonPath("pvp.currency").value("EUR"))
                    .andExpect(jsonPath("startDate").value("2020-06-15T16:00:00"))
                    .andExpect(jsonPath("endDate").value("2020-12-31T23:59:59"));
    }


    @Test
    public void throwPriceNotFoundExceptionTest() throws Exception {
        this.mockMvc.perform(get("/api/price")
                                     .queryParam("productId", "35455")
                                     .queryParam("brandId", "2")    // No register with this brand
                                     .queryParam("applicableDateTime", "2020-06-15T19:00:00Z"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("error").exists());

    }


    @Test
    public void throwMissingAttributeExceptionTest() throws Exception {
        this.mockMvc.perform(get("/api/price")
                                     // missing brandId query param
                                     .queryParam("productId", "35455")
                                     .queryParam("applicableDateTime", "2020-06-15T19:00:00Z"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("error").exists());
    }
}
