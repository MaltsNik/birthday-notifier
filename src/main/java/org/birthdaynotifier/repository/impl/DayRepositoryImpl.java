package org.birthdaynotifier.repository.impl;

import org.birthdaynotifier.repository.DayRepository;
import org.birthdaynotifier.repository.entity.Day;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
      return session.createQuery("from Day d where d.date = :date", Day.class)
              .setParameter("date", date)
              .uniqueResultOptional();
    }
  }

  @Override
  public Optional<Long> createDay(Day day) {
    try (Session session = sessionFactory.openSession()) {
      return Optional.ofNullable(((Long) session.save(day)));
    }
  }

  @Override
  public Optional<Day> updateById(Long id, Day day) {
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      Day dayToUpdate = session.find(Day.class, id);
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
}