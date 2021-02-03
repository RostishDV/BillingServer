package org.novonet.billing.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class Subscriber2RateId implements Serializable {

    @Column(name = "subscriber_id")
    private Long subscriberId;

    @Column(name = "rate_id")
    private Long rateId;

    public Subscriber2RateId() {
    }

    public Subscriber2RateId(long subscriberId, long rateId) {
        this.subscriberId = subscriberId;
        this.rateId = rateId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    @Override
    public int hashCode() {
        long code = subscriberId.hashCode() + rateId.hashCode();
        return (int) code;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Subscriber2RateId)){
            return false;
        }
        Subscriber2RateId other = (Subscriber2RateId) obj;
        boolean subscriberIdEquals = this.subscriberId.equals(other.subscriberId);
        boolean rateIdEquals = this.rateId.equals((other.rateId));
        return subscriberIdEquals && rateIdEquals;
    }
}
