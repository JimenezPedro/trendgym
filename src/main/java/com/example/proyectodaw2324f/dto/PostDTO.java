package com.example.proyectodaw2324f.dto;

import com.example.proyectodaw2324f.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private MultipartFile img;
    private LocalDateTime date;
    private User user;

}
