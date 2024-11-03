package com.auth.authservice.service;

import com.auth.authservice.domain.User;
import com.auth.authservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User mockUser = new User();
        mockUser.setUsername("new_user");
        mockUser.setPassword("encoded_password");
        mockUser.setRoles(Set.of("ROLE_USER"));

        when(userRepository.save(mockUser)).thenReturn(mockUser);

        when(userRepository.findByUsername("new_user")).thenReturn(Optional.empty());

        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
    }


    @Test
    void testLoadUserByUsername() {
        User existingUser = new User();
        existingUser.setUsername("john_doe");
        existingUser.setPassword("encoded_password");
        existingUser.setRoles(Set.of("ROLE_USER"));

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(existingUser));

        User user = authService.loadUserByUsername("john_doe");

        assertNotNull(user, "User should not be null");
        assertThat(user.getUsername()).isEqualTo("john_doe");
        assertThat(user.getPassword()).isEqualTo("encoded_password");
    }

    @Test
    void testRegisterUser_ShouldEncodePasswordAndSaveUser() {
        User newUser = authService.registerUser("new_user", "password");

        assertNotNull(newUser, "User should not be null");
        assertThat(newUser.getUsername()).isEqualTo("new_user");
        assertThat(newUser.getPassword()).isEqualTo("encoded_password");
        assertThat(newUser.getRoles()).contains("ROLE_USER");
    }


    @Test
    void testRegisterUser_ShouldThrowExceptionIfUserExists() {
        when(userRepository.findByUsername("existing_user")).thenReturn(Optional.of(new User()));

        assertThrows(IllegalArgumentException.class, () -> authService.registerUser("existing_user", "password"));
    }
}
