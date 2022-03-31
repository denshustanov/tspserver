package com.company.tspserver.repository;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostComment;
import com.company.tspserver.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostCommentRepository {
    PostComment createPostComment(User user, Post post, String text, LocalDateTime publicationDate);
    List<PostComment> FindAllCommentsByPost(Post post);
    void deletePostComment(PostComment postComment);
    PostComment findPostCommentById(UUID id);
}
