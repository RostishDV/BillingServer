package org.novonet.billing.controllers;

import org.novonet.billing.models.Payment;
import org.novonet.billing.repo.PaymentRepository;
import org.novonet.billing.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PaymentController {
    private final PaymentRepository paymentRepository = RepositoryService.getPaymentRepository();

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
                                             @RequestParam double debitedMoney,
                                             @RequestParam double previousBalance
    ){
        try {
            Payment payment = new Payment(subscriberId, debitedMoney, previousBalance);
            paymentRepository.save(payment);
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
