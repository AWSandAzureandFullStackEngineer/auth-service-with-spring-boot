package com.auth.authservice.repository;

import com.auth.authservice.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        testUser = new User(null, "john_doe", "securepassword", roles);
        userRepository.save(testUser);
    }

    @Test
    public void shouldFindUserByUsername() {
        Optional<User> foundUser = userRepository.findByUsername("john_doe");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("john_doe");
    }

    @Test
    public void shouldReturnEmptyIfUserNotFound() {
        Optional<User> foundUser = userRepository.findByUsername("non_existent_user");
        assertThat(foundUser).isEmpty();
    }
}

