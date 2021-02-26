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


    @PostMapping("/rates/")
    private ResponseEntity addNewRate(@RequestParam String name,
                                      @RequestParam double price){
        if (rateRepository.findByName(name).isPresent()){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("rate with name " + name + " already exist");
        }
        Rate rate = new Rate();
        rate.setName(name);
        rate.setPrice(price);
        rateRepository.save(rate);
        return ResponseEntity.status(HttpStatus.OK).body(rate.getId());
    }


    @DeleteMapping("/rates/{id}")
    public ResponseEntity deleteRateById(@PathVariable long id){
        Optional<Rate> rate = rateRepository.findById(id);
        if (rate.isPresent()) {
            rateRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(rate);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
