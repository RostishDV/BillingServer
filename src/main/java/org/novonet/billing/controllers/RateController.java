package org.novonet.billing.controllers;

import org.novonet.billing.models.Rate;
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

    private ResponseEntity addNewRate(@RequestParam String name,
                                      @RequestParam double price){
        Rate rate = new Rate();
        rate.setName(name);
        rate.setPrice(price);
        try {
            getService().save(rate);
            return ResponseEntity.status(HttpStatus.OK).body(rate.getId());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ex.getStackTrace());
        }
    }
}
