package org.novonet.billing.controllers;

import org.novonet.billing.models.AbstractEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface CommonController<E extends AbstractEntity> {

    @GetMapping
    ResponseEntity findAll();

    @GetMapping("/{id}")
    ResponseEntity findById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id);

    @DeleteMapping
    ResponseEntity delete(@RequestBody E entity);
}
