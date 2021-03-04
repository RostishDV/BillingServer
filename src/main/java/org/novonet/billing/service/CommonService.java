package org.novonet.billing.service;

import org.novonet.billing.models.AbstractEntity;

import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {

    E save(E entity);

    Optional<E> findById(Long id);

    Iterable<E> findAll();

    void deleteById(Long id);

    void delete(E entity);
}
