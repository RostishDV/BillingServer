package org.novonet.billing.controllers;

import org.novonet.billing.models.Subscriber;
import org.novonet.billing.service.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribers")
public class SubscriberController extends AbstractController<Subscriber, SubscriberService> {

    protected SubscriberController(SubscriberService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity addNewSubscriber(@RequestParam() String surname,
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
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }
}
