package com.company.tspserver.service;

import com.company.tspserver.dto.PostDTO;
import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostAttachment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> findPostByAuthorUsername(String username);
    List<Post> findPostsByContent(String text);
    Post createPost(PostDTO postDTO);
    void deletePost(UUID postId);
    int calculateUserPosts(String username);
    Post findPostById(UUID postId);
    PostAttachment findPostAttachmentById(UUID id);
    List<Post> findAllPosts();
}
