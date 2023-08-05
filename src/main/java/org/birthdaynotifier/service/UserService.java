package org.birthdaynotifier.service;

import org.birthdaynotifier.service.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    Long add(UserDto userDto);

    UserDto changeById(Long id);

    void removeById(Long id);

}
