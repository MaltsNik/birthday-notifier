package org.birthdaynotifier.service.mapper;

import org.birthdaynotifier.repository.entity.User;
import org.birthdaynotifier.service.model.UserDto;

import java.util.List;

public interface UserMapper {
    UserDto toDto(User source);

    User toEntity(UserDto source);

    List<UserDto> toDto(List<User> source);

    List<User> toEntity(List<UserDto> source);

}
