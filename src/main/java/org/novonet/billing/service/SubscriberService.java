package org.novonet.billing.service;

import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriberService extends AbstractService<Subscriber, SubscriberRepository> {
    public SubscriberService(SubscriberRepository repository) {
        super(repository);
    }

    public Subscriber save(Subscriber subscriber){
        return repository.save(subscriber);
    }
}
