package org.novonet.billing.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class Subscriber2RateId implements Serializable {

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "rate_id")
    private long rateId;

    public Subscriber2RateId() {
    }

    public Subscriber2RateId(long subscriberId, long rateId) {
        this.subscriberId = subscriberId;
        this.rateId = rateId;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public long getRateId() {
        return rateId;
    }

    public void setRateId(long rateId) {
        this.rateId = rateId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
