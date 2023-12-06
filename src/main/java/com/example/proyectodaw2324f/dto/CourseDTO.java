package com.example.proyectodaw2324f.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private String url;
    private String instructor;
    private MultipartFile img;
}
