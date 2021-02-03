package org.novonet.billing.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Subscriber2ServiceId implements Serializable {

    @Column(name = "subscriber_id")
    private Long subscriberId;

    @Column(name = "service_id")
    private Long serviceId;

    public Subscriber2ServiceId() {
    }

    public Subscriber2ServiceId(long subscriberId, long serviceId) {
        this.subscriberId = subscriberId;
        this.serviceId = serviceId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int hashCode() {
        long code = subscriberId.hashCode() + serviceId.hashCode();
        return (int) code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Subscriber2ServiceId)){
            return false;
        }
        Subscriber2ServiceId other = (Subscriber2ServiceId) obj;
        boolean subscriberIdEquals = this.subscriberId.equals(other.subscriberId);
        boolean serviceIdEquals = this.serviceId.equals(other.serviceId);
        return subscriberIdEquals && serviceIdEquals;
    }
}
