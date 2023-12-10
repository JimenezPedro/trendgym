package com.example.proyectodaw2324f.foro;

import com.example.proyectodaw2324f.dto.PostDTO;

import com.example.proyectodaw2324f.user.User;
import com.example.proyectodaw2324f.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;

import java.util.Base64;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository,UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * Método que guardar un post en la base de datos
     * @param postDTO Objeto publicación
     * @param user Objeto usuario
     * @throws Exception lanza una excepción por el método getBytes()
     */
    public void savePost(PostDTO postDTO, User user) throws Exception{
        Post post = new Post();

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDate(LocalDateTime.now());
        post.setUser(user);
        if(!postDTO.getImg().isEmpty()) {
            post.setImg(postDTO.getImg().getBytes());
        }
        postRepository.save(post);
    }

    /**
     * Encontrar un post por su id
     * @param id id del post
     * @return un post
     */
    public Optional<Post> findPostById(Long id){
        return postRepository.findById(id);
    }

    /**
     * Método para mostrar todos los post en una lista
     * @return una lista de posts
     */
    public List<Post> showPosts(){
        List<Post> posts = postRepository.findAll();
        posts.forEach(post -> {
            if (post.getImg() != null) {
                String imgBase64 = Base64.getEncoder().encodeToString(post.getImg());
                post.setImgBase64("data:image/jpeg;base64," + imgBase64);
            }
        });
        return posts;
    }

    /**
     * Borrar un post por id
     * @param id id del post a eliminar
     */
    public void deletePostById(Long id){
        Post post = postRepository.findById(id).get();
        post.getComments().clear();
        postRepository.deleteById(id);
    }

    /**
     * Método para inicializar algunos posts de ejemplo
     * @throws Exception excepción lanzada por image.getBytes()
     */
    public void generatePosts() throws Exception{

        Post post1 = new Post();

        String rutaImagen = "src/main/resources/static/img/CursoDefinicion.png";
        Path path = Paths.get(rutaImagen);
        byte[] bytes = Files.readAllBytes(path);

        if(postRepository.findPostsByTitle("La mejor web")==null){
            post1.setTitle("La mejor web");
            post1.setContent("Descubre nuestro curso de entrenamiento para definición muscular, ideal para quienes buscan esculpir su cuerpo. Con ejercicios específicos y asesoría en nutrición, te guiaremos en cada paso hacia tu objetivo de tonificación. Únete y transforma tu físico con técnicas eficaces y un enfoque personalizado. ¡Empieza tu viaje hacia un cuerpo más definido y saludable hoy!");
            post1.setImg(bytes);
            post1.setUser(userRepository.findByUsername("admin"));
            post1.setDate(LocalDateTime.now());
            postRepository.save(post1);
        }else{
            System.out.println("Post ya creado");
        }

        Post post2 = new Post();

        String rutaImagen2 = "src/main/resources/static/img/banner.png";
        Path path2 = Paths.get(rutaImagen2);
        byte[] bytes2 = Files.readAllBytes(path2);

        if(postRepository.findPostsByTitle("Mirad esta rutina")==null){
            post2.setTitle("Mirad esta rutina");
            post2.setContent("Descubre nuestro curso de entrenamiento para definición muscular, ideal para quienes buscan esculpir su cuerpo. Con ejercicios específicos y asesoría en nutrición, te guiaremos en cada paso hacia tu objetivo de tonificación. Únete y transforma tu físico con técnicas eficaces y un enfoque personalizado. ¡Empieza tu viaje hacia un cuerpo más definido y saludable hoy!");
            post2.setImg(bytes2);
            post2.setUser(userRepository.findByUsername("admin"));
            post2.setDate(LocalDateTime.now());
            postRepository.save(post2);
        }else{
            System.out.println("Post ya creado");
        }

        Post post3 = new Post();

        String rutaImagen3 = "src/main/resources/static/img/straps.png";
        Path path3 = Paths.get(rutaImagen3);
        byte[] bytes3 = Files.readAllBytes(path3);

        if(postRepository.findPostsByTitle("El mejor instructor")==null){
            post3.setTitle("El mejor instructor");
            post3.setContent("Descubre nuestro curso de entrenamiento para definición muscular, ideal para quienes buscan esculpir su cuerpo. Con ejercicios específicos y asesoría en nutrición, te guiaremos en cada paso hacia tu objetivo de tonificación. Únete y transforma tu físico con técnicas eficaces y un enfoque personalizado. ¡Empieza tu viaje hacia un cuerpo más definido y saludable hoy!");
            post3.setImg(bytes3);
            post3.setUser(userRepository.findByUsername("admin"));
            post3.setDate(LocalDateTime.now());
            postRepository.save(post3);
        }else{
            System.out.println("Post ya creado");
        }

    }

}
