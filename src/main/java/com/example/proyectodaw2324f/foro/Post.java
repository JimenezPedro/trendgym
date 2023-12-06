package com.example.proyectodaw2324f.foro;


import com.example.proyectodaw2324f.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Lob
    @Column(length = 1000000)
    private byte[] img;

    @Transient
    private String imgBase64;

    private LocalDateTime date;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comments;




}
