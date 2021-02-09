package org.novonet.billing.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30)
    private String surname, name, patronymic;

    @Column(length = 25)
    private String city;

    @Column(length = 50)
    private String street;

    private Integer house;

    private Integer building;

    private Integer apartment;

    private Long phone;

    private Double balance;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subscribers_to_rates",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "rate_id")}
    )
    private List<Rate> rates;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subscribers_to_services",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<Service> services;

    @OneToMany(mappedBy = "subscriber", targetEntity = Application.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Application> applications;

    @OneToMany(mappedBy = "subscriber",targetEntity = Debit.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Debit> debits;

    @OneToMany(mappedBy = "subscriber", targetEntity = Payment.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Payment> payments;



    public Subscriber() {
    }

    public Subscriber(String surname, String name, String patronymic, String city, String street, Integer house, Integer building, Integer apartment, Long phone) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.city = city;
        this.street = street;
        this.house = house;
        this.building = building;
        this.apartment = apartment;
        this.phone = phone;
        balance = 0.;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Rate> getRate() {
        return rates;
    }

    public void setRate(List<Rate> rates) {
        this.rates = rates;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setService(List<Service> services) {
        this.services = services;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addApplication(Application application){
        applications.add(application);
    }

    public void removeApplication(Application application){
        applications.remove(application);
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void addDebit(Debit debit){
        debits.add(debit);
    }

    public void removeDebit(Debit debit){
        debits.remove(debit);
    }

    public List<Debit> getDebits() {
        return debits;
    }

    public void setDebits(List<Debit> debits) {
        this.debits = debits;
    }

    public void addPayment(Payment payment){
        payments.add(payment);
    }

    public void removePayment(Payment payment){
        payments.remove(payment);
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}
