package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.models.UserEntity;
import org.acme.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

// @ApplicationScoped: Tells CDI to create exactly one instance of this service for the entire application lifetime.
@ApplicationScoped
public class UserService {

    // @Inject: Tells CDI to automatically provide an instance of UserRepository here.
    // We don't need to call "new UserRepository()" ourselves — Quarkus handles that.
    @Inject
    UserRepository userRepository;

    public Collection<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> updateUser(Long id, UserEntity user) {
        return userRepository.update(id, user);
    }

    public boolean deleteUser(Long id) {
        return userRepository.delete(id);
    }
}