package org.novonet.billing.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rates")
public class Rate extends AbstractEntity{
    @Column(length = 50)
    private String name;

    private double price;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "rates")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Double.compare(rate.price, price) == 0 && Objects.equals(name, rate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
