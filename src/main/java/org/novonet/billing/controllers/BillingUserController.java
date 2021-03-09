package org.novonet.billing.controllers;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.service.BillingUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billingUsers")
public class BillingUserController extends AbstractController<BillingUser, BillingUserService> {

    public BillingUserController(BillingUserService service) {
        super(service);
    }

    @PostMapping()
    private ResponseEntity addNewBillingUser(@RequestParam String name,
                                      @RequestParam String password,
                                      @RequestParam String privilege){
            BillingUser billingUser = new BillingUser();
            billingUser.setName(name);
            billingUser.setName(password);
            billingUser.setPrivilege(privilege);
            getService().save(billingUser);
            return ResponseEntity.status(HttpStatus.OK).body(billingUser.getId());
    }
}
