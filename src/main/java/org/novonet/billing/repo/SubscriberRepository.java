package org.novonet.billing.repo;


import org.novonet.billing.models.Subscriber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {
    @Query(value = "select s from subscribers s where s.city = :city", nativeQuery = true)
    Iterable<Subscriber> findByCity(@Param("city") String city);
}
