package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity{
    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "received_money")
    private double receivedMoney;

    @Column(name = "previous_balance")
    private double previousBalance;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", insertable = false, updatable = false)
    private Subscriber subscriber;

    public Payment() {
    }

    public Payment(long subscriberId, double receivedMoney, double previousBalance) {
        this.subscriberId = subscriberId;
        this.receivedMoney = receivedMoney;
        this.previousBalance = previousBalance;
        paymentDate = new Date();
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public double getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(double receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(double previousBalance) {
        this.previousBalance = previousBalance;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
