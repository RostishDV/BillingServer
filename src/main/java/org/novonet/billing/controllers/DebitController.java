package org.novonet.billing.controllers;

import org.novonet.billing.models.Debit;
import org.novonet.billing.service.DebitService;
import org.novonet.billing.service.SubscriberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debits")
public class DebitController extends AbstractController<Debit, DebitService> {

    public DebitController(DebitService service) {
        super(service);
    }

    @PostMapping
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
