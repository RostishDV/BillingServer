package org.novonet.billing.controllers;

import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class SubscriberController {
    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping("/subscribers/")
    private ResponseEntity getAllSubscribers(){
        Iterable<Subscriber> subscribers = subscriberRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(subscribers);
    }

    @GetMapping("/subscribers/{id}")
    private  ResponseEntity getSubscriberById(@PathVariable long id){
        Optional<Subscriber> subscriber = subscriberRepository.findById(id);
        if (subscriber.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(subscriber);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
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
            System.out.println(surname + " " + name + " " + patronymic + " " + city);
            Subscriber subscriber = new Subscriber(surname, name, patronymic, city, street, house, building, apartment, phone);
            subscriberRepository.save(subscriber);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }


}
