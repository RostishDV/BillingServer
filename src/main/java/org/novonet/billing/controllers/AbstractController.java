package org.novonet.billing.controllers;

import org.novonet.billing.models.AbstractEntity;
import org.novonet.billing.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E> {

    private final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Iterable<E>> findAll() {
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        Optional<E> entity = service.findById(id);
        if(entity.isPresent()){
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(entity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
    }

    @Override
    public ResponseEntity<E> delete(E entity) {
        try {
            Optional<E> optionalE = service.findById(entity.getId());
            if (optionalE.isPresent()) {
                service.delete(entity);
                return ResponseEntity.status(HttpStatus.OK).body(entity);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entity);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public S getService() {
        return service;
    }
}