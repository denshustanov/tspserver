package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.Subscription;
import com.company.tspserver.entity.User;
import com.company.tspserver.repository.SubscriptionRepository;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    @Autowired
    protected DataManager dataManager;

    @Override
    public Subscription createSubscription(User subscriptionUser, User subscriber) {
        Subscription subscription = dataManager.create(Subscription.class);
        subscription.setSubscription(subscriptionUser);
        subscription.setSubscriber(subscriber);
        return dataManager.save(subscription);
    }

    @Override
    public void deleteSubscription(User subscriptionUser, User subscriber) {
        List<Subscription> subscriptions = findSubscriptions(subscriber, subscriptionUser);
        dataManager.remove(subscriptions);
    }

    @Override
    public List<Subscription> findAllUserSubscriptions(User user) {
        return dataManager.load(Subscription.class)
                .query("select s from Subscription s where s.subscriber = :user")
                .parameter("user", user)
                .list();
    }

    @Override
    public List<Subscription> findAllUserSubscribers(User user) {
        return dataManager.load(Subscription.class)
                .query("select s from Subscription  s where s.subscription = :user")
                .parameter("user", user)
                .list();
    }

    @Override
    public List<Subscription> findSubscriptions(User subscriber, User subscription) {
        return dataManager.load(Subscription.class)
                .query("select s from Subscription  s where s.subscription = :subscription and s.subscriber =:subscriber")
                .parameter("subscription", subscription)
                .parameter("subscriber", subscriber)
                .list();
    }

    @Override
    public void deleteAllUserRelatedSubscriptions(User user) {
        List<Subscription> subscriptions = dataManager.load(Subscription.class)
                .query("select s from Subscription s where s.subscription = :user or s.subscriber = :user")
                .list();
        dataManager.remove(subscriptions);
    }
}
