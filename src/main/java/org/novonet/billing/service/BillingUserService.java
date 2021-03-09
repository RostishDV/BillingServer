package org.novonet.billing.service;

import org.novonet.billing.controllers.AbstractController;
import org.novonet.billing.models.BillingUser;
import org.novonet.billing.repo.BillingUserRepository;

public class BillingUserService extends AbstractService<BillingUser, BillingUserRepository> {
    protected BillingUserService(BillingUserRepository service) {
        super(service);
    }

    public BillingUser save(BillingUser billingUser){
        return repository.save(billingUser);
    }
}
