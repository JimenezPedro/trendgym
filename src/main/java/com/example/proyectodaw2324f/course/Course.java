package com.example.proyectodaw2324f.course;


import com.example.proyectodaw2324f.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "course",uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String url;
    private String instructor;
    @Lob
    @Column(length = 1000000)
    private byte[] img;
    @Transient
    private String imgBase64;
    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}
