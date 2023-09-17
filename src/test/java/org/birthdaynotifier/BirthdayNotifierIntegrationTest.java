package org.birthdaynotifier;

import org.birthdaynotifier.repository.DayRepository;
import org.birthdaynotifier.repository.UserRepository;
import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.repository.entity.User;
import org.birthdaynotifier.repository.impl.DayRepositoryImpl;
import org.birthdaynotifier.repository.impl.UserRepositoryImpl;
import org.birthdaynotifier.service.DayService;
import org.birthdaynotifier.service.UserService;
import org.birthdaynotifier.service.impl.DayServiceImpl;
import org.birthdaynotifier.service.impl.UserServiceImpl;
import org.birthdaynotifier.service.mapper.DayMapper;
import org.birthdaynotifier.service.mapper.UserMapper;
import org.birthdaynotifier.service.mapper.impl.DayMapperImpl;
import org.birthdaynotifier.service.mapper.impl.UserMapperImpl;
import org.birthdaynotifier.service.model.DayDto;
import org.birthdaynotifier.service.model.UserDto;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Testcontainers
public class BirthdayNotifierIntegrationTest {
    @Container
    private static final PostgreSQLContainer container = new PostgreSQLContainer<>("postgres");
    private static UserService userService;
    private static UserMapper userMapper;
    private static DayService dayService;
    private static DayMapper dayMapper;
    private static UserRepository userRepository;
    private static DayRepository dayRepository;

    @BeforeAll
    private static void init() {
        container.start();
        Properties properties = new Properties();
        properties.put("hibernate.connection.url", container.getJdbcUrl());
        properties.put("hibernate.connection.username", container.getUsername());
        properties.put("hibernate.connection.password", container.getPassword());
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        SessionFactory sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Day.class)
                .buildSessionFactory();
        userMapper = new UserMapperImpl();
        dayMapper = new DayMapperImpl();
        userRepository = new UserRepositoryImpl(sessionFactory);
        dayRepository = new DayRepositoryImpl(sessionFactory);
        dayService = new DayServiceImpl(dayRepository, dayMapper);
        userService = new UserServiceImpl(userMapper, userRepository, dayService);
    }

    @Test
    void integrationGetAllTest() {
        DayDto dayDto = new DayDto();
        dayDto.setDate(LocalDate.of(2023, 7, 11));
        Long addedDayId = dayService.add(dayDto);
        System.out.println(addedDayId);
        dayDto.setId(addedDayId);
        UserDto userDto = new UserDto("Иванов Иван", dayDto);
        Long addedUserId = userService.add(userDto);
        List<UserDto> dtoList = userService.getAll();
        Assertions.assertEquals(Collections.singletonList(userDto), dtoList);
    }

    @Test
    void integrationGetByIdTest() {
        DayDto dayDto = new DayDto();
        dayDto.setDate(LocalDate.of(2023, 10, 10));
        Long addDayId = dayService.add(dayDto);
        System.out.println(addDayId);
        dayDto.setId(addDayId);
        UserDto userDto = new UserDto("Иванов Иван", dayDto);
        Long addUserId = userService.add(userDto);
        UserDto expected = userService.getById(addUserId);
        Assertions.assertEquals(expected,userDto);
    }

    @AfterAll
    private static void drop() {
        container.stop();
    }

}
