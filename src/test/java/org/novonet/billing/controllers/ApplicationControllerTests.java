package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.novonet.billing.models.Application;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.ApplicationRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ApplicationControllerTests{
//
//    @Autowired
//    private ApplicationController applicationController;
//
//    @Autowired
//    private static ApplicationRepository applicationRepository;
//
//    @Autowired
//    private static SubscriberRepository subscriberRepository;
//
//    private List<Subscriber> subscribersList;
//
//    private List<Application> applicationList;
//
//    @BeforeAll
//    static void initAll(){
//        Subscriber testSubscriber = new Subscriber();
//        testSubscriber.setName("Имя");
//        testSubscriber.setPatronymic("Отчество");
//        testSubscriber.setSurname("Фамилия");
//        testSubscriber.setCity("Город");
//        testSubscriber.setStreet("Улица");
//        testSubscriber.setApartment(1);
//        testSubscriber.setHouse(1);
//        long phone = 654_321;
//        testSubscriber.setPhone(phone);
//        testSubscriber.setBalance(0.);
//
//        subscriberRepository.save(testSubscriber);
//    }
//
//    @BeforeEach
//    static void init(){
//
//    }

//    @Test
//    public void createNewApplicationTest(){
//        ResponseEntity actualResponse = applicationController.addNewApplication(
//                1, "new", "install hardware",
//                "description");
//
//        assertEquals(actualResponse.getStatusCode(), HttpStatus.OK);
//        assertEquals(actualResponse.getBody(), 1);
//    }
}
