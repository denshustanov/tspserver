package com.company.tspserver.dto;

import com.company.tspserver.entity.Subscription;

public class SubscriptionDTO {
    private String subscriberUsername;
    private String subscriptionUsername;

    public SubscriptionDTO(Subscription subscription){
        this.subscriberUsername = subscription.getSubscriber().getUsername();
        this.subscriptionUsername = subscription.getSubscription().getUsername();
    }

    public String getSubscriberUsername() {
        return subscriberUsername;
    }

    public void setSubscriberUsername(String subscriberUsername) {
        this.subscriberUsername = subscriberUsername;
    }

    public String getSubscriptionUsername() {
        return subscriptionUsername;
    }

    public void setSubscriptionUsername(String subscriptionUsername) {
        this.subscriptionUsername = subscriptionUsername;
    }
}
