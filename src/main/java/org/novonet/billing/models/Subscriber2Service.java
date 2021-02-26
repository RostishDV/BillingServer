package org.novonet.billing.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "subscribers_to_services")
public class Subscriber2Service {

    @EmbeddedId
    private Subscriber2ServiceId subscriber2serviceId;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(insertable = false, updatable = false)
//    private Subscriber subscriber;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(insertable = false, updatable = false)
//    private Service service;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscriber2Service(Subscriber2ServiceId subscriber2ServiceId) {
        this.subscriber2serviceId = subscriber2ServiceId;
    }

    public Subscriber2ServiceId getSubscriber2ServiceId() {
        return subscriber2serviceId;
    }

    public void setSubscriber2ServiceId(Subscriber2ServiceId subscriber2serviceId) {
        this.subscriber2serviceId = subscriber2serviceId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
