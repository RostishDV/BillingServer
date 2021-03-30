package org.novonet.billing.controllers;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.service.BillingUserService;
import org.novonet.billing.service.EntryWithSameNameAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/billingUsers")
public class BillingUserController extends AbstractController<BillingUser, BillingUserService> {

    public BillingUserController(BillingUserService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity addNewBillingUser(@RequestParam String name,
                                      @RequestParam String password,
                                      @RequestParam String privilege){
            BillingUser billingUser = new BillingUser();
            billingUser.setName(name);
            billingUser.setPassword(password);
            billingUser.setPrivilege(privilege);
        try {
            getService().save(billingUser);
            return ResponseEntity.status(HttpStatus.OK).body(billingUser);
        } catch (EntryWithSameNameAlreadyExist entryWithSameNameAlreadyExist) {
            entryWithSameNameAlreadyExist.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(name);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getBillingUserByName(@PathVariable String name){
        Optional<BillingUser> billingUser = getService().findByName(name);
        if (billingUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(billingUser.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(name);
    }
}
