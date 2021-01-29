package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "debits")
public class Debit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "debited_money")
    private double debitedMoney;

    @Column(name = "previous_balance")
    private double previousBalance;

    @Column(name = "debit_date")
    private Date debitDate;

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
}
