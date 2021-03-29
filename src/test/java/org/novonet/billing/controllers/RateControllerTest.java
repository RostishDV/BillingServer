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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestController
public class RateControllerTest {
    @Autowired
    private RateController rateController;

    @Autowired
    private RateService rateService;

    private Rate testRate1;
    private Rate testRate2;

    @BeforeEach
    void setUp(){
        testRate1 = new Rate();
        testRate1.setPrice(100.);
        testRate1.setName("Название");
        try {
            rateService.save(testRate1) ;
        } catch (EntryWithSameNameAlreadyExist entryWithSameNameAlreadyExist) {
            entryWithSameNameAlreadyExist.printStackTrace();
        }

        testRate2 = new Rate();
        testRate2.setPrice(100.);
        testRate2.setName("Другое название");
        try {
            rateService.save(testRate2) ;
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
        ResponseEntity actualResponse = rateController.addNewRate(testRate1.getName(), 400.);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, actualResponse.getStatusCode());
        assertEquals(testRate1.getName(), actualResponse.getBody());
    }

    @Test
    @DisplayName("попытка удаления существующего тарифа по ID")
    public void deleteExistedRateById(){
        ResponseEntity responseEntity = rateController.deleteById((long) 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testRate1, responseEntity.getBody());
    }

    @Test
    @DisplayName("Попытка удаления несуществующего тарифа по ID")
    public void deleteNonExistedRateById(){
        ResponseEntity responseEntity = rateController.deleteById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("Попытка получения существующего тарифа")
    public void getExistedRateById(){
        ResponseEntity responseEntity = rateController.findById((long) 2);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testRate2, responseEntity.getBody());
    }

    @Test
    @DisplayName("Попытка получения не существующего тарифа")
    public void getNonExistedRateById(){
        ResponseEntity responseEntity = rateController.findById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("Получение всех тарифов")
    public void getAllRates(){
        ResponseEntity responseEntity = rateController.findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
