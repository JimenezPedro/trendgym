package com.example.proyectodaw2324f.course;

import com.example.proyectodaw2324f.dto.CourseDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }



    public void initialCourses()throws IOException{

        Course course1 = new Course();
        String rutaImagen = "src/main/resources/static/img/CursoDefinición.png";
        Path path = Paths.get(rutaImagen);
        byte[] bytes = Files.readAllBytes(path);

        if(courseRepository.findCourseByName("Curso definicion")==null){
            course1.setName("Curso definición");
            course1.setDescription("Descubre nuestro curso de entrenamiento para definición muscular, ideal para quienes buscan esculpir su cuerpo. Con ejercicios específicos y asesoría en nutrición, te guiaremos en cada paso hacia tu objetivo de tonificación. Únete y transforma tu físico con técnicas eficaces y un enfoque personalizado. ¡Empieza tu viaje hacia un cuerpo más definido y saludable hoy!");
            course1.setUrl("https://www.youtube.com/embed/uZxhiOatfps?si=8cJJLbtqFEanFvba");
            course1.setImg(bytes);
            course1.setInstructor("Jeff Nipard");
            courseRepository.save(course1);
        }else{
            System.out.println("Curso definición ya creado");
        }

        Course course2 = new Course();
        String rutaImagen2 = "src/main/resources/static/img/banner.png";
        Path path2 = Paths.get(rutaImagen2);
        byte[] bytes2 = Files.readAllBytes(path2);
        if(courseRepository.findCourseByName("Curso volumen")==null){
            course2.setName("Curso volumen");
            course2.setDescription("Únete a nuestro innovador curso online de volumen y ganancia muscular, diseñado para impulsar tu progreso en el gimnasio. Aprende técnicas efectivas para aumentar la masa muscular, con rutinas personalizadas y asesoramiento nutricional. Ideal para todos los niveles, desde principiantes hasta avanzados. ¡Transforma tu cuerpo y alcanza tus objetivos de fitness desde la comodidad de tu hogar!");
            course2.setUrl("https://www.youtube.com/embed/EKI0ktcjADU?si=_ml6RvVwA8ic-I7M");
            course2.setImg(bytes2);
            course2.setInstructor("Jeff Nipard");
            courseRepository.save(course2);
        }else{
            System.out.println("Curso volumen ya creado");
        }

        Course course3 = new Course();
        String rutaImagen3 = "src/main/resources/static/img/entrenamiento.png";
        Path path3 = Paths.get(rutaImagen3);
        byte[] bytes3 = Files.readAllBytes(path3);
        if(courseRepository.findCourseByName("Mejor rutina empuje tracción")==null){
            course3.setName("Mejor rutina empuje tracción");
            course3.setDescription("Únete a nuestro innovador curso online de volumen y ganancia muscular, diseñado para impulsar tu progreso en el gimnasio. Aprende técnicas efectivas para aumentar la masa muscular, con rutinas personalizadas y asesoramiento nutricional. Ideal para todos los niveles, desde principiantes hasta avanzados. ¡Transforma tu cuerpo y alcanza tus objetivos de fitness desde la comodidad de tu hogar!");
            course3.setUrl("https://www.youtube.com/embed/ftpH4-xFGQI?si=l8MxtH2csRsolkiq");
            course3.setImg(bytes3);
            course3.setInstructor("Jeff Nipard");
            courseRepository.save(course3);
        }else{
            System.out.println("Curso mejor rutina empuje tracción ya creada");
        }

    }

    public List<Course> showCourses(){
        List<Course> cursos = courseRepository.findAll();
        cursos.forEach(curso -> {
            if (curso.getImg() != null) {
                String imgBase64 = Base64.getEncoder().encodeToString(curso.getImg());
                curso.setImgBase64("data:image/jpeg;base64," + imgBase64);
            }
        });
        return courseRepository.findAll();
    }

    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }

    public void registerCourse(CourseDTO courseDTO)throws Exception{
        Course course = new Course();
        if(courseRepository.findCourseByName(courseDTO.getName())!=null){
            throw new IllegalArgumentException("El nombre del curso ya está en uso");
        }
        if(StringUtils.isBlank(courseDTO.getName())){
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío");
        }

        if (StringUtils.isBlank(courseDTO.getUrl())) {
            throw new IllegalArgumentException("La URL no puede estar vacía");
        }

        course.setName(courseDTO.getName());
        course.setUrl(courseDTO.getUrl());
        course.setDescription(courseDTO.getDescription());
        course.setInstructor(courseDTO.getInstructor());
        course.setImg(courseDTO.getImg().getBytes());
        courseRepository.save(course);
    }

    public Optional<Course> getCursoById(Long id) {
        return courseRepository.findById(id);
    }

}
