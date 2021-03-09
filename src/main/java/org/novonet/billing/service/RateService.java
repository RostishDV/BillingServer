package org.novonet.billing.service;

import org.novonet.billing.models.Rate;
import org.novonet.billing.repo.RateRepository;

public class RateService extends AbstractService<Rate, RateRepository> {
    public RateService(RateRepository repository) {
        super(repository);
    }
}
