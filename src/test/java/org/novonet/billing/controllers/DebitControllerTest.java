package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.novonet.billing.TestController;
import org.novonet.billing.models.Debit;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.service.DebitService;
import org.novonet.billing.service.SubscriberNotFoundException;
import org.novonet.billing.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestController
@Transactional
public class DebitControllerTest {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private DebitController debitController;

    @Autowired
    private DebitService debitService;

    private Subscriber testSubscriber;
    private Debit testDebit1;
    private Debit testDebit2;

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

        testDebit1 = new Debit();
        testDebit1.setSubscriberId(1);
        testDebit1.setDebitedMoney(150.);
        testDebit1.setReason("rate");
        try {
            debitService.save(testDebit1);
        } catch (SubscriberNotFoundException e) {
            e.printStackTrace();
        }

        testDebit2 = new Debit();
        testDebit2.setSubscriberId(1);
        testDebit2.setDebitedMoney(170.);
        testDebit2.setReason("rate");
        try {
            debitService.save(testDebit2);
        } catch (SubscriberNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("создание списания для существующего клиента")
    public void createNewDebitToExistedSubscriber() {
        Debit expectedDebit = new Debit();
        expectedDebit.setSubscriberId(1);
        expectedDebit.setDebitedMoney(125.);
        ResponseEntity responseEntity = debitController.addNewDebit((long) 1, 125., "rate");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDebit, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка создания списания для несуществующего пользователя")
    public void createNewDebitToNonExistedSubscriber() {
        ResponseEntity responseEntity = debitController.addNewDebit((long) 100, 130., "rate");
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка получения существующего списания из списаний")
    public void getExistedDebitFromDebits(){
        ResponseEntity responseEntity = debitController.findById((long) 2);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testDebit2, responseEntity.getBody());
    }

    @Test
    @DisplayName("Попытка получения не существующего списания из списаний")
    public void getNonExistedDebitFromDebits(){
        ResponseEntity responseEntity = debitController.findById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка получения существующего списания через клиента")
    public void getExistedDebitFromSubscriber(){
        // do this feature in controller
        Iterable<Subscriber> iterableSubscribers = subscriberService.findAll();
        boolean isPresent = false;
        for (Subscriber subscriber : iterableSubscribers){
            for (Debit debit : subscriber.getDebits()){
                if (debit.equals(testDebit2)){
                    isPresent = true;
                }
            }
        }
        assertTrue(isPresent);
    }

    @Test
    @DisplayName("удаление существующего списания по id")
    public void deleteExistedDebitById(){
        ResponseEntity responseEntity = debitController.deleteById((long) 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testDebit1, responseEntity.getBody());
    }

    @Test
    @DisplayName("попытка удаелния несуществующего списания по id")
    public void deleteNonExistedDebitById(){
        ResponseEntity responseEntity = debitController.deleteById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }
}
