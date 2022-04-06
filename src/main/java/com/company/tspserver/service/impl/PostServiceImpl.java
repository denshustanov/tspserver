package com.company.tspserver.service.impl;

import com.company.tspserver.dto.PostCommentDTO;
import com.company.tspserver.dto.PostDTO;
import com.company.tspserver.entity.*;
import com.company.tspserver.entity.complaint.Complaint;
import com.company.tspserver.entity.complaint.ComplaintCause;
import com.company.tspserver.repository.*;
import com.company.tspserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    protected PostRepository postRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected PostLikeRepository postLikeRepository;

    @Autowired
    protected PostCommentRepository postCommentRepository;

    @Autowired
    protected ComplaintRepository complaintRepository;


    @Override
    public List<Post> findPostByAuthorUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return postRepository.findPostByAuthor(user);
    }

    @Override
    public List<Post> findPostsByContent(String text) {
        return postRepository.findPostByText(text);
    }

    @Override
    public Post createPost(PostDTO postDTO) {
        User author = userRepository.findUserByUsername(postDTO.getAuthor().getUsername());
        return postRepository.createPost(author, postDTO.getText(), postDTO.getAttachments(), postDTO.getPublicationDate());
    }

    @Override
    public void deletePost(UUID postId) {
        Post post = postRepository.findPostById(postId);
        if(post!=null){
            postRepository.deletePost(post);
        }
    }

    @Override
    public int calculateUserPosts(String username) {
        User user = userRepository.findUserByUsername(username);
        return postRepository.findPostByAuthor(user).size();
    }

    @Override
    public Post findPostById(UUID postId) {
        return postRepository.findPostById(postId);
    }

    @Override
    public PostAttachment findPostAttachmentById(UUID id) {
        return postRepository.findPostAttachmentById(id);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllPosts();
    }

    @Override
    public PostLike createPostLike(String username, UUID postId) {
        User user = userRepository.findUserByUsername(username);
        Post post = postRepository.findPostById(postId);
        try{
            PostLike postLike = postLikeRepository.findPostLike(user, post);
            return null;
        } catch (Exception e){}
        return postLikeRepository.createPostLike(user, post);
    }

    @Override
    public void deletePostLike(String username, UUID postId) {
        User user = userRepository.findUserByUsername(username);
        Post post = postRepository.findPostById(postId);
        try {
            PostLike postLike = postLikeRepository.findPostLike(user, post);
            postLikeRepository.deletePostLike(postLike);
        } catch (Exception e){

        }
    }

    @Override
    public List<String> findAllPostLikes(UUID postId) {
        Post post = postRepository.findPostById(postId);
        List<PostLike> likes = postLikeRepository.findAllLikesByPost(post);
        List<String> usersLiked = new ArrayList<>();
        for (PostLike like: likes) {
            usersLiked.add(like.getUser().getUsername());
        }
        return usersLiked;
    }

    @Override
    public List<PostComment> getPostComments(UUID postId) {
        Post post = postRepository.findPostById(postId);
        return postCommentRepository.FindAllCommentsByPost(post);
    }

    @Override
    public PostComment createPostComment(PostCommentDTO commentDTO) {
        Post post = postRepository.findPostById(commentDTO.getPostId());
        User author = userRepository.findUserByUsername(commentDTO.getAuthor().getUsername());
        return postCommentRepository.createPostComment(author,
                post,
                commentDTO.getText(),
                commentDTO.getPublicationDate());
    }

    @Override
    public void deletePostComment(PostComment postComment) {
        postCommentRepository.deletePostComment(postComment);
    }

    @Override
    public PostComment findPostCommentById(UUID id) {
        return postCommentRepository.findPostCommentById(id);
    }

    @Override
    public List<Post> findPostsBySubscriptions(String username, int offset) {
        User user = userRepository.findUserByUsername(username);
        return postRepository.loadSubscriptionsPosts(user, offset);
    }

    @Override
    public Complaint createPostComplaint(String username, UUID postId, ComplaintCause complaintCause) {
        User user = userRepository.findUserByUsername(username);
        Post post = findPostById(postId);
        return complaintRepository.createComplaint(user, post, complaintCause);
    }
}
