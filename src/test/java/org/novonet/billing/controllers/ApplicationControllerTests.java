package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.novonet.billing.TestController;
import org.novonet.billing.models.Application;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.ApplicationRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestController
public class ApplicationControllerTests{

    @Autowired
    private ApplicationController applicationController;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;
    
    private Application testApplication;


    @BeforeEach
    void init(){
        Subscriber testSubscriber = new Subscriber();
        testSubscriber.setName("Имя");
        testSubscriber.setPatronymic("Отчество");
        testSubscriber.setSurname("Фамилия");
        testSubscriber.setCity("Город");
        testSubscriber.setStreet("Улица");
        testSubscriber.setApartment(1);
        testSubscriber.setHouse(1);
        testSubscriber.setBuilding(0);
        long phone = 654_321;
        testSubscriber.setPhone(phone);
        testSubscriber.setBalance(0.);
        
        subscriberRepository.save(testSubscriber);

        testApplication = new Application();
        testApplication.setStatus("status");
        testApplication.setSubscriberId(testSubscriber.getId());
        testApplication.setTitle("Title");
        testApplication.setDescription("Description");

        applicationRepository.save(testApplication);
    }

    @Test
    public void createNewApplicationTest(){
        Application expectedApplication = new Application();
        expectedApplication.setStatus("status");
        expectedApplication.setSubscriberId((long) 1);
        expectedApplication.setTitle("title");
        expectedApplication.setDescription("description");
        ResponseEntity actualResponse = applicationController.save(
                1, "status", "title",
                "description");

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertTrue(actualResponse.hasBody());
        Application actualApplication = (Application) actualResponse.getBody();
        assertEquals(expectedApplication.getSubscriberId(), actualApplication.getSubscriberId());
    }

    @Test
    public void deleteByIdExistedEntity(){
        ResponseEntity actualResponse = applicationController.deleteById((long) 1);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertTrue(actualResponse.hasBody());
        Application actualApplication = (Application) actualResponse.getBody();
        assertEquals(1, actualApplication.getId());

    }

    @Test
    public void deleteByIdNotExisted(){
        ResponseEntity actualResponse = applicationController.deleteById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
        assertEquals(100, (long) actualResponse.getBody());
    }
}
