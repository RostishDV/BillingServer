package org.novonet.billing.repo;

import org.novonet.billing.models.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {

    Optional<Rate> findByName(String name);
}
