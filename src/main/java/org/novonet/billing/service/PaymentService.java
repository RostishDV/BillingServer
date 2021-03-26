package org.novonet.billing.service;

import org.novonet.billing.models.Payment;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.PaymentRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService extends AbstractService<Payment, PaymentRepository> {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public PaymentService(PaymentRepository repository) {
        super(repository);
    }

    public Payment save(Payment payment) throws SubscriberNotFoundException{
        Optional<Subscriber> subscriberOptional = subscriberRepository.findById(payment.getSubscriberId());
        if (subscriberOptional.isPresent()) {
            Subscriber subscriber = subscriberOptional.get();
            subscriber.addPayment(payment);
            subscriberRepository.save(subscriber);
            return payment;
        }
        throw new SubscriberNotFoundException(payment.getSubscriberId());
    }
}
