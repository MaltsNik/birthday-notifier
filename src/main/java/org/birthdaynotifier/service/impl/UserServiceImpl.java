package org.birthdaynotifier.service.impl;

import org.birthdaynotifier.repository.UserRepository;
import org.birthdaynotifier.repository.entity.User;
import org.birthdaynotifier.service.UserService;
import org.birthdaynotifier.service.mapper.UserMapper;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        Optional<List<User>> optionalUsers = userRepository.findAll();
        List<User> users = optionalUsers.orElseThrow(() -> new RuntimeException("No users found"));
        return userMapper.toDto(users);
    }

    @Override
    public UserDto getById(Long id) {
        return null;
    }

    @Override
    public Long add(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto changeById(Long id) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }
}
