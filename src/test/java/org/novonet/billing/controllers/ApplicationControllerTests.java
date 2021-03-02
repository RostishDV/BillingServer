package org.novonet.billing.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.novonet.billing.models.Application;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.ApplicationRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerTests{

    @Autowired
    private ApplicationController applicationController;

    @Autowired
    private static ApplicationRepository applicationRepository;

    @Autowired
    private static SubscriberRepository subscriberRepository;

    @Autowired
    private MockMvc mockMvc;

    private List<Subscriber> subscribersList;

    private List<Application> applicationList;

    @BeforeAll
    static void initAll(){
        Subscriber testSubscriber = new Subscriber();
        testSubscriber.setName("Имя");
        testSubscriber.setPatronymic("Отчество");
        testSubscriber.setSurname("Фамилия");
        testSubscriber.setCity("Город");
        testSubscriber.setStreet("Удица");
        testSubscriber.setApartment(1);
        testSubscriber.setHouse(1);
        long phone = 654_321;
        testSubscriber.setPhone(phone);
        testSubscriber.setBalance(0.);

        subscriberRepository.save(testSubscriber);
    }

    @BeforeEach
    static void init(){

    }

    @Test
    public void createNewApplicationTest(){
        Subscriber testSubscriber = new Subscriber();
        testSubscriber.setName("Имя");
        testSubscriber.setPatronymic("Отчество");
        testSubscriber.setSurname("Фамилия");
        testSubscriber.setCity("Город");
        testSubscriber.setStreet("Удица");
        testSubscriber.setApartment(1);
        testSubscriber.setHouse(1);
        long phone = 654_321;
        testSubscriber.setPhone(phone);
        testSubscriber.setBalance(0.);
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    public static RequestPostProcessor authentication() {
        return request -> {
            request.addHeader("Authorization", getBasicAuthHeader("John", "secr3t"));
            return request;
        };
    }
}
