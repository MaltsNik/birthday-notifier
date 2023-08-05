package org.birthdaynotifier.repository.impl;

import org.birthdaynotifier.repository.DayRepository;
import org.birthdaynotifier.repository.entity.Day;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DayRepositoryImpl implements DayRepository {
    private final SessionFactory sessionFactory;

    public DayRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<List<Day>> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.createQuery("from Day ", Day.class).getResultList());
        }
    }

    @Override
    public Optional<Day> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Day.class, id));
        }
    }

    @Override
    public Optional<Day> findByDay(LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.createQuery("from Day d where d.date = date", Day.class).getSingleResult());
        }
    }

    @Override
    public Optional<Day> createDay(Day day) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(((Day) session.save(day.getDate())));
        }
    }

    @Override
    public Optional<Day> updateById(Long id, Day day) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Day dayToUpdate = session.find(Day.class, id);
            dayToUpdate.setUsers(day.getUsers());
            dayToUpdate.setDate(day.getDate());
            session.saveOrUpdate(dayToUpdate);
            session.getTransaction().commit();
            return Optional.of(dayToUpdate);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Day day = session.find(Day.class, id);
            session.remove(day);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        DayRepositoryImpl dayRepository = new DayRepositoryImpl(configuration.buildSessionFactory());

        System.out.println(dayRepository.findAll().get());
        System.out.println(dayRepository.findById(1l).get());
        dayRepository.deleteById(10l);
        dayRepository.deleteById(11l);
        // System.out.println(dayRepository.createDay(new Day(1l, LocalDate.of(2022,11,1))));
        //System.out.println(dayRepository.findAll().get());
        System.out.println();
    }
}
