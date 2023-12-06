package com.example.proyectodaw2324f.dto;

import com.example.proyectodaw2324f.foro.Post;
import com.example.proyectodaw2324f.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class CommentDTO {
    private Long postId;
    private String comment;


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
