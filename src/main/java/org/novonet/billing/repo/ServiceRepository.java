package org.novonet.billing.repo;

import org.novonet.billing.models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends CommonRepository<Service> {
    Optional<Service> findByName(String name);
}
