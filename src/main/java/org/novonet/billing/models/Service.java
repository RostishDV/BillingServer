package org.novonet.billing.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Double.compare(service.price, price) == 0 && Objects.equals(name, service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
