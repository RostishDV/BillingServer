package org.novonet.billing.repo;

import org.novonet.billing.models.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CommonRepository<Payment> {
}
