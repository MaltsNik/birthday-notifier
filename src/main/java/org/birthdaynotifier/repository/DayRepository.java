package org.birthdaynotifier.repository;

import org.birthdaynotifier.repository.entity.Day;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayRepository {
    Optional<List<Day>> findAll();

    Optional<Day> findById(Long id);

    Optional<Day> findByDay(LocalDate date);

    Optional<Long> createDay(Day day);

    Optional<Day> updateById(Long id, Day day);

    void deleteById(Long id);
}
