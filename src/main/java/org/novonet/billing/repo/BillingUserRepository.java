package org.novonet.billing.repo;

import org.novonet.billing.models.BillingUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingUserRepository extends CrudRepository<BillingUser, Long> {
    BillingUser findByUsername(String username);
}
