package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.User;
import com.company.tspserver.entity.UserFCMToken;
import com.company.tspserver.repository.UserFCMTokenRepository;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFCMTokenRepositoryImpl implements UserFCMTokenRepository {
    @Autowired
    private DataManager dataManager;

    @Override
    public UserFCMToken createToken(String username, String token) {
        UserFCMToken fcmToken = dataManager.create(UserFCMToken.class);
        fcmToken.setUsername(username);
        fcmToken.setToken(token);
        try{
            findToken(username, token);
        } catch (Exception e) {
            return dataManager.save(fcmToken);
        }
        return null;
    }

    @Override
    public void deleteToken(UserFCMToken fcmToken) {
        dataManager.remove(fcmToken);
    }

    @Override
    public UserFCMToken findToken(String username, String token) {
        return dataManager.load(UserFCMToken.class)
                .condition(
                        LogicalCondition.and(
                                PropertyCondition.equal("username", username),
                                PropertyCondition.equal("token", token)
                        ))
                .one();
    }

    @Override
    public List<UserFCMToken> findTokensByUsername(String username) {
        return dataManager.load(UserFCMToken.class)
                .condition(PropertyCondition.equal("username", username))
                .list();
    }
}
