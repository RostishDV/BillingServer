package org.novonet.billing.repo;

import org.novonet.billing.models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
}
