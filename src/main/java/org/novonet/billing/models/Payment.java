package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "received_money")
    private double receivedMoney;

    @Column(name = "previous_balance")
    private double previousBalance;

    @Column(name = "payment_date")
    private Date paymentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
