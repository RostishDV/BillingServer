package org.novonet.billing.repo;

import net.bytebuddy.TypeCache;
import org.novonet.billing.models.Debit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitRepository extends CrudRepository<Debit, Long> {

    //todo: write this query
    @Query(value = "SELECT d FROM debits d " +
            "INNER JOIN subscribers s ON d.subscriber_id = s.id",
            nativeQuery = true)
    Iterable<Debit> findLatestDebitForEachSubscriber(Sort sort);
}
