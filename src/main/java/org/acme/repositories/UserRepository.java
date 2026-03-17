package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.models.UserEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// @ApplicationScoped: Tells CDI to create exactly one instance of this class for the entire application lifetime.
// This makes our in-memory store (the map below) survive across all requests.
@ApplicationScoped
public class UserRepository {

    // In-memory store: maps each user's ID to their entity.
    private final Map<Long, UserEntity> store = new HashMap<>();

    // Thread-safe counter used to generate unique IDs for new users.
    private final AtomicLong idSequence = new AtomicLong(1);

    public Collection<UserEntity> findAll() {
        return store.values();
    }

    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public UserEntity save(UserEntity user) {
        user.setId(idSequence.getAndIncrement());
        store.put(user.getId(), user);
        return user;
    }

    public Optional<UserEntity> update(Long id, UserEntity updatedUser) {
        if (!store.containsKey(id)) {
            return Optional.empty();
        }
        updatedUser.setId(id);
        store.put(id, updatedUser);
        return Optional.of(updatedUser);
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}