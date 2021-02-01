package org.novonet.billing.controllers;

import org.novonet.billing.models.Subscriber2Service;
import org.novonet.billing.models.Subscriber2ServiceId;
import org.novonet.billing.repo.Subscriber2ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class Subscriber2ServiceController {
    @Autowired
    private Subscriber2ServiceRepository subscriber2ServiceRepository;


//    @GetMapping("/subscriber2service/{subscriberId}")
//    private ResponseEntity getSubscriber2Service(@PathVariable long subscriberId){
//        Optional<Subscriber> subscriber = subscriberRepository.findById(subscriberId);
//        if (subscriber.isPresent()) {
//            Iterable<Subscriber2Service> subscriber2Service = subscriber2ServiceRepository.findAll();
//            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Service);
//        }
//    }

    @GetMapping("/subscriber2service/{subscriberId} {serviceId}")
    private ResponseEntity getServiceById(@PathVariable long subscriberId,
                                       @PathVariable long serviceId){
        Subscriber2ServiceId subscriber2ServiceId = new Subscriber2ServiceId(
                subscriberId, serviceId
        );


        Optional<Subscriber2Service> subscriber2Service = subscriber2ServiceRepository
                .findById(subscriber2ServiceId);
        if (subscriber2Service.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Service);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subscriber2ServiceId);
    }

    @PostMapping("/subscriber2rates/")
    private ResponseEntity addNewRate(@RequestParam long subscriberId,
                                      @RequestParam long rateId){
        try {
            Subscriber2ServiceId subscriber2RateId = new Subscriber2ServiceId(
                    subscriberId, rateId
            );
            Subscriber2Service subscriber2Service = new Subscriber2Service();
            subscriber2ServiceRepository.save(subscriber2Service);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Service
                    .getSubscriber2ServiceId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }


    @DeleteMapping("/subscriber2rates/{id}")
    public ResponseEntity deleteSubscriberById(@PathVariable long subscriberId,
                                               @PathVariable long rateId){
        Subscriber2ServiceId subscriber2RateId =
                new Subscriber2ServiceId(subscriberId, rateId);
        Optional<Subscriber2Service> subscriber2Service = subscriber2ServiceRepository
                .findById(subscriber2RateId);
        if (subscriber2Service.isPresent()) {
            subscriber2ServiceRepository.deleteById(subscriber2RateId);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Service);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subscriber2RateId);
    }
}
