package com.company.tspserver.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "SUBSCRIPTION")
@Entity
public class Subscription {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "SUBSCRIPTION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User subscription;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "SUBSCRIBER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User subscriber;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getSubscription() {
        return subscription;
    }

    public void setSubscription(User subscription) {
        this.subscription = subscription;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }
}