package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostLike;
import com.company.tspserver.entity.User;
import com.company.tspserver.repository.PostLikeRepository;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class PostLikeRepositoryImpl implements PostLikeRepository {
    @Autowired
    protected DataManager dataManager;

    @Override
    public PostLike createPostLike(User user, Post post) {
        PostLike postLike = dataManager.create(PostLike.class);
        postLike.setPost(post);
        postLike.setUser(user);
        return dataManager.save(postLike);
    }

    @Override
    public List<PostLike> findAllLikesByPost(Post post) {
        return dataManager.load(PostLike.class)
                .condition(PropertyCondition.equal("post", post))
                .list();
    }

    @Override
    public void deletePostLike(PostLike postLike) {
        dataManager.remove(postLike);
    }
}
