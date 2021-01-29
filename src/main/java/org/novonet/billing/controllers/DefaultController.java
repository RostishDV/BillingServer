package org.novonet.billing.controllers;

import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController {
    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/subscribers")
    public ResponseEntity getSubscribers(){
        return ResponseEntity.status(HttpStatus.OK).body(subscriberRepository.findAll());
    }

}