package com.company.tspserver.repository;

import com.company.tspserver.entity.Subscription;
import com.company.tspserver.entity.User;

import java.util.List;

public interface SubscriptionRepository {
    Subscription createSubscription(User subscription, User subscriber);
    void deleteSubscription(User subscription, User subscriber);
    List<Subscription> findAllUserSubscriptions(User user);
    List<Subscription> findAllUserSubscribers(User user);
    List<Subscription> findSubscriptions(User subscriber, User subscription);
}
