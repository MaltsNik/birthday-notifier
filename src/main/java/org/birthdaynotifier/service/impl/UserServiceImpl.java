package org.birthdaynotifier.service.impl;

import org.birthdaynotifier.repository.UserRepository;
import org.birthdaynotifier.repository.entity.User;
import org.birthdaynotifier.service.DayService;
import org.birthdaynotifier.service.UserService;
import org.birthdaynotifier.service.mapper.UserMapper;
import org.birthdaynotifier.service.model.DayDto;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final DayService dayService;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
                           DayService dayService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.dayService = dayService;
    }

    @Override
    public List<UserDto> getAll() {
        Optional<List<User>> optionalUsers = userRepository.findAll();
        List<User> users = optionalUsers.orElseThrow(() -> new RuntimeException("No users found"));
        return userMapper.toDto(users);
    }

    @Override
    public List<UserDto> getAllByDate(LocalDate day) {
        DayDto dayDto = dayService.getByDate(day).orElseThrow(() -> new RuntimeException("Day not found"));
        return dayDto.getUserDtoList();
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("No user found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getByFullName(String fullName) {
        return userRepository.findByFullName(fullName).map(userMapper::toDto).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public Long add(UserDto userDto) {
        enrichWithDay(userDto);
        return userRepository.createUser(userMapper.toEntity(userDto)).
                orElseThrow(() -> new RuntimeException("Error while saving"));
    }

    @Override
    public UserDto changeById(Long id, UserDto userDto) {
        enrichWithDay(userDto);
        Optional<User> optionalUser = userRepository.updateById(id, userMapper.toEntity(userDto));
        User user = optionalUser.orElseThrow(() -> new RuntimeException("error while updating"));
        return userMapper.toDto(user);
    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    private void enrichWithDay(UserDto userDto) {
        Optional<DayDto> optionalDayDto = dayService.getByDate(userDto.getDay().getDate());
        if (optionalDayDto.isEmpty()) {
            Long addedDayId = dayService.add(userDto.getDay());
            userDto.getDay().setId(addedDayId);
        } else {
            userDto.getDay().setId(optionalDayDto.get().getId());
        }
    }
}