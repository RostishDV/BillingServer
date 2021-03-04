package org.novonet.billing.repo;

import org.novonet.billing.models.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CommonRepository<Application> {
}
