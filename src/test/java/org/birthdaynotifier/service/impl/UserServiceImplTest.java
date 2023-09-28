package org.birthdaynotifier.service.impl;

import org.birthdaynotifier.repository.UserRepository;
import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.repository.entity.User;
import org.birthdaynotifier.service.DayService;
import org.birthdaynotifier.service.mapper.UserMapper;
import org.birthdaynotifier.service.model.DayDto;
import org.birthdaynotifier.service.model.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DayService dayService;
    @InjectMocks
    UserServiceImpl userService;

    static UserDto userDto;
    static DayDto dayDto;
    static User user;
    static Day day;

    @BeforeAll
    static void init() {
        userDto = new UserDto();
        dayDto = new DayDto();
        user = new User();
        day = new Day();

        dayDto.setDate(LocalDate.of(2023, 8, 19));
        userDto.setFullName("TEST");
        userDto.setDay(dayDto);

        user.setId(0L);
        user.setFullName("TEST");
        user.setDay(day);

        day.setId(0L);
        day.setDate(LocalDate.of(2023, 8, 19));
    }

    @Test
    void getAll() {
        List<User> entityList = Collections.singletonList(user);
        List<UserDto> dtoList = Collections.singletonList(userDto);

        when(userRepository.findAll()).thenReturn(Optional.of(entityList));
        when(userMapper.toDto(entityList)).thenReturn(dtoList);

        List<UserDto> result = userService.getAll();

        verify(userRepository).findAll();
        verify(userMapper).toDto(entityList);

        assertEquals(result, dtoList);
    }

    @Test
    void when_null_throw_exception() {
        when(userRepository.findAll()).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.getAll());
    }

    @Test
    void getById() {
        when(userRepository.findById(0l)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto expected = userService.getById(0L);

        verify(userRepository).findById(0l);
        verify(userMapper).toDto(user);

        assertEquals(expected, userDto);
    }

    @Test
    void getByFullName() {
        when(userRepository.findByFullName("TEST")).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto expected = userService.getByFullName("TEST");

        verify(userRepository).findByFullName("TEST");
        verify(userMapper).toDto(user);

        assertEquals(expected, userDto);
    }

    @Test
    void add() {
        when(userRepository.createUser(user)).thenReturn(Optional.of(0l));
        when(userMapper.toEntity(userDto)).thenReturn(user);

        Long expected = userService.add(userDto);

        verify(userRepository).createUser(user);
        verify(userMapper).toEntity(userDto);

        assertEquals(expected, user.getId());
    }

    @Test
    void changeById() {
        when(userRepository.updateById(0l, user)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto expected = userService.changeById(user.getId(), userDto);

        verify(userRepository).updateById(0l, user);
        verify(userMapper).toDto(user);

        assertEquals(expected, userDto);
    }

    @Test
    void removeById() {
        userService.removeById(0L);
        verify(userRepository).deleteById(0l);
    }
}