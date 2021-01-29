package org.novonet.billing.repo;

import org.novonet.billing.models.Debit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitRepository extends CrudRepository<Debit, Long> {
}
