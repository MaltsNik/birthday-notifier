package org.birthdaynotifier.service;

import org.birthdaynotifier.service.model.DayDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayService {
    List<DayDto> getAll();

    DayDto getById(Long id);

    Optional<DayDto> getByDate(LocalDate date);

    Long add(DayDto day);

    DayDto changeById(Long id, DayDto day);

    void removeById(Long id);

}
