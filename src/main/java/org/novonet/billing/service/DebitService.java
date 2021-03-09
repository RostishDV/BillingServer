package org.novonet.billing.service;

import org.novonet.billing.models.Debit;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.DebitRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Optional;

public class DebitService extends AbstractService<Debit, DebitRepository> {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public DebitService(DebitRepository repository) {
        super(repository);
    }

    public Debit save(Debit debit) throws SubscriberNotFoundException {
        Optional<Subscriber> subscriberOptional = subscriberRepository.findById(debit.getSubscriberId());
        if (subscriberOptional.isPresent()) {
            Subscriber subscriber = subscriberOptional.get();
            subscriber.addDebit(debit);
            subscriberRepository.save(subscriber);
            return debit;
        }
        throw new SubscriberNotFoundException(debit.getSubscriberId());
    }
}
