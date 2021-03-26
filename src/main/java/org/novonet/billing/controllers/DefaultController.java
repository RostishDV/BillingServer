package org.novonet.billing.controllers;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.models.Role;
import org.novonet.billing.repo.BillingUserRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class DefaultController {
    @Autowired
    private BillingUserRepository billingUserRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
//
//    @PostMapping("/registration")
//    public ResponseEntity addUser(@RequestParam String username,
//                                  @RequestParam String password){
//        BillingUser user = billingUserRepository.findByUsername(username);
//        if (user != null) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Already exist");
//        }
//        user = new BillingUser();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setRoles(Collections.singleton(Role.USER));
//        billingUserRepository.save(user);
//        return ResponseEntity.status(HttpStatus.OK).body("successfully register " + username);
//    }
}