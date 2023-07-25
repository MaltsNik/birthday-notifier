package database.dao;

import database.dao.entity.Day;

import java.util.List;

public interface DayDao {
    List<Day> getAll();

    Day getById(Long id);

    Day createDay(Day day);

    Day updateById(Long id, Day day);

    void deleteById(Long id);
}
