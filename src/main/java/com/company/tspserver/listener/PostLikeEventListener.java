package com.company.tspserver.listener;

import com.company.tspserver.entity.PostLike;
import com.company.tspserver.event.PostLikeEvent;
import com.company.tspserver.service.FCMService;
import com.company.tspserver.service.impl.fcm.PushNotifyConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class PostLikeEventListener implements ApplicationListener<PostLikeEvent> {
    @Autowired
    private FCMService fcmService;

    @Override
    public void onApplicationEvent(PostLikeEvent event) {
        PostLike postLike = event.getPostLike();
        if(!postLike.getUser().getUsername().equals(postLike.getPost().getAuthor().getUsername())) {
            PushNotifyConf conf = new PushNotifyConf();
            conf.setBody("User " + postLike.getUser().getUsername() + " liked your post!");
            conf.setTitle("Posts");
            Logger.getLogger(PostLikeEventListener.class.getName()).info("LIKE");
            fcmService.sendPersonal(conf, postLike.getPost().getAuthor());
        }
    }
}
