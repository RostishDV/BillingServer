package org.novonet.billing.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Service extends AbstractEntity{
    @Column(length = 50)
    private String name;

    private double price;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "services")
    private List<Subscriber> subscribers;

    public Service() {
    }

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

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
