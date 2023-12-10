package com.example.proyectodaw2324f.user;

import com.example.proyectodaw2324f.course.Course;
import com.example.proyectodaw2324f.course.CourseRepository;
import com.example.proyectodaw2324f.dto.UserDTO;
import com.example.proyectodaw2324f.dto.UserRegisterDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,CourseRepository courseRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.courseRepository = courseRepository;
    }

    /**
     * Función para encontrar un usuario por su nombre de Usuario
     * @param username cadena que representa el nombre de usuario
     * @return retorna un usuario o una excepción
     */
    public User findUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user!=null){
            return user;
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    /**
     * Función para registrar un usuario
     * @param userRegisterDTO representa el objeto usuario que quedará almacenado en la base de datos
     */
    public boolean registerUser(UserRegisterDTO userRegisterDTO){
        User user = new User();
        if(StringUtils.isBlank(userRegisterDTO.getUsername())){
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }

        if (StringUtils.isBlank(userRegisterDTO.getPassword()) || userRegisterDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }

        if (userRepository.findByUsername(userRegisterDTO.getUsername()) != null) {
            throw new UsernameNotFoundException("El nombre de usuario ya está en uso.");
        }

        user.setName(userRegisterDTO.getName());
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setCountry(userRegisterDTO.getCountry());
        user.setRole(Role.USER);
        userRepository.save(user);
        return true;
    }

    /**
     * Función para obtener el usuario con la sesión iniciada
     * @return un usuario si se encuentra, en caso contrario, un null
     */
    public User returnUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            String username =  ((UserDetails)principal).getUsername();
            return userRepository.findByUsername(username);
        }else{
            return null;
        }
    }


    /**
     * Función para actualizar el ususario en el área de perfil
     * @param userDTO representa el usuario del que queremos añadir información en la BDD
     * @throws Exception excepción necesaria al usar el método .getBytes()
     */
    public void updateUser(UserDTO userDTO) throws Exception{
        User userR = returnUser();
        double heightMetres = userDTO.getHeight()/100.0;
        double imc = userDTO.getWeight()/Math.pow(heightMetres,2);
        double imcRound = (double) Math.round(imc * 100) /100;

        userR.setAddress(userDTO.getAddress());
        userR.setCity(userDTO.getCity());
        userR.setHeight(userDTO.getHeight());
        userR.setWeight(userDTO.getWeight());
        userR.setImc(imcRound);
        if(!userDTO.getImg().isEmpty()) {
            userR.setImg(userDTO.getImg().getBytes());
        }
        userRepository.save(userR);

    }

    /**
     *
     * @param username cadena que representa el nombre de usuario
     * @return un usuario que coincida con el username que le hemos pasado
     * @throws UsernameNotFoundException excepción que se lanza si no existe el usuario en la BDD
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Usuario incorrecto");
        }
        return user;
    }

    /**
     *
     * @return
     */
    public List<User> userList(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void registerAdminUser(){
        User user = new User();
        if(userRepository.findByUsername("admin") == null){
            user.setUsername("admin");
            user.setName("ADMIN");
            user.setEmail("admin@iestrassierra.net");
            user.setPassword(passwordEncoder.encode("admin2324"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }else{
            System.out.println("Admin ya creado");
        }
    }

    public void subscribeCourse(Long userId, Long courseId){
        User user = userRepository.findById(userId).get();
        Course course = courseRepository.findById(courseId).get();

        if(user.getCourses().contains(course)){
            throw new IllegalStateException("El usuario ya está suscrito");
        }

        user.getCourses().add(course);
        course.getUsers().add(user);

        userRepository.save(user);
        courseRepository.save(course);
    }

    public void cancelSubscription(Long userId, Long courseId){
        User user = userRepository.findById(userId).get();
        Course course = courseRepository.findById(courseId).get();

        user.getCourses().remove(course);
        course.getUsers().remove(user);

        userRepository.save(user);
        courseRepository.save(course);
    }

    public boolean findUsername(String username){
        return userRepository.existsByUsername(username);
    }



}
