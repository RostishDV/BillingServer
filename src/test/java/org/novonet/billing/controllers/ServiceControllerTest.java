package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.novonet.billing.TestController;
import org.novonet.billing.models.Service;
import org.novonet.billing.service.EntryWithSameNameAlreadyExist;
import org.novonet.billing.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestController
public class ServiceControllerTest {
    @Autowired
    private ServiceController serviceController;

    @Autowired
    private ServiceService serviceService;

    private Service testService1;
    private Service testService2;

    @BeforeEach
    void init(){
        testService1 = new Service();
        testService1.setName("Услуга");
        testService1.setPrice(100.);
        try {
            serviceService.save(testService1);
        } catch (EntryWithSameNameAlreadyExist entryWithSameNameAlreadyExist) {
            entryWithSameNameAlreadyExist.printStackTrace();
        }
        testService2 = new Service();
        testService2.setName("Другая услуга");
        testService2.setPrice(150.);
        try {
            serviceService.save(testService2);
        } catch (EntryWithSameNameAlreadyExist entryWithSameNameAlreadyExist) {
            entryWithSameNameAlreadyExist.printStackTrace();
        }

    }

    @Test
    @DisplayName("создание новой услуги")
    public void crateNewService(){
        Service expectedService = new Service();
        expectedService.setName("Новая услуга");
        expectedService.setPrice(125.);
        ResponseEntity responseEntity = serviceController.addNewService("Новая услуга", 125.);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedService, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка создания существующей услуги")
    public void createExistedService(){
        ResponseEntity responseEntity = serviceController.addNewService("Другая услуга", 125.);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
        assertEquals("Другая услуга", responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка удаления существующей услуги по ID")
    public void deleteExistedServiceById(){
        ResponseEntity responseEntity = serviceController.deleteById((long) 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testService1, responseEntity.getBody());
    }
    @Test
    @DisplayName("Попытка удаления несуществующей услуги по ID")
    public void deleteNonExistedServiceById(){
        ResponseEntity responseEntity = serviceController.deleteById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
     }

    @Test
    @DisplayName("Попытка получения существующей услуги")
    public void getExistedServiceById(){
        ResponseEntity responseEntity = serviceController.findById((long) 2);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testService2, responseEntity.getBody());
    }

    @Test
    @DisplayName("Попытка получения не существующей услуги")
    public void getNonExistedServiceById(){
        ResponseEntity responseEntity = serviceController.findById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("Получение всех услуг")
    public void getAllServices(){
        ResponseEntity responseEntity = serviceController.findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
