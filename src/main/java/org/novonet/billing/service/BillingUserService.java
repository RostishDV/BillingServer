package org.novonet.billing.service;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.repo.BillingUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingUserService extends AbstractService<BillingUser, BillingUserRepository> {
    public BillingUserService(BillingUserRepository service) {
        super(service);
    }

    public BillingUser save(BillingUser billingUser) throws EntryWithSameNameAlreadyExist {
        if (repository.findByName(billingUser.getName()).isPresent()){
            throw new EntryWithSameNameAlreadyExist(billingUser.getName());
        }
        return repository.save(billingUser);
    }

    public Optional<BillingUser> findByName(String name) {
        return super.repository.findByName(name);
    }
}
