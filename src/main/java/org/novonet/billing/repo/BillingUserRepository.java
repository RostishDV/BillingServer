package org.novonet.billing.repo;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingUserRepository extends CommonRepository<BillingUser> {
    Optional<BillingUser> findByName(String name);
}
