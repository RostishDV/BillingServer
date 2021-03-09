package org.novonet.billing.controllers;

import org.novonet.billing.models.Debit;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.DebitRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.novonet.billing.service.DebitService;
import org.novonet.billing.service.SubscriberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DebitController extends AbstractController<Debit, DebitService> {

    public DebitController(DebitService service) {
        super(service);
    }

    @PostMapping("/debits/")
    private ResponseEntity addNewDebit(@RequestParam long subscriberId,
                                             @RequestParam double debitedMoney
    ){
        Debit debit = new Debit();
        debit.setSubscriberId(subscriberId);
        debit.setDebitedMoney(debitedMoney);
        try {
            getService().save(debit);
            return ResponseEntity.status(HttpStatus.OK).body(debit);
        } catch (SubscriberNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
        }
    }
}
