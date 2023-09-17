package org.birthdaynotifier.service.impl;

import org.birthdaynotifier.repository.DayRepository;
import org.birthdaynotifier.repository.entity.Day;
import org.birthdaynotifier.service.DayService;
import org.birthdaynotifier.service.mapper.DayMapper;
import org.birthdaynotifier.service.model.DayDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DayServiceImpl implements DayService {

    private final DayRepository dayRepository;

    private final DayMapper dayMapper;

    public DayServiceImpl(DayRepository dayRepository, DayMapper dayMapper) {
        this.dayRepository = dayRepository;
        this.dayMapper = dayMapper;
    }

    @Override
    public List<DayDto> getAll() {
        Optional<List<Day>> optionalDays = dayRepository.findAll();
        List<Day> dayList = optionalDays.orElseThrow(() -> new RuntimeException("days not found"));
        return dayMapper.toDto(dayList);
    }

    @Override
    public DayDto getById(Long id) {
        Optional<Day> optionalDay = dayRepository.findById(id);
        Day day = optionalDay.orElseThrow(() -> new RuntimeException("day not found"));
        return dayMapper.toDto(day);
    }

    @Override
    public Optional<DayDto> getByDate(LocalDate date) {
        return dayRepository.findByDay(date).map(dayMapper::toDto);
    }

    @Override
    public Long add(DayDto day) {
//        Optional<DayDto> optDay = getByDate(day.getDate());
//        if(optDay.isPresent()){
//
//            throw new RuntimeException("such a day exists");
//        }
        return dayRepository.createDay(dayMapper.toEntity(day)).orElseThrow(() -> new RuntimeException("Couldn't save day"));
    }

    @Override
    public DayDto changeById(Long id, DayDto dayDto) {
        Optional<Day> optionalDay = dayRepository.updateById(id, dayMapper.toEntity(dayDto));
        Day day = optionalDay.orElseThrow(() -> new RuntimeException("error while updating"));
        return dayMapper.toDto(day);
    }

    @Override
    public void removeById(Long id) {
        dayRepository.deleteById(id);
    }
}
