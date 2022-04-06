package com.company.tspserver.service.impl.fcm;

import com.company.tspserver.entity.User;
import com.company.tspserver.entity.UserFCMToken;
import com.company.tspserver.repository.UserFCMTokenRepository;
import com.company.tspserver.service.FCMService;
import com.company.tspserver.service.UserService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FCMServiceImpl implements FCMService {
    @Autowired
    protected UserService userService;

    @Autowired
    protected UserFCMTokenRepository userFCMTokenRepository;

    @Value("${fcm.service-account-file}")
    String serviceAccountFilePath;

    @PostConstruct
    protected void initFirebase(){
        Path p = Paths.get(serviceAccountFilePath);
        try(InputStream serviceAccount = Files.newInputStream(p)){
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(firebaseOptions);
        } catch (IOException e) {
            Logger.getLogger(FCMServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void sendPersonal(PushNotifyConf conf, User user) {
        List<UserFCMToken> tokenList = userFCMTokenRepository.findTokensByUsername(user.getUsername());
        AndroidConfig androidConfig = AndroidConfig
                .builder()
                .setPriority(AndroidConfig.Priority.HIGH)
                .build();
        JsonObject notifiaction = new JsonObject();
        notifiaction.addProperty("title", conf.getTitle());
        notifiaction.addProperty("body", conf.getBody());

        for(UserFCMToken token: tokenList) {
            Message message = Message.builder()
                    .setToken(token.getToken())
                    .setWebpushConfig(WebpushConfig.builder().build())
                    .setAndroidConfig(androidConfig)
                    .putData("notification", notifiaction.toString())
                    .build();
            try{
                String response = FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                Logger.getLogger(FCMServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public UserFCMToken createToken(String username, String token) {
        return userFCMTokenRepository.createToken(username, token);
    }

    @Override
    public void deleteToken(String username, String token) {
        userFCMTokenRepository.deleteToken(username, token);
    }

    @Override
    public UserFCMToken findToken(String username, String token) {
        return userFCMTokenRepository.findToken(username, token);
    }

    @Override
    public List<UserFCMToken> findTokensByUsername(String username) {
        return userFCMTokenRepository.findTokensByUsername(username);
    }
}
