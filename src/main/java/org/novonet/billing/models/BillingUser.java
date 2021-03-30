package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingUser that = (BillingUser) o;
        return Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(privilege, that.privilege);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, privilege);
    }
}
