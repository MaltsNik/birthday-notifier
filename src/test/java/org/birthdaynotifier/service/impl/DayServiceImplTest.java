package org.birthdaynotifier.service.impl;

import org.birthdaynotifier.repository.DayRepository;
import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.service.mapper.DayMapper;
import org.birthdaynotifier.service.model.DayDto;
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
class DayServiceImplTest {
    @Mock
    private DayMapper dayMapper;
    @Mock
    private DayRepository dayRepository;
    @InjectMocks
    DayServiceImpl dayService;
    static DayDto dayDto;
    static Day day;

    @BeforeAll
    static void init() {
        day = new Day();
        dayDto = new DayDto();

        dayDto.setId(0L);
        dayDto.setDate(LocalDate.of(2023, 8, 20));
        day.setId(0L);
        day.setDate(LocalDate.of(2023, 8, 20));

    }

    @Test
    void getAll() {
        List<Day> entityDayList = Collections.singletonList(day);
        List<DayDto> dayDtoList = Collections.singletonList(dayDto);

        when(dayRepository.findAll()).thenReturn(Optional.of(entityDayList));
        when(dayMapper.toDto(entityDayList)).thenReturn(dayDtoList);

        List<DayDto> expected = dayService.getAll();

        verify(dayRepository).findAll();
        verify(dayMapper).toDto(entityDayList);

        assertEquals(expected, dayDtoList);
    }

    @Test
    void when_null_throw_exception() {
        when(dayRepository.findAll()).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> dayService.getAll());
    }

    @Test
    void getById() {
        when(dayRepository.findById(day.getId())).thenReturn(Optional.of(day));
        when(dayMapper.toDto(day)).thenReturn(dayDto);

        DayDto expected = dayService.getById(0L);

        verify(dayRepository).findById(0L);
        verify(dayMapper).toDto(day);

        assertEquals(expected, dayDto);
    }

    @Test
    void getByDate() {
        when(dayRepository.findByDay(day.getDate())).thenReturn(Optional.of(day));
        when(dayMapper.toDto(day)).thenReturn(dayDto);

        Optional<DayDto> expected = dayService.getByDate(day.getDate());

        verify(dayRepository).findByDay(day.getDate());
        verify(dayMapper).toDto(day);

        assertEquals(expected.get(), dayDto);
    }

    @Test
    void add() {
        when(dayRepository.createDay(day)).thenReturn(Optional.of(0L));
        when(dayMapper.toEntity(dayDto)).thenReturn(day);

        Long expected = dayService.add(dayDto);

        verify(dayRepository).createDay(day);
        verify(dayMapper).toEntity(dayDto);

        assertEquals(expected, day.getId());
    }

    @Test
    void changeById() {
        when(dayRepository.updateById(0L, day)).thenReturn(Optional.of(day));
        when(dayMapper.toDto(day)).thenReturn(dayDto);

        DayDto expected = dayService.changeById(0l, dayDto);

        verify(dayRepository).updateById(0L, day);
        verify(dayMapper).toDto(day);

        assertEquals(expected, dayDto);
    }

    @Test
    void removeById() {
        dayService.removeById(0L);
        verify(dayRepository).deleteById(0L);
    }
}