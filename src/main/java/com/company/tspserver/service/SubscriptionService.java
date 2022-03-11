package com.company.tspserver.service;

import com.company.tspserver.entity.Subscription;
import com.company.tspserver.entity.User;

import java.util.List;

public interface SubscriptionService {
    Subscription subscribeToUser(String subscriberUsername, String subscriptionUsername);
    void deleteSubscription(String subscriberUser, String subscriptionUsername);
    List<Subscription> findAllUserSubscriptions(String username);
    List<Subscription> findAllUserSubscribers(String username);
    int calculateUserSubscriptions(String username);
    int calculateUserSubscribers(String username);
    boolean checkSubscription(String subscriberUsername, String SubscriptionUsername);
    boolean checkSubscription(User subscriber, User subscription);
}
