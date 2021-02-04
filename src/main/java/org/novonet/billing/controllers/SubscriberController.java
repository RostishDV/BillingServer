package org.novonet.billing.controllers;

import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.SubscriberRepository;
import org.novonet.billing.services.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SubscriberController {

    private final SubscriberRepository subscriberRepository = RepositoryService.getSubscriberRepository();

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
            Subscriber subscriber = new Subscriber(surname, name, patronymic, city, street, house, building, apartment, phone);
            subscriberRepository.save(subscriber);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }


    }

    @RequestMapping(path = "/subscribers/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteSubscriberById(@PathVariable long id){
        Optional<Subscriber> subscriber = subscriberRepository.findById(id);
        if (subscriber.isPresent()) {
            subscriberRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

}
