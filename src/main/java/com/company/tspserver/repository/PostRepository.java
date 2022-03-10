package com.company.tspserver.repository;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.User;

import java.util.List;

public interface PostRepository {
    List<Post> findPostByAuthorUsername(String username);

    List<Post> findPostByText(String text);

    Post createPost(User user, String text, List<byte[]> attachments);

    void deletePost(Post post);
}
