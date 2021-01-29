package org.novonet.billing.repo;


import org.novonet.billing.models.Subscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {
}
