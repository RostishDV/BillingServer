package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.novonet.billing.TestController;
import org.novonet.billing.models.BillingUser;
import org.novonet.billing.service.BillingUserService;
import org.novonet.billing.service.EntryWithSameNameAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestController
public class BillingUserControllerTest {
    @Autowired
    private BillingUserController billingUserController;

    @Autowired
    private BillingUserService billingUserService;

    private BillingUser testUser1;

    private BillingUser testUser2;

    @BeforeEach
    void init(){
        testUser1 = new BillingUser();
        testUser1.setName("First");
        testUser1.setPassword("First");
        testUser1.setPrivilege("User");
        try {
            billingUserService.save(testUser1);
        } catch (EntryWithSameNameAlreadyExist entryWithSameNameAlreadyExist) {
            entryWithSameNameAlreadyExist.printStackTrace();
        }

        testUser2 = new BillingUser();
        testUser2.setName("Second");
        testUser2.setPassword("Second");
        testUser2.setPrivilege("User");
        try {
            billingUserService.save(testUser2);
        } catch (EntryWithSameNameAlreadyExist entryWithSameNameAlreadyExist) {
            entryWithSameNameAlreadyExist.printStackTrace();
        }
    }

    @Test
    @DisplayName("создание нового пользователя")
    public void createNewUser(){
        BillingUser expectedUser = new BillingUser();
        expectedUser.setName("Имя");
        expectedUser.setPrivilege("User");
        expectedUser.setPassword("Пароль");
        ResponseEntity responseEntity = billingUserController.addNewBillingUser(
                "Имя", "Пароль", "User");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUser, responseEntity.getBody());
    }

    @Test
    @DisplayName("создание существующего пользователя")
    public void createUserWithSameName(){
        ResponseEntity responseEntity = billingUserController.
                addNewBillingUser(testUser2.getName(),
                        testUser2.getPassword(), testUser2.getPrivilege());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
        assertEquals(testUser2.getName(), responseEntity.getBody());
    }

    @Test
    @DisplayName("удаление существующего пользователя")
    public void deleteExistedUser(){
        ResponseEntity responseEntity = billingUserController.deleteById((long) 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testUser1, responseEntity.getBody());
    }

    @Test
    @DisplayName("Удаление несуществующего пользователя")
    public void deleteNonExistedUser(){
        ResponseEntity responseEntity = billingUserController.deleteById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("Получение существующего пользователя по Id")
    public void getExistedUserById(){
        ResponseEntity responseEntity = billingUserController.findById((long) 2);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testUser2, responseEntity.getBody());
    }

    @Test
    @DisplayName("Получение не существующего пользователя по Id")
    public void getNonExistedUserById(){
        ResponseEntity responseEntity = billingUserController.findById((long) 100);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals((long) 100, responseEntity.getBody());
    }

    @Test
    @DisplayName("Получение существующего пользователя по имени")
    public void getExistedUserByName(){
        ResponseEntity responseEntity = billingUserController.
                getBillingUserByName("Second");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testUser2, responseEntity.getBody());
    }

    @Test
    @DisplayName("Получение несуществующего пользователя по имени")
    public void getNonExistedUserByName(){
        ResponseEntity responseEntity = billingUserController.
                getBillingUserByName("Third");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Third", responseEntity.getBody());
    }

    @Test
    @DisplayName("Получение всех пользователей")
    public void getAllUsers(){
        ResponseEntity responseEntity = billingUserController.findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
