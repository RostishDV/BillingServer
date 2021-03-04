package org.novonet.billing.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rates")
public class Rate extends AbstractEntity{
    @Column(length = 50)
    private String name;

    private double price;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subscribers_to_rates",
            joinColumns = {@JoinColumn(name = "rate_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")}
    )
    private List<Subscriber> subscribers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
