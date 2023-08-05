package org.birthdaynotifier.service.mapper.impl;

import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.service.mapper.DayMapper;
import org.birthdaynotifier.service.model.DayDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DayMapperImpl implements DayMapper {
    @Override
    public DayDto toDto(Day source) {
        if (source == null) {
            return null;
        }
        final DayDto dayDto = new DayDto();
        dayDto.setDate(source.getDate());
        return dayDto;
    }

    @Override
    public Day toEntity(DayDto source) {
        if (source == null) {
            return null;
        }
        final Day day = new Day();
        day.setDate(source.getDate());
        return day;
    }

    @Override
    public List<DayDto> toDto(List<Day> source) {
        return source.stream().map(this::toDto).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<Day> toEntity(List<DayDto> source) {
        return source.stream().map(this::toEntity).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
