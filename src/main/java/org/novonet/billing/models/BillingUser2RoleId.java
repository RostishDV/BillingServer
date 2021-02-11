package org.novonet.billing.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BillingUser2RoleId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        long hash = Long.hashCode(userId) + Long.hashCode(roleId);
        return (int) hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof BillingUser2RoleId)){
            return false;
        }

        BillingUser2RoleId other = (BillingUser2RoleId) obj;
        boolean userIdEquals = this.userId.equals(other.userId);
        boolean roleIdEquals = this.roleId.equals(other.roleId);
        return userIdEquals && roleIdEquals;
    }
}
