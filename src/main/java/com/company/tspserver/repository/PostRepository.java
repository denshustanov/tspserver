package com.company.tspserver.repository;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostAttachment;
import com.company.tspserver.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostRepository {
    List<Post> findPostByAuthor(User user);

    List<Post> findPostByText(String text);

    Post createPost(User user, String text, List<String> attachments, LocalDateTime publicatonDate);

    void deletePost(Post post);

    Post findPostById(UUID id);

    PostAttachment findPostAttachmentById(UUID id);
    List<Post> findAllPosts();

    void deleteAllUserPosts(User user);
}
