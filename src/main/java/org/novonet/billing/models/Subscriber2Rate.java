package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscribers_to_rates")
public class Subscriber2Rate {

    @EmbeddedId
    private Subscriber2RateId subscriber2RateId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(insertable = false, updatable = false)
    private Subscriber subscriber;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscriber2Rate(Subscriber2RateId subscriber2RateId) {
        this.subscriber2RateId = subscriber2RateId;
        subscriptionDate = new Date();
    }

    public Subscriber2Rate() {
        subscriptionDate = new Date();
    }

    public Subscriber2RateId getSubscriber2RateId() {
        return subscriber2RateId;
    }

    public void setSubscriber2RateId(Subscriber2RateId subscriber2RateId) {
        this.subscriber2RateId = subscriber2RateId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
