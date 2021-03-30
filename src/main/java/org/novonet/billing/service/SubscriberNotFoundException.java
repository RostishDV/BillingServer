package org.novonet.billing.service;

public class SubscriberNotFoundException extends Exception{
    public SubscriberNotFoundException(Long id){
        super("Subscriber with ID = " + id + " not found");
    }
}
