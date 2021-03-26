package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.novonet.billing.TestController;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@TestController
public class SubscriberControllerTests {

    @Autowired
    private SubscriberController subscriberController;

    @Autowired
    private SubscriberService subscriberService;

    private Subscriber testSubscriber;

    @BeforeEach
    void init(){
        testSubscriber = new Subscriber();
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

        subscriberService.save(testSubscriber);
    }

    @Test
    @DisplayName("создание нового пользователя")
    public void createNewSubscriberTest(){
        ResponseEntity responseEntity = subscriberController.addNewSubscriber(
                "Фамилия", "Имя", "Отчество",
                "Город", "Улица", 1, 0,1,
                (long) 654_321);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testSubscriber, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка получения существующего пользователя")
    public void findByExistedId(){
        ResponseEntity responseEntity = subscriberController.findById((long) 2);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testSubscriber, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка получения не существующего пользователя")
    public void findByNonExistedId(){
        ResponseEntity responseEntity = subscriberController.findById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("проверка получения всех пользователей")
    public void findAllTest(){
        ResponseEntity responseEntity = subscriberController.findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("удаление существующего пользователя")
    public void deleteByExistedId(){
        ResponseEntity responseEntity = subscriberController.deleteById((long) 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Subscriber actualSubscriber = (Subscriber) responseEntity.getBody();
        assertEquals(1, actualSubscriber.getId());
    }

    @Test
    @DisplayName("попытка удаления несуществующего пользователя")
    public void deleteNotExistedById(){
        ResponseEntity responseEntity = subscriberController.deleteById((long) 10);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 10, responseEntity.getBody());
    }
}
