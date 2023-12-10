package com.example.proyectodaw2324f.user;

import com.example.proyectodaw2324f.dto.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testFindUserByUsername() {
        // Arrange
        String username = "testUser";
        User mockUser = new User();
        mockUser.setUsername(username);

        // Mockear el comportamiento del repositorio
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Act
        User foundUser = userService.findUserByUsername(username);

        // Assert
        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());

        // Verificar que el método del repositorio fue llamado con el username correcto
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindUserByUsername_UserNotFound() {
        // Arrange
        String nonExistentUsername = "nonExistentUser";

        // Mockear el comportamiento del repositorio cuando el usuario no existe
        when(userRepository.findByUsername(nonExistentUsername)).thenReturn(null);

        // Act y Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.findUserByUsername(nonExistentUsername);
        });

        // Verificar que el método del repositorio fue llamado con el username correcto
        verify(userRepository, times(1)).findByUsername(nonExistentUsername);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setName("Test User");
        userRegisterDTO.setUsername("testuser");
        userRegisterDTO.setEmail("testuser@example.com");
        userRegisterDTO.setPassword("password123");
        userRegisterDTO.setCountry("Country");

        // Mockear el comportamiento del repositorio
        when(userRepository.findByUsername(userRegisterDTO.getUsername())).thenReturn(null);

        // Mockear el comportamiento del password encoder
        when(passwordEncoder.encode(userRegisterDTO.getPassword())).thenReturn("encodedPassword");

        // Act
        boolean registrationResult = userService.registerUser(userRegisterDTO);

        // Assert
        assertTrue(registrationResult);

        // Verificar que el método del repositorio fue llamado con el usuario correcto
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("existingUser");
        userRegisterDTO.setPassword("Contraseña98.");
        // Mockear el comportamiento del repositorio cuando el usuario ya existe
        when(userRepository.findByUsername(userRegisterDTO.getUsername())).thenReturn(new User());

        // Act y Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.registerUser(userRegisterDTO);
        });

        // Verificar que el método del repositorio no fue llamado
        verify(userRepository, never()).save(any(User.class));
    }

}