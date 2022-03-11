package com.company.tspserver.security;

import com.company.tspserver.entity.*;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;

@ResourceRole(name = "Base User Rest Role", code = BaseUserRestRole.CODE)
public interface BaseUserRestRole {
    String CODE = "base-user-rest-role";

    @EntityPolicy(
            entityClass = User.class,
            actions = EntityPolicyAction.ALL
    )
    @EntityPolicy(
            entityClass = Post.class,
            actions = EntityPolicyAction.ALL
    )
    @EntityPolicy(
            entityClass = PostComment.class,
            actions = EntityPolicyAction.ALL
    )
    @EntityPolicy(
            entityClass = PostLike.class,
            actions = EntityPolicyAction.ALL
    )
    @EntityPolicy(
            entityClass = PostAttachment.class,
            actions = EntityPolicyAction.ALL
    )
    @EntityPolicy(
            entityClass = Subscription.class,
            actions = EntityPolicyAction.ALL
    )
    void user();
}
