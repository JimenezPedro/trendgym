package com.example.proyectodaw2324f.config;

import com.example.proyectodaw2324f.course.Course;
import com.example.proyectodaw2324f.course.CourseRepository;
import com.example.proyectodaw2324f.course.CourseService;
import com.example.proyectodaw2324f.foro.PostService;
import com.example.proyectodaw2324f.product.ProductService;
import com.example.proyectodaw2324f.user.Role;
import com.example.proyectodaw2324f.user.User;
import com.example.proyectodaw2324f.user.UserRepository;
import com.example.proyectodaw2324f.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CourseService courseService;
    private final UserService userService;
    private final ProductService productService;
    private final PostService postService;

    public DataInitializer(CourseService courseService, UserService userService, ProductService productService,PostService postService){
        this.userService = userService;
        this.courseService = courseService;
        this.productService = productService;
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.registerAdminUser();
        courseService.initialCourses();
        productService.generateProducts();
        postService.generatePosts();
    }




}
