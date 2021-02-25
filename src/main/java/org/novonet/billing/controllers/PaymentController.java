package org.novonet.billing.controllers;

import org.novonet.billing.models.Payment;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.PaymentRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping("/payments/")
    private ResponseEntity getAllSubscribers(){
        Iterable<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }

    @GetMapping("/payments/{id}")
    private  ResponseEntity getSubscriberById(@PathVariable long id){
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(payment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

    @PostMapping("/payments/")
    private ResponseEntity addNewApplication(@RequestParam long subscriberId,
                                             @RequestParam double debitedMoney
    ){
        try {
            Optional<Subscriber> optionalSubscriber = subscriberRepository.findById(subscriberId);
            if (optionalSubscriber.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            }
            Subscriber subscriber = optionalSubscriber.get();
            Payment payment = new Payment(subscriberId, debitedMoney, subscriber.getBalance());
            subscriber.addPayment(payment);
            subscriberRepository.save(subscriber);
            return ResponseEntity.status(HttpStatus.OK).body(payment.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity deleteApplicationById(@PathVariable long id){
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(payment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
