package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.novonet.billing.TestController;
import org.novonet.billing.models.Rate;
import org.novonet.billing.service.EntryWithSameNameAlreadyExist;
import org.novonet.billing.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestController
public class RateControllerTest {
    @Autowired
    private RateController rateController;

    @Autowired
    private RateService rateService;

    private Rate testRate;

    @BeforeTestClass
    void setUp(){
        testRate = new Rate();
        testRate.setPrice(100.);
        testRate.setName("Название");
        try {
            rateService.save(testRate) ;
        } catch (EntryWithSameNameAlreadyExist entryWithSameNameAlreadyExist) {
            entryWithSameNameAlreadyExist.printStackTrace();
        }
    }

    @Test
    @DisplayName("создание нового тарифа")
    public void createNewRateTest(){
        Rate expectedRate = new Rate();
        expectedRate.setName("Name");
        expectedRate.setPrice(400.);
        ResponseEntity responseEntity = rateController.addNewRate("Name", 400.);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedRate, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка создания существующего тарифа")
    public void createExistedRate(){
        ResponseEntity responseEntity = rateController.addNewRate(testRate.getName(), 400.);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
        assertEquals(testRate.getName(), responseEntity.getBody());
    }


}
