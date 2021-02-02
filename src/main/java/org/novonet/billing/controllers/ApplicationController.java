package org.novonet.billing.controllers;

import org.novonet.billing.models.Application;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.ApplicationRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ApplicationController {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping("/applications/")
    private ResponseEntity getAllSubscribers(){
        Iterable<Application> applications = applicationRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }

    @GetMapping("/applications/{id}")
    private  ResponseEntity getSubscriberById(@PathVariable long id){
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(application);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

    @PostMapping("/applications/")
    private ResponseEntity addNewApplication(@RequestParam long subscriberId,
                                             @RequestParam String status,
                                             @RequestParam String title,
                                             @RequestParam String description
                                             ){
        Optional<Subscriber> optionalSubscriber = subscriberRepository.findById(subscriberId);
        if (optionalSubscriber.isPresent()){
            Subscriber subscriber = optionalSubscriber.get();
            Application application = new Application(subscriberId, status,
                    title, description);
            subscriber.addApplication(application);
            subscriberRepository.save(subscriber);
            return ResponseEntity.status(HttpStatus.OK).body(application.getId());
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity deleteApplicationById(@PathVariable long id){
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()) {
            applicationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(application);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
