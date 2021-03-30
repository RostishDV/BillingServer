package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Column
    private String reason;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", referencedColumnName = "id", insertable = false, updatable = false)
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debit debit = (Debit) o;
        return subscriberId == debit.subscriberId && Double.compare(debit.debitedMoney, debitedMoney) == 0 && Double.compare(debit.previousBalance, previousBalance) == 0 && Objects.equals(debitDate, debit.debitDate) && Objects.equals(reason, debit.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriberId, debitedMoney, previousBalance, debitDate, reason);
    }
}
