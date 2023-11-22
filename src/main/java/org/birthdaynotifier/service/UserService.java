package org.birthdaynotifier.service;

import org.birthdaynotifier.service.model.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    List<UserDto> getAllByDate(LocalDate day);

    UserDto getById(Long id);

    UserDto getByFullName(String fullName);

    Long add(UserDto userDto);

    UserDto changeById(Long id, UserDto userDto);

    void removeById(Long id);

}
