package com.company.tspserver.dto.mapper;

import com.company.tspserver.dto.PostCommentDTO;
import com.company.tspserver.dto.PostDTO;
import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostComment;
import com.company.tspserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PostMapper {
    @Autowired
    private PostService postService;

    public PostDTO convertToDto(Post post){
        PostDTO dto = new PostDTO(post);
        dto.setUsersLiked(postService.findAllPostLikes(post.getId()));
        List<PostComment> comments = postService.getPostComments(post.getId());
        List<PostCommentDTO> commentDTOS = new LinkedList<>();
        for(PostComment comment: comments){
            commentDTOS.add(new PostCommentDTO(comment));
        }
        dto.setComments(commentDTOS);
        return dto;
    }
}
