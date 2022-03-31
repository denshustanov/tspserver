package com.company.tspserver.repository;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostLike;
import com.company.tspserver.entity.User;

import java.util.List;

public interface PostLikeRepository {
    PostLike createPostLike(User user, Post post);
    List<PostLike> findAllLikesByPost(Post post);
    void deletePostLike(PostLike postLike);
    PostLike findPostLike(User user, Post post);
}
