package org.birthdaynotifier.service.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;
import java.util.Objects;

public class DayDto {
    private Long id;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate date;

    public DayDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DayDto(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayDto dayDto = (DayDto) o;
        return Objects.equals(id, dayDto.id) && Objects.equals(date, dayDto.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        return "DayDto{" +
                "id=" + id +
                "date=" + date +
                '}';
    }
}
