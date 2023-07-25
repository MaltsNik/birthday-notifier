package database.dao;

import database.dao.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getById(Long id);

    User createUser(User user);

    User updateById(Long id, User user);

    void deleteById(Long id);
}
