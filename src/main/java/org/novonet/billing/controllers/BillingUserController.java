package org.novonet.billing.controllers;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.repo.BillingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BillingUserController {
    @Autowired
    private BillingUserRepository billingUserRepository;

    @GetMapping("/billingUsers/")
    private ResponseEntity getAllRates(){
        Iterable<BillingUser> billingUsers = billingUserRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(billingUsers);
    }

    @GetMapping("/billingUsers/{id}")
    private  ResponseEntity getRateById(@PathVariable long id){
        Optional<BillingUser> billingUsers = billingUserRepository.findById(id);
        if (billingUsers.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(billingUsers);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

    @PostMapping("/billingUsers/")
    private ResponseEntity addNewRate(@RequestParam String name,
                                      @RequestParam String password,
                                      @RequestParam String privilege){
        try {
            BillingUser billingUser = new BillingUser();
            billingUser.setName(name);
            billingUser.setName(password);
            billingUser.setPrivilege(privilege);
            return ResponseEntity.status(HttpStatus.OK).body(billingUser.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }


    @DeleteMapping("/services/{id}")
    public ResponseEntity deleteSubscriberById(@PathVariable long id){
        Optional<BillingUser> billingUser = billingUserRepository.findById(id);
        if (billingUser.isPresent()) {
            billingUserRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(billingUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
