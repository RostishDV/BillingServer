package org.novonet.billing.controllers;

import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.SubscriberRepository;
import org.novonet.billing.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subscribers")
public class SubscriberController extends AbstractController<Subscriber, SubscriberService> {

    protected SubscriberController(SubscriberService service) {
        super(service);
    }

    @PostMapping("/subscribers/")
    private ResponseEntity addNewSubscriber(@RequestParam() String surname,
                                            @RequestParam() String name,
                                            @RequestParam() String patronymic,
                                            @RequestParam() String city,
                                            @RequestParam() String street,
                                            @RequestParam() Integer house,
                                            @RequestParam() Integer building,
                                            @RequestParam() Integer apartment,
                                            @RequestParam() Long phone){
        try {
            Subscriber subscriber = new Subscriber(surname, name, patronymic, city, street, house, building, apartment, phone);
            getService().save(subscriber);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber);
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }
}
