package org.novonet.billing.controllers;

import org.novonet.billing.models.Rate;
import org.novonet.billing.repo.RateRepository;
import org.novonet.billing.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class RateController {
    private final RateRepository rateRepository = RepositoryService.getRateRepository();

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
                                            @RequestParam Double price){
        try {
            Rate rate = new Rate();
            rate.setName(name);
            rate.setPrice(price);
            rateRepository.save(rate);
            return ResponseEntity.status(HttpStatus.OK).body(rate.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
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
