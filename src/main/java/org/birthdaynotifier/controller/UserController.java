package org.birthdaynotifier.controller;

import org.birthdaynotifier.service.UserService;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/birthday-notifier")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
    @GetMapping("/users/fullName/{fullName}")
    public ResponseEntity<UserDto> getUserByFullName(@PathVariable(value = "fullName") String fullName){
        return ResponseEntity.ok(userService.getByFullName(fullName));
    }

    @PostMapping("/users")
    public ResponseEntity<Long> createUser(@RequestBody UserDto request) {
        return ResponseEntity.ok(userService.add(request));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable (value = "id") Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.changeById(id, userDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        userService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
