package org.birthdaynotifier.service.mapper.impl;

import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.repository.entity.User;
import org.birthdaynotifier.service.mapper.UserMapper;
import org.birthdaynotifier.service.model.DayDto;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User source) {
        if (source == null) {
            return null;
        }
        final UserDto userDto = new UserDto();
        userDto.setFullName(source.getFullName());
        DayDto dto = null;
        if (source.getDay() != null) {
            dto = new DayDto();
            dto.setId(source.getDay().getId());
            dto.setDate(source.getDay().getDate());
        }
        userDto.setDay(dto);
        return userDto;
    }

    @Override
    public User toEntity(UserDto source) {
        if (source == null) {
            return null;
        }
        final User user = new User();
        user.setFullName(source.getFullName());
        Day day = null;
        if (source.getDay() != null) {
            day = new Day();
            day.setId(source.getDay().getId());
            day.setDate(source.getDay().getDate());
        }
        user.setDay(day);
        return user;
    }

    @Override
    public List<UserDto> toDto(List<User> source) {
//        List<UserDto> userDtoList = new ArrayList<>();
//
//        for (User user: source) {
//            userDtoList.add(toDto(user));
//        }
//
//        for (int i = 0; i < source.size(); i++) {
//            User element = source.get(i);
//            userDtoList.add(toDto(element));
//        }
//
//        return userDtoList;
        return source.stream().map(this::toDto).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<User> toEntity(List<UserDto> source) {
        return source.stream().map(this::toEntity).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
