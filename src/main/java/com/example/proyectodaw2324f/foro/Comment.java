package com.example.proyectodaw2324f.foro;

import com.example.proyectodaw2324f.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;

    private LocalDateTime date;
}
