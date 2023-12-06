package com.example.proyectodaw2324f.foro;

import com.example.proyectodaw2324f.dto.CommentDTO;
import com.example.proyectodaw2324f.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    public CommentService(CommentRepository commentRepository,PostService postService){
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    public Comment saveComment(CommentDTO commentDTO, User user,Post post){
        Comment comment = new Comment();

        comment.setContent(commentDTO.getComment());
        comment.setDate(LocalDateTime.now());
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);
        return comment;
    }

    public void deleteCommentById(Long id){
        commentRepository.deleteById(id);
    }

}
