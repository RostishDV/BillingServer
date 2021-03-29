package org.novonet.billing.controllers;

import org.novonet.billing.models.Service;
import org.novonet.billing.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController extends AbstractController<Service, ServiceService> {

    protected ServiceController(ServiceService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity addNewService(@RequestParam String name,
                                        @RequestParam Double price){
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(getService().save(service));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(name);
        }
    }
}
