package org.novonet.billing.controllers;

import org.novonet.billing.models.AbstractEntity;
import org.novonet.billing.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E> {

    private final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity save(@RequestBody E entity) {
        E createdEntity;
        try {
            createdEntity = service.save(entity);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("sorry something went wrong");
        }
        return ResponseEntity.status(HttpStatus.OK).body(createdEntity);
    }

    @Override
    public ResponseEntity findAll() {
        Iterable<E> entities = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(entities);
    }

    @Override
    public ResponseEntity findById(Long id) {
        try {
            Optional<E> optionalE = service.findById(id);
            if (optionalE.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(optionalE.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("sorry, something went wrong");
        }
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        if(service.findById(id).isPresent()){
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

    @Override
    public ResponseEntity delete(E entity) {
        service.delete(entity);
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }
}