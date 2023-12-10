package com.example.proyectodaw2324f.config;


import com.example.proyectodaw2324f.course.CourseService;
import com.example.proyectodaw2324f.foro.PostService;
import com.example.proyectodaw2324f.product.ProductService;
import com.example.proyectodaw2324f.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CourseService courseService;
    private final UserService userService;
    private final ProductService productService;
    private final PostService postService;

    /**
     * @param courseService clase que contiene los métodos relacionados a los cursos
     * @param userService clase que contiene los métodos relacionados a los usuarios
     * @param productService clase que contiene los métodos relacionados a los productos
     * @param postService clase que contiene los métodos relacionados a las publicaciones
     */
    public DataInitializer(CourseService courseService, UserService userService, ProductService productService,PostService postService){
        this.userService = userService;
        this.courseService = courseService;
        this.productService = productService;
        this.postService = postService;
    }


    /**
     * Método que inicia los datos de las distintas clases
     */
    @Override
    public void run(String... args) throws Exception {
        userService.registerAdminUser();
        courseService.initialCourses();
        productService.generateProducts();
        postService.generatePosts();
    }




}
