package com.example.proyectodaw2324f.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "product",uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private double price;
    private int stock;
    @Lob
    @Column(length = 100000)
    private byte[] img;
    @Transient
    private String imgBase64;
}
