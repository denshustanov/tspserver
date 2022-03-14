package com.company.tspserver.security;

import com.company.tspserver.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securitydata.entity.RoleAssignmentEntity;

@ResourceRole(name="RegistrationRestRole", code = RegistrationRole.CODE)
public interface RegistrationRole {
    String CODE = "registration-rest-role";

    @EntityPolicy(
            entityClass = User.class,
            actions = EntityPolicyAction.CREATE
    )
    @EntityPolicy(
            entityClass = RoleAssignmentEntity.class,
            actions = EntityPolicyAction.CREATE
    )
    @EntityAttributePolicy(
            entityClass = User.class,
            attributes = {"username"},
            action = EntityAttributePolicyAction.VIEW
    )
    void role();
}
