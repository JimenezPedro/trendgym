package com.example.proyectodaw2324f.foro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.proyectodaw2324f.foro.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void testFindPostById() {
        // Arrange
        Long postId = 1L;
        Post mockPost = new Post();
        mockPost.setId(postId);

        // Mockear el comportamiento del repositorio
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        // Act
        Optional<Post> result = postService.findPostById(postId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(postId, result.get().getId());

        // Verificar que el método del repositorio fue llamado con el ID correcto
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    void testFindPostById_PostNotFound() {
        // Arrange
        Long nonExistentPostId = 99L;

        // Mockear el comportamiento del repositorio cuando el post no existe
        when(postRepository.findById(nonExistentPostId)).thenReturn(Optional.empty());

        // Act
        Optional<Post> result = postService.findPostById(nonExistentPostId);

        // Assert
        assertFalse(result.isPresent());

        // Verificar que el método del repositorio fue llamado con el ID correcto
        verify(postRepository, times(1)).findById(nonExistentPostId);
    }
}
