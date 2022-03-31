package com.company.tspserver.service;

import com.company.tspserver.dto.PostCommentDTO;
import com.company.tspserver.dto.PostDTO;
import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostAttachment;
import com.company.tspserver.entity.PostComment;
import com.company.tspserver.entity.PostLike;

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
    List<String> findAllPostLikes(UUID postId);
    PostLike createPostLike(String username, UUID postId);
    void deletePostLike(String username, UUID postId);
    List<PostComment> getPostComments(UUID postId);
    PostComment createPostComment(PostCommentDTO commentDTO);
    void deletePostComment(PostComment postComment);
    PostComment findPostCommentById(UUID id);
}
