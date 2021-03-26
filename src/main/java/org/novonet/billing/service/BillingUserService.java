package org.novonet.billing.service;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.repo.BillingUserRepository;
import org.springframework.stereotype.Service;

@Service
public class BillingUserService extends AbstractService<BillingUser, BillingUserRepository> {
    public BillingUserService(BillingUserRepository service) {
        super(service);
    }

    public BillingUser save(BillingUser billingUser){
        return repository.save(billingUser);
    }
}
