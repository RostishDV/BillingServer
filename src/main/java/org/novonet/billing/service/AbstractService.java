package org.novonet.billing.service;

import org.novonet.billing.models.AbstractEntity;
import org.novonet.billing.repo.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity,
        R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }
}
