package org.birthdaynotifier.repository.impl;

import org.birthdaynotifier.repository.UserRepository;
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

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory1 = configuration.buildSessionFactory();
        UserRepository userRepository = new UserRepositoryImpl(sessionFactory1);
        User user1 = new User();
        user1.setFullName("Иванов Иван");
        user1.setDate(LocalDate.of(2020, 7, 10));
        //userRepository.createUser(user1);
       // System.out.println(userRepository.createUser(user1));
        User user2= new User();
        user2.setFullName("Иванов Иван Иванович");
        user2.setDate(LocalDate.of(2010,7,10));
        System.out.println(userRepository.updateById(1l,user2));


    }
}
