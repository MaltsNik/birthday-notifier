package org.birthdaynotifier.service.mapper.impl;

import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.repository.entity.User;
import org.birthdaynotifier.service.mapper.DayMapper;
import org.birthdaynotifier.service.model.DayDto;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DayMapperImpl implements DayMapper {
    @Override
    public DayDto toDto(Day source) {
        if (source == null) {
            return null;
        }
        final DayDto dayDto = new DayDto();
        dayDto.setId(source.getId());
        dayDto.setDate(source.getDate());

        if (source.getUsers() != null && !source.getUsers().isEmpty()) {
            List<UserDto> userDtoList = new ArrayList<>();
            for (User user : source.getUsers()) {
                UserDto userDto = new UserDto();
                userDto.setFullName(user.getFullName());
                userDtoList.add(userDto);
            }
            dayDto.setUserDtoList(userDtoList);
        }

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
