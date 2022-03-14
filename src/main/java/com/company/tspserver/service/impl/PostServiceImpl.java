package com.company.tspserver.service.impl;

import com.company.tspserver.dto.PostDTO;
import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostAttachment;
import com.company.tspserver.entity.User;
import com.company.tspserver.repository.PostRepository;
import com.company.tspserver.repository.UserRepository;
import com.company.tspserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    protected PostRepository postRepository;

    @Autowired
    protected UserRepository userRepository;


    @Override
    public List<Post> findPostByAuthorUsername(String username) {
        return postRepository.findPostByAuthorUsername(username);
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
        return postRepository.findPostByAuthorUsername(username).size();
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
}
