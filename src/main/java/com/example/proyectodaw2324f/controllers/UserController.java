package com.example.proyectodaw2324f.controllers;

import com.example.proyectodaw2324f.course.Course;
import com.example.proyectodaw2324f.course.CourseService;
import com.example.proyectodaw2324f.dto.*;
import com.example.proyectodaw2324f.foro.Comment;
import com.example.proyectodaw2324f.foro.CommentService;
import com.example.proyectodaw2324f.foro.Post;
import com.example.proyectodaw2324f.foro.PostService;
import com.example.proyectodaw2324f.product.Product;
import com.example.proyectodaw2324f.product.ProductService;
import com.example.proyectodaw2324f.user.User;
import com.example.proyectodaw2324f.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

import java.util.*;


/**
 * Controlador del usuario
 */
@Controller
public class UserController {

    private final UserService userService;
    private final CourseService courseService;
    private final ProductService productService;
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public UserController(UserService userService,CourseService courseService,ProductService productService,PostService postService,CommentService commentService){
        this.userService = userService;
        this.courseService = courseService;
        this.productService = productService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/index")
    public String showIndex(){
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegisterDTO userRegisterDTO,Model model){
        boolean nombreUsuarioExistente = userService.findUsername(userRegisterDTO.getUsername().trim());
        if (nombreUsuarioExistente) {
            model.addAttribute("errorNombreUsuario", "El nombre de usuario ya está en uso.");
            return "register";
        }

        userService.registerUser(userRegisterDTO);
        return "redirect:login";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserDTO userDTO)throws Exception{
        userService.updateUser(userDTO);
        return "redirect:profileArea";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(){
        return "redirect:index";
    }

    @GetMapping("/politicaPrivacidad")
    public String showPrivPol(){
        return "politicaPrivacidad";
    }

    @GetMapping("/ayuda")
    public String showHelp(){
        return "ayuda";
    }

    @GetMapping("/acercaDe")
    public String showAbbout(){
        return "acercaDe";
    }

    @GetMapping("/profileArea")
    public String showProfileArea(Model model){
        User userR = userService.returnUser();
        List<Course> courses = userR.getCourses();
        if (userR != null && userR.getImg() != null) {
            String imageBase64 = Base64.getEncoder().encodeToString(userR.getImg());
            model.addAttribute("imageBase64","data:image/jpeg;base64, " + imageBase64);
        }
        model.addAttribute("courses",courses);
        model.addAttribute("user",userR);
        return "profileArea";
    }

    @GetMapping("/courses")
    public String courses(Model model){
        List<Course> courses = courseService.showCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String course(@PathVariable Long id, Model model,Principal principal) {
        Optional<Course> courseOpt = courseService.getCursoById(id);

        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            User user = userService.findUserByUsername(principal.getName());
            model.addAttribute("user",user);
            model.addAttribute("curso", course);
            return "course";
        } else {
            return "/courses";
        }
    }

    @GetMapping("/tienda")
    public String tienda(Model model){
        List<Product> productos = productService.showProducts();
        model.addAttribute("products", productos);
        return "tienda";
    }

    @GetMapping("/tienda/{id}")
    public String product(@PathVariable Long id,Model model){
        Optional<Product> productOpt = productService.getProductById(id);
        if(productOpt.isPresent()){
            Product producto = productOpt.get();
            model.addAttribute("producto", producto);
            List<Product> productos = productService.showProducts();
            productos.forEach(product -> {
                if (product.getImg() != null) {
                    String imgBase64 = Base64.getEncoder().encodeToString(product.getImg());
                    product.setImgBase64("data:image/jpeg;base64," + imgBase64);
                }
            });
            return "producto";
        }else{
            return "/tienda";
        }
    }

    @GetMapping("/Foro")
    public String foro(Model model){
        List<Post> posts = postService.showPosts();
        PostDTO postDTO = new PostDTO();
        Collections.sort(posts, Comparator.comparing(Post::getDate).reversed());
        model.addAttribute("posts", posts);
        model.addAttribute("postForm",postDTO);
        return "Foro";
    }

    @GetMapping("/Foro/{id}")
    public String post(@PathVariable Long id,Model model){

        Optional<Post> postOpt = postService.findPostById(id);
        if(postOpt.isPresent()){
            Post post = postOpt.get();
            CommentDTO commentDTO = new CommentDTO();
            Collections.sort(post.getComments(), Comparator.comparing(Comment::getDate).reversed());
            model.addAttribute("post", post);
            model.addAttribute("commentDTO",commentDTO);
            model.addAttribute("comments",post.getComments());
            List<Post> posts = postService.showPosts();

            posts.forEach(publ -> {
                if (publ.getImg() != null) {
                    String imgBase64 = Base64.getEncoder().encodeToString(publ.getImg());
                    publ.setImgBase64("data:image/jpeg;base64," + imgBase64);
                }
            });
            return "post";
        }else{
            PostDTO postDTO = new PostDTO();
            model.addAttribute("postForm",postDTO);
            return "/Foro";
        }
    }

    @PostMapping("/publicar")
    public String createPost(@ModelAttribute PostDTO post, Principal principal)throws Exception{
        User user = userService.findUserByUsername(principal.getName());
        postService.savePost(post,user);
        return "redirect:/Foro";
    }

    @PostMapping("/comentarPost")
    public String createComment(CommentDTO commentDTO, Model model,  Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        Post post = postService.findPostById(commentDTO.getPostId()).get();
        List<Comment> comments = commentService.showComments(post);
        Comment comment =commentService.saveComment(commentDTO,user,post);
        comments.add(comment);

        model.addAttribute("post", post);
        model.addAttribute("commentDTO",new CommentDTO());
        model.addAttribute("comments",comments);

        return "redirect:/Foro/" + commentDTO.getPostId();
    }
    @PostMapping("/subscribeCourse")
    public String suscribeCourse(CourseDTO courseDTO, Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        userService.subscribeCourse(user.getId(),courseDTO.getId());
        return "redirect:/courses/" + courseDTO.getId();
    }

    @PostMapping("/cancelSubscription")
    public String cancelSuscription(CourseDTO courseDTO, Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        userService.cancelSubscription(user.getId(),courseDTO.getId());
        return "redirect:/courses/" + courseDTO.getId();
    }


}
