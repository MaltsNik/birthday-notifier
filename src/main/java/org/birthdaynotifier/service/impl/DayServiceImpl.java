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
    public Long add(DayDto dayDto) {
        Optional<Day> day = dayRepository.findByDay(dayDto.getDate());
        if (day.isEmpty()) {
            return dayRepository.createDay(dayMapper.toEntity(dayDto))
                    .orElseThrow(() -> new RuntimeException("Error while creating day"));
        }
        throw new RuntimeException("Day is already exist");
  }


    @Override
    public DayDto changeById(Long id, DayDto dayDto) {
        Optional<Day> day = dayRepository.findByDay(dayDto.getDate());
        if (day.isEmpty()) {
            return dayMapper.toDto(
                    dayRepository.updateById(id, dayMapper.toEntity(dayDto))
                            .orElseThrow(() -> new RuntimeException(""))
            );
        }
        throw new RuntimeException("Day is already exist");
  }

  @Override
  public void removeById(Long id) {
    dayRepository.deleteById(id);
  }

}
