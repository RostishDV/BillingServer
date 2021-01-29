package org.novonet.billing.repo;

import org.novonet.billing.models.Subscriber2Rate;
import org.novonet.billing.models.Subscriber2RateId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Subscriber2RateRepository extends CrudRepository<Subscriber2Rate, Subscriber2RateId> {
}
