package org.novonet.billing.controllers;

import org.novonet.billing.models.Debit;
import org.novonet.billing.repo.DebitRepository;
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

    @GetMapping("/debits/")
    private ResponseEntity getAllDebits(){
        Iterable<Debit> debits = debitRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(debits);
    }

    @GetMapping("/debits/{subscriberId}")
    private  ResponseEntity getDebitById(@PathVariable long id){

        Optional<Debit> debit = debitRepository.findById(id);
        if (debit.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(debit);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

    @PostMapping("/debits/")
    private ResponseEntity addNewDebit(@RequestParam long subscriberId,
                                             @RequestParam double debitedMoney,
                                             @RequestParam double previousBalance
    ){
        try {
            Debit debit = new Debit(subscriberId, debitedMoney, previousBalance);
            debitRepository.save(debit);
            return ResponseEntity.status(HttpStatus.OK).body(debit.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }

    @DeleteMapping("/debits/{id}")
    public ResponseEntity deleteDebitById(@PathVariable long id){
        Optional<Debit> debit = debitRepository.findById(id);
        if (debit.isPresent()) {
            debitRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(debit);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
