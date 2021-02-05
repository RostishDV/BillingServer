package org.novonet.billing.controllers;

import org.novonet.billing.models.Debit;
import org.novonet.billing.models.Rate;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.RateRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class RateController {
    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping("/rates")
    private ResponseEntity getAllRates(){
        Iterable<Rate> rates = rateRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(rates);
    }

    @GetMapping("/rates/{id}")
    private  ResponseEntity getRateById(@PathVariable long id){
        Optional<Rate> rate = rateRepository.findById(id);
        if (rate.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(rate);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }


    //todo: rework this part for rate
    @PostMapping("/debits/")
    private ResponseEntity addNewDebit(@RequestParam long subscriberId,
                                       @RequestParam double debitedMoney
    ){
        Optional<Subscriber> optionalSubscriber = subscriberRepository.findById(subscriberId);
        if (optionalSubscriber.isPresent()){
            Subscriber subscriber = optionalSubscriber.get();
            Debit debit = new Debit(subscriber.getId(), debitedMoney, subscriber.getBalance());
            subscriber.setBalance(subscriber.getBalance() - debitedMoney);
            subscriber.addDebit(debit);
            subscriberRepository.save(subscriber);
            return ResponseEntity.status(HttpStatus.OK).body(debit);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subscriberId);
        }
    }


    @DeleteMapping("/rates/{id}")
    public ResponseEntity deleteSubscriberById(@PathVariable long id){
        Optional<Rate> rate = rateRepository.findById(id);
        if (rate.isPresent()) {
            rateRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(rate);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
