package org.novonet.billing.controllers;

import org.novonet.billing.models.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface CommonController<E extends AbstractEntity> {

    @PostMapping
    ResponseEntity save(@RequestBody E entity);

    @GetMapping
    ResponseEntity findAll();

    @GetMapping
    ResponseEntity findById(@RequestParam Long id);

    @DeleteMapping
    ResponseEntity deleteById(@PathVariable Long id);

    @DeleteMapping
    ResponseEntity delete(@RequestBody E entity);
}
