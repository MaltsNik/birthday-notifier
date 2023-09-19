package org.birthdaynotifier.controller;

import org.birthdaynotifier.service.DayService;
import org.birthdaynotifier.service.model.DayDto;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/birthday-notifier")

public class DayController {
    private final DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/days")
    public ResponseEntity<List<DayDto>> getAllDays() {
        List<DayDto> day = dayService.getAll();
        return ResponseEntity.ok(day);
    }

    @GetMapping("/days/{id}")
    public ResponseEntity<DayDto> getDayById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(dayService.getById(id));
    }

    @PostMapping("/days")
    public ResponseEntity<Long> createDay(@RequestBody DayDto dayDto) {
        return ResponseEntity.ok(dayService.add(dayDto));
    }

    @PutMapping("/days/{id}")
    public ResponseEntity<DayDto> updateDay(@PathVariable(value = "id") Long id, @RequestBody DayDto dayDto) {
        return ResponseEntity.ok(dayService.changeById(id, dayDto));
    }

    @DeleteMapping("/days/{id}")
    public ResponseEntity<DayDto> deleteDay(@PathVariable(value = "id") Long id) {
        dayService.removeById(id);
        return ResponseEntity.ok().build();
    }
}