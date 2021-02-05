package org.novonet.billing.controllers;

import org.novonet.billing.models.Application;
import org.novonet.billing.models.Debit;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.ApplicationRepository;
import org.novonet.billing.repo.DebitRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DebitController {
    @Autowired
    private DebitRepository debitRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping("/debits/")
    private ResponseEntity getAllSubscribers(){
        Iterable<Debit> debits = debitRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(debits);
    }

    @GetMapping("/debits/{id}")
    private  ResponseEntity getSubscriberById(@PathVariable long id){
        Optional<Debit> debit = debitRepository.findById(id);
        if (debit.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(debit);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

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

    @DeleteMapping("/debits/{id}")
    public ResponseEntity deleteApplicationById(@PathVariable long id){
        Optional<Debit> debit = debitRepository.findById(id);
        if (debit.isPresent()) {
            debitRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(debit);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
