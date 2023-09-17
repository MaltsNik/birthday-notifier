package org.birthdaynotifier.repository.impl;

import org.birthdaynotifier.repository.DayRepository;
import org.birthdaynotifier.repository.UserRepository;
import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.repository.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<List<User>> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            List<User> result = query.getResultList();
            return Optional.ofNullable(result);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User u where u.id = :id", User.class);
            query.setParameter("id", id);
            User user = query.getSingleResult();
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> findByFullName(String fullName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User u where u.fullName=:fullname",User.class)
                    .setParameter("fullname",fullName)
                    .uniqueResultOptional();
        }
    }

    @Override
    public Optional<Long> createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable((Long) session.save(user));
        }
    }

    @Override
    public Optional<User> updateById(Long id, User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User userToUpdate = session.find(User.class, id);
            userToUpdate.setDay(user.getDay());
            userToUpdate.setFullName(user.getFullName());
            session.saveOrUpdate(userToUpdate);
            session.getTransaction().commit();
            return Optional.of(userToUpdate);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        }
    }
}
