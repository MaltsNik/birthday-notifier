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

    private final DayRepository dayRepository;

    public UserRepositoryImpl(SessionFactory sessionFactory, DayRepository dayRepository) {
        this.sessionFactory = sessionFactory;
        this.dayRepository = dayRepository;
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
//            if (day.equals(user.getDay())) {
//                user.setDay(day);
//            } else {
//                day = new Day();
//                user.setDay(day);
//            }
//            return Optional.ofNullable((User) session.save(user));

            Day day = dayRepository.findByDay(user.getDay().getDate()).orElseThrow();

            System.out.println(day);
            if (user.getDay().getDate().equals(day.getDate())) {
                user.setDay(day);
            }

            dayRepository.createDay(user.getDay());


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

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory1 = configuration.buildSessionFactory();
        UserRepository userRepository = new UserRepositoryImpl(sessionFactory1, new DayRepositoryImpl(sessionFactory1));

//        System.out.println(userRepository.findAll().get());
//        System.out.println("___________________________________________________________________");
//        userRepository.createUser(new User(1l, "Иванов Ваня" +
//                ""),new Day(12l,LocalDate.of(2023,8,4)));
//
//        // System.out.println(userRepository.updateById(5l, new User(9l, "Сиданов Иван")));
//        System.out.println(userRepository.findAll().get());


        final User user = new User();
        final Day day = new Day();

        day.setDate(LocalDate.now());
        user.setFullName("Test 2");
        user.setDay(day);
        System.out.println(userRepository.createUser(user));

    }
}
