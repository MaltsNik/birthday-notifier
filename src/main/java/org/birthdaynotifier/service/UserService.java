package org.birthdaynotifier.service;

import org.birthdaynotifier.service.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    UserDto getByFullName(String fullName);

    Long add(UserDto userDto);

    UserDto changeById(Long id, UserDto userDto);

    void removeById(Long id);

}
