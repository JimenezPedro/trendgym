package com.example.proyectodaw2324f.controllers;

import com.example.proyectodaw2324f.course.Course;
import com.example.proyectodaw2324f.course.CourseService;

import com.example.proyectodaw2324f.dto.CourseDTO;
import com.example.proyectodaw2324f.dto.PostDTO;
import com.example.proyectodaw2324f.dto.ProductDTO;
import com.example.proyectodaw2324f.foro.CommentService;

import com.example.proyectodaw2324f.foro.PostService;
import com.example.proyectodaw2324f.product.Product;
import com.example.proyectodaw2324f.product.ProductService;

import com.example.proyectodaw2324f.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador del administrador
 */

@Controller
public class AdminController {

    private final UserService userService;
    private final ProductService productService;
    private final CourseService courseService;
    private final PostService postService;
    private final CommentService commentService;
    @Autowired
    public AdminController(UserService userService, ProductService productService, CourseService courseService,PostService postService,CommentService commentService){
        this.userService = userService;
        this.productService = productService;
        this.courseService = courseService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/usuarios")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.userList());
        return "usuarios";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:usuarios";
    }

    @GetMapping("/panelControl")
    public String dashboard() {
        return "panelControl";
    }

    @GetMapping("/cursos")
    public String listCourses(Model model) {
        model.addAttribute("cursos", courseService.showCourses());
        return "cursos";
    }

    @PostMapping("/cursos/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return "redirect:/cursos";
    }

    @GetMapping("/createCourse")
    public String createCourse(Model model){
        model.addAttribute("course",new Course());
        return "createCourse";
    }

    @PostMapping("/creationCourse")
    public String creationCourse(@ModelAttribute CourseDTO courseDTO)throws Exception{
        courseService.registerCourse(courseDTO);
        return "redirect:cursos";
    }

    @GetMapping("/productos")
    public String listProducts(Model model){
        model.addAttribute("productos",productService.showProducts());
        return "productos";
    }
    @GetMapping("/createProduct")
    public String createProduct(Model model){
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping("/creationProduct")
    public String creationProduct(@ModelAttribute ProductDTO productDTO)throws Exception{
        productService.registerProduct(productDTO);
        return "redirect:productos";
    }

    @PostMapping("/productos/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return "redirect:/productos";
    }

    @PostMapping("/Foro/{id}")
    public String deletePost(@PathVariable Long id){
        postService.deletePostById(id);
        return "redirect:/Foro";
    }

    @PostMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id,PostDTO postDTO){
        commentService.deleteCommentById(id);
        Long idPost = postDTO.getId();
        return "redirect:/Foro/" + idPost;
    }




}
