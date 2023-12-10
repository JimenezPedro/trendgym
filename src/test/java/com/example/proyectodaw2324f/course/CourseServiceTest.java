package com.example.proyectodaw2324f.course;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void testDeleteCourseById() {
        // Arrange
        Long courseIdToDelete = 1L;

        // Act
        assertDoesNotThrow(() -> courseService.deleteCourseById(courseIdToDelete));

        // Verificar que el método del repositorio fue llamado con el ID correcto
        verify(courseRepository, times(1)).deleteById(courseIdToDelete);
    }

}
