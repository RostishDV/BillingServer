package org.novonet.billing.service;

public class SubscriberNotFoundException extends Exception {
    public SubscriberNotFoundException(Long id){
        super("Subscriber with id " + id + " not found");
    }
}
