package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "debits")
public class Debit extends AbstractEntity{

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "debited_money")
    private double debitedMoney;

    @Column(name = "previous_balance")
    private double previousBalance;

    @Column(name = "debit_date")
    private Date debitDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", insertable = false, updatable = false)
    private Subscriber subscriber;

    public Debit() {
    }

    public Debit(long subscriberId, double debitedMoney, double previousBalance) {
        this.subscriberId = subscriberId;
        this.debitedMoney = debitedMoney;
        this.previousBalance = previousBalance;
        debitDate = new Date();
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public double getDebitedMoney() {
        return debitedMoney;
    }

    public void setDebitedMoney(double debitedMoney) {
        this.debitedMoney = debitedMoney;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(double previousBalance) {
        this.previousBalance = previousBalance;
    }

    public Date getDebitDate() {
        return debitDate;
    }

    public void setDebitDate(Date debitDate) {
        this.debitDate = debitDate;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
