package org.novonet.billing.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Subscriber2ServiceId implements Serializable {

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "service_id")
    private long serviceId;

    public Subscriber2ServiceId(long subscriberId, long serviceId) {
        this.subscriberId = subscriberId;
        this.serviceId = serviceId;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
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
