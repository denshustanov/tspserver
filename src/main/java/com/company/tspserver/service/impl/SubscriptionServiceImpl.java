package com.company.tspserver.service.impl;

import com.company.tspserver.entity.Subscription;
import com.company.tspserver.entity.User;
import com.company.tspserver.repository.SubscriptionRepository;
import com.company.tspserver.repository.UserRepository;
import com.company.tspserver.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public Subscription subscribeToUser(String subscriberUsername, String subscriptionUsername) {
        if(Objects.equals(subscriberUsername, subscriptionUsername)){
            return null;
        }
        User subscriber = userRepository.findUserByUsername(subscriberUsername);
        User subscription = userRepository.findUserByUsername(subscriptionUsername);

        if(!checkSubscription(subscriber, subscription)){
            return subscriptionRepository.createSubscription(subscription, subscriber);
        }
        return null;
    }

    @Override
    public void deleteSubscription(String subscriberUsername, String subscriptionUsername) {
        User subscriber = userRepository.findUserByUsername(subscriberUsername);
        User subscription = userRepository.findUserByUsername(subscriptionUsername);

        subscriptionRepository.deleteSubscription(subscription, subscriber);
    }

    @Override
    public List<Subscription> findAllUserSubscriptions(String username) {
        User user = userRepository.findUserByUsername(username);

        return subscriptionRepository.findAllUserSubscriptions(user);
    }

    @Override
    public List<Subscription> findAllUserSubscribers(String username) {
        User user = userRepository.findUserByUsername(username);

        return subscriptionRepository.findAllUserSubscribers(user);
    }

    @Override
    public int calculateUserSubscriptions(String username) {
        User user = userRepository.findUserByUsername(username);
        List<Subscription> subscriptions =  subscriptionRepository.findAllUserSubscriptions(user);
        return subscriptions.size();
    }

    @Override
    public int calculateUserSubscribers(String username) {
        return findAllUserSubscribers(username).size();
    }

    @Override
    public boolean checkSubscription(String subscriberUsername, String subscriptionUsername) {
        User subscriber = userRepository.findUserByUsername(subscriberUsername);
        User subscription = userRepository.findUserByUsername(subscriptionUsername);

        return !subscriptionRepository.findSubscriptions(subscriber, subscription).isEmpty();
    }

    @Override
    public boolean checkSubscription(User subscriber, User subscription) {
        return !subscriptionRepository.findSubscriptions(subscriber, subscription).isEmpty();
    }
}
