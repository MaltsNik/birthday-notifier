package org.birthdaynotifier.service.mapper;

import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.service.model.DayDto;

import java.util.List;

public interface DayMapper {
    DayDto toDto(Day source);

    Day toEntity(DayDto source);

    List<DayDto> toDto(List<Day> source);

    List<Day> toEntity(List<DayDto> source);
}
