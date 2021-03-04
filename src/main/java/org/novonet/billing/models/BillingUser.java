package org.novonet.billing.models;

import javax.persistence.*;

@Entity
@Table(name = "billing_users")
public class BillingUser extends AbstractEntity{
    private String name;

    private String password;

    private String privilege;

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

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
