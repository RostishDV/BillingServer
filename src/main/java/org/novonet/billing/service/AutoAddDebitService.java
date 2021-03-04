package org.novonet.billing.service;

import org.hibernate.SessionFactory;
import org.novonet.billing.models.Subscriber;
import org.novonet.billing.repo.DebitRepository;
import org.novonet.billing.repo.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoAddDebitService {
//    @Autowired
//    private SubscriberRepository subscriberRepository;
//
//    @Autowired
//    private DebitRepository debitRepository;
//
//
////    todo: uncomment in testing
//    @Scheduled(cron = "1 0 * * ?")
////    todo: uncomment in production
////    @Scheduled(cron = "0 1 * * ?")
//    public void addDebits(){
//        //todo: write something to work like
//        // SELECT * FROM debits ORDER BY debit_date LIMIT 1 GROUP BY subscriberId
//        Iterable<Subscriber> subscribers = subscriberRepository.findAll();
//        for(Subscriber subscriber : subscribers){
//
//        }
//
//    }
}
