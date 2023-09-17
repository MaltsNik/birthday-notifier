package org.birthdaynotifier.repository;

import org.birthdaynotifier.repository.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<List<User>> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByFullName(String fullName);
    Optional<Long> createUser(User user);
    Optional<User> updateById(Long id, User user);
    void deleteById(Long id);
}
