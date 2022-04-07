package com.company.tspserver.service;

import com.company.tspserver.entity.User;
import com.company.tspserver.entity.UserFCMToken;
import com.company.tspserver.service.impl.fcm.PushNotifyConf;

import java.util.List;

public interface FCMService {
    void sendPersonal(PushNotifyConf conf, User user);
    UserFCMToken createToken(String username, String token);
    void deleteToken(UserFCMToken token);
    UserFCMToken findToken(String username, String token);
    List<UserFCMToken> findTokensByUsername(String username);
}
