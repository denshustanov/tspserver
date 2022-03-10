package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostComment;
import com.company.tspserver.entity.User;
import com.company.tspserver.repository.PostCommentRepository;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class PostCommentRepositoryImpl implements PostCommentRepository {
    @Autowired
    protected DataManager dataManager;

    @Override
    public PostComment createPostComment(User user, Post post, String text) {
        PostComment postComment = dataManager.create(PostComment.class);

        postComment.setPost(post);
        postComment.setAuthor(user);
        postComment.setText(text);
        return dataManager.save(postComment);
    }

    @Override
    public List<PostComment> FindAllCommentsByPost(Post post) {
        return dataManager.load(PostComment.class)
                .condition(PropertyCondition.equal("post", post))
                .list();
    }

    @Override
    public void deletePostComment(PostComment postComment) {
        dataManager.remove(postComment);
    }
}
