package org.novonet.billing.controllers;

import org.novonet.billing.models.Subscriber2Rate;
import org.novonet.billing.models.Subscriber2RateId;
import org.novonet.billing.repo.Subscriber2RateRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class Subscriber2RateController {
    @Autowired
    private Subscriber2RateRepository subscriber2RateRepository;
    private SubscriberRepository subscriberRepository;

//    @GetMapping("/subscriber2rates/{subscriberId}")
//    private ResponseEntity getSubscriber2Rates(@PathVariable long subscriberId){
//        Optional<Subscriber> subscriber = subscriberRepository.findById(subscriberId);
//        if (subscriber.isPresent()) {
//            Iterable<Subscriber2Rate> subscriber2Rates = subscriber2RateRepository.findAll();
//            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Rates);
//        }
//    }

    @GetMapping("/subscriber2rates/{subscriberId} {rateId}")
    private  ResponseEntity getRateById(@PathVariable long subscriberId,
                                        @PathVariable long rateId){
        Subscriber2RateId subscriber2RateId = new Subscriber2RateId(
                subscriberId, rateId
        );

        Optional<Subscriber2Rate> subscriber2Rate = subscriber2RateRepository
                .findById(subscriber2RateId);
        if (subscriber2Rate.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Rate);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subscriber2RateId);
    }

    @PostMapping("/subscriber2rates/")
    private ResponseEntity addNewRate(@RequestParam long subscriberId,
                                      @RequestParam long rateId){
        try {
            Subscriber2RateId subscriber2RateId = new Subscriber2RateId(
                    subscriberId, rateId
            );
            Subscriber2Rate subscriber2Rate = new Subscriber2Rate();
            subscriber2RateRepository.save(subscriber2Rate);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Rate
                    .getSubscriber2RateId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }


    @DeleteMapping("/subscriber2rates/{id}")
    public ResponseEntity deleteSubscriberById(@PathVariable long subscriberId,
                                               @PathVariable long rateId){
        Subscriber2RateId subscriber2RateId =
                new Subscriber2RateId(subscriberId, rateId);
        Optional<Subscriber2Rate> subscriber2Rate = subscriber2RateRepository
                .findById(subscriber2RateId);
        if (subscriber2Rate.isPresent()) {
            subscriber2RateRepository.deleteById(subscriber2RateId);
            return ResponseEntity.status(HttpStatus.OK).body(subscriber2Rate);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subscriber2RateId);
    }
}
