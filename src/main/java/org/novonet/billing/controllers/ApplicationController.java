package org.novonet.billing.controllers;

import org.novonet.billing.models.Application;
import org.novonet.billing.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationController extends AbstractController<Application, ApplicationService> {

    public ApplicationController(ApplicationService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity save(@RequestParam long subscriberId, @RequestParam String status,
                               @RequestParam String title, @RequestParam String description) {
        if (status == null || status.equals("")){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("status mast be not empty");
        }
        if (title == null || title.equals("")){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("title mast be not empty");
        }
        if (description == null || description.equals("")){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("title mast be not empty");
        }
        Application application = new Application();
        application.setSubscriberId(subscriberId);
        application.setStatus(status);
        application.setTitle(title);
        application.setDescription(description);
        getService().save(application);
        return ResponseEntity.status(HttpStatus.OK).body(application);
    }
}
