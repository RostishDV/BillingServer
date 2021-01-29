package org.novonet.billing.models;

import javax.persistence.*;

@Entity
public class BillingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private UserType status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getStatus() {
        return status;
    }

    public void setStatus(UserType status) {
        this.status = status;
    }
}
