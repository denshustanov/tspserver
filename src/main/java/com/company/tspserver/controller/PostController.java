package com.company.tspserver.controller;

import com.company.tspserver.dto.PostCommentDTO;
import com.company.tspserver.dto.PostDTO;
import com.company.tspserver.dto.UserDTO;
import com.company.tspserver.dto.mapper.PostMapper;
import com.company.tspserver.entity.*;
import com.company.tspserver.service.PostService;
import com.company.tspserver.service.UserService;
import io.jmix.core.security.CurrentAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
public class PostController {
    @Autowired
    protected PostService postService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected CurrentAuthentication currentAuthentication;

    @Autowired
    protected PostMapper postMapper;

    @PostMapping(value = "/post")
    ResponseEntity createPost(@RequestBody PostDTO postDTO){
        String username = currentAuthentication.getUser().getUsername();
        if(postDTO.getAuthor() == null){
            User author = userService.findUserByUsername(username);
            postDTO.setAuthor(new UserDTO(author));
        }else if(!currentAuthentication.getUser().getUsername().equals(postDTO.getAuthor().getUsername())){
            return ResponseEntity.status(403).body("Invalid username!");
        }
        Post post = postService.createPost(postDTO);

        return ResponseEntity.ok(new PostDTO(post));
    }

    @GetMapping(value = "/user/{username}/posts")
    ResponseEntity getUserPosts(@PathVariable String username){
        List<Post> posts = postService.findPostByAuthorUsername(username);

        List<PostDTO> postDTOS = new LinkedList<>();

        for (Post post: posts) {
            postDTOS.add(postMapper.convertToDto(post));
        }

        return ResponseEntity.ok(postDTOS);
    }

    @DeleteMapping(value = "/post/{id}")
    ResponseEntity deletePost(@PathVariable String id){
        UUID postId = UUID.fromString(id);
        try {
            Post post = postService.findPostById(postId);
            if (!post.getAuthor().getUsername().equals(currentAuthentication.getUser().getUsername())) {
                return ResponseEntity.status(403).body("User can not delete not his posts!");
            }

            postService.deletePost(postId);

            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(404).body("Post not found!");
        }
    }

    @GetMapping(value = "/post/attachment/{id}")
    ResponseEntity getPostAttachment(@PathVariable String id){
        UUID attachmentId = UUID.fromString(id);

        try{
            PostAttachment postAttachment = postService.findPostAttachmentById(attachmentId);
            return ResponseEntity.ok(postAttachment.getImage());
        } catch (Exception e){
            return ResponseEntity.status(404).body("Attachment not found");
        }
    }

    @GetMapping(value = "/post/all")
    ResponseEntity getAllPosts(){
        List<Post> posts = postService.findAllPosts();

        List<PostDTO> postDTOS = new LinkedList<>();

        for(Post post: posts){
            postDTOS.add(postMapper.convertToDto(post));
        }

        return ResponseEntity.ok(postDTOS);
    }

    @GetMapping(value = "/post/find")
    ResponseEntity findPostsByText(@RequestParam(name = "text") String text){
        List<Post> posts = postService.findPostsByContent(text);

        List<PostDTO> postDTOS = new LinkedList<>();
        for(Post post: posts){
            postDTOS.add(postMapper.convertToDto(post));
        }

        return ResponseEntity.ok(postDTOS);
    }

    @PostMapping(value = "/post/{id}/like")
    ResponseEntity createPostLike(@PathVariable String id){
        String username = currentAuthentication.getUser().getUsername();
        UUID postId = UUID.fromString(id);
        PostLike like = postService.createPostLike(username, postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/post/{id}/like")
    ResponseEntity deletePostLike(@PathVariable String id){
        String username = currentAuthentication.getUser().getUsername();
        UUID postId = UUID.fromString(id);
        postService.deletePostLike(username, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/post/{id}/comments")
    ResponseEntity getPostComments(@PathVariable String id){
        return null;
    }

    @PostMapping(value = "/post/{id}/comment")
    ResponseEntity createPostComment(@RequestBody PostCommentDTO postCommentDTO){
        System.out.println("comment!");
        String username = currentAuthentication.getUser().getUsername();
        User user = userService.findUserByUsername(username);
        postCommentDTO.setAuthor(new UserDTO(user));
        PostComment comment = postService.createPostComment(postCommentDTO);
        return ResponseEntity.ok(new PostCommentDTO(comment));
    }
}
