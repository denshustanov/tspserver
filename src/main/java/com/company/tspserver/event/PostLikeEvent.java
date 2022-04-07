package com.company.tspserver.event;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostLike;
import org.springframework.context.ApplicationEvent;

public class PostLikeEvent extends ApplicationEvent {
    private PostLike postLike;

    public PostLikeEvent(PostLike postLike) {
        super(postLike);
        this.postLike = postLike;
    }

    public PostLike getPostLike() {
        return postLike;
    }
}
