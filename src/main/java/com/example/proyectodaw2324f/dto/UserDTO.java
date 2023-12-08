package com.example.proyectodaw2324f.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserDTO {
    private String password;
    private String address;
    private String city;
    private MultipartFile img;
    private int height;
    private double weight;

}
