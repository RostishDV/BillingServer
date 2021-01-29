package org.novonet.billing.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "subscribers_to_rates")
public class Subscriber2Rate {

    @EmbeddedId
    private Subscriber2RateId subscriber2RateId;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscriber2RateId getSubscriber2RateId() {
        return subscriber2RateId;
    }

    public void setSubscriber2RateId(Subscriber2RateId subscriber2RateId) {
        this.subscriber2RateId = subscriber2RateId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
