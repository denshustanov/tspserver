package com.company.tspserver.repository;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostComment;
import com.company.tspserver.entity.User;

import java.util.List;

public interface PostCommentRepository {
    PostComment createPostComment(User user, Post post, String text);
    List<PostComment> FindAllCommentsByPost(Post post);
    void deletePostComment(PostComment postComment);
}
