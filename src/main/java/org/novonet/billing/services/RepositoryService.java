package org.novonet.billing.services;

import org.novonet.billing.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {
    @Autowired
    private static volatile ApplicationRepository applicationRepository;

    @Autowired
    private static volatile BillingUserRepository billingUserRepository;

    @Autowired
    private static volatile DebitRepository debitRepository;

    @Autowired
    private static volatile PaymentRepository paymentRepository;

    @Autowired
    private static volatile RateRepository rateRepository;

    @Autowired
    private static volatile ServiceRepository serviceRepository;

    @Autowired
    private static volatile Subscriber2RateRepository subscriber2RateRepository;

    @Autowired
    private static volatile Subscriber2ServiceRepository subscriber2ServiceRepository;

    @Autowired
    private static volatile SubscriberRepository subscriberRepository;

    public static ApplicationRepository getApplicationRepository() {
        return applicationRepository;
    }

    public static BillingUserRepository getBillingUserRepository() {
        return billingUserRepository;
    }

    public static DebitRepository getDebitRepository() {
        return debitRepository;
    }

    public static PaymentRepository getPaymentRepository() {
        return paymentRepository;
    }

    public static RateRepository getRateRepository() {
        return rateRepository;
    }

    public static ServiceRepository getServiceRepository() {
        return serviceRepository;
    }

    public static Subscriber2RateRepository getSubscriber2RateRepository() {
        return subscriber2RateRepository;
    }

    public static Subscriber2ServiceRepository getSubscriber2ServiceRepository() {
        return subscriber2ServiceRepository;
    }

    public static SubscriberRepository getSubscriberRepository() {
        return subscriberRepository;
    }
}
