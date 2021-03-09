package org.novonet.billing.controllers;

import org.novonet.billing.models.Payment;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.PaymentRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.novonet.billing.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController extends AbstractController<Payment, PaymentService> {

    protected PaymentController(PaymentService service) {
        super(service);
    }

    @PostMapping
    private ResponseEntity addNewApplication(@RequestParam long subscriberId,
                                             @RequestParam double receivedMoney
    ){
        try {
            Payment payment = new Payment();
            payment.setSubscriberId(subscriberId);
            payment.setReceivedMoney(receivedMoney);
            getService().save(payment);
            return ResponseEntity.status(HttpStatus.OK).body(payment.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ex.getStackTrace());
        }
    }
}
