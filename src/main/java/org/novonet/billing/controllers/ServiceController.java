package org.novonet.billing.controllers;

import org.novonet.billing.models.Service;
import org.novonet.billing.repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/services")
    private ResponseEntity getAllRates(){
        Iterable<Service> services = serviceRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(services);
    }

    @GetMapping("/services/{id}")
    private  ResponseEntity getRateById(@PathVariable long id){
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(service);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

    @PostMapping("/services/")
    private ResponseEntity addNewRate(@RequestParam String name,
                                      @RequestParam Double price){
        try {
            if (serviceRepository.findByName(name).isPresent()){
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("this service already exist");
            }
            Service service = new Service();
            service.setName(name);
            service.setPrice(price);
            serviceRepository.save(service);
            return ResponseEntity.status(HttpStatus.OK).body(service.getId());
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }


    @DeleteMapping("/services/{id}")
    public ResponseEntity deleteSubscriberById(@PathVariable long id){
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            serviceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(service);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }
}
