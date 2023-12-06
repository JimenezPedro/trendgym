package com.example.proyectodaw2324f.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private int stock;
    private MultipartFile img;
}
