package org.novonet.billing.repo;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.service.CommonService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingUserRepository extends CommonRepository<BillingUser>, CommonService<BillingUser> {
    Optional<BillingUser> findByName(String name);
}
