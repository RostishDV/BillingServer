package org.novonet.billing.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class BillingUser2Role {
    @EmbeddedId
    private BillingUser2RoleId billingUser2RoleId;

    public BillingUser2RoleId getBillingUser2RoleId() {
        return billingUser2RoleId;
    }

    public void setBillingUser2RoleId(BillingUser2RoleId billingUser2RoleId) {
        this.billingUser2RoleId = billingUser2RoleId;
    }
}
