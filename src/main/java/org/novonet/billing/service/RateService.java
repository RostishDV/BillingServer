package org.novonet.billing.service;

import org.novonet.billing.models.Rate;
import org.novonet.billing.repo.RateRepository;
import org.springframework.stereotype.Service;

@Service
public class RateService extends AbstractService<Rate, RateRepository> {
    public RateService(RateRepository repository) {
        super(repository);
    }

    public Rate save(Rate rate) throws EntryWithSameNameAlreadyExist {
        if (repository.findByName(rate.getName()).isPresent()){
            throw new EntryWithSameNameAlreadyExist(rate.getName());
        }
        return repository.save(rate);
    }
}
