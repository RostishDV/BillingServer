package org.novonet.billing.controllers;

import org.novonet.billing.models.Rate;
import org.novonet.billing.service.EntryWithSameNameAlreadyExist;
import org.novonet.billing.service.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class RateController extends AbstractController<Rate, RateService> {

    protected RateController(RateService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity addNewRate(@RequestParam String name,
                                      @RequestParam double price){
        Rate rate = new Rate();
        rate.setName(name);
        rate.setPrice(price);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(getService().save(rate));
        } catch (EntryWithSameNameAlreadyExist ex){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(name);
        }
    }
}
