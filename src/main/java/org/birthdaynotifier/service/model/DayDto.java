package org.birthdaynotifier.service.model;

import java.time.LocalDate;

public class DayDto {

    private LocalDate date;

    public DayDto() {
    }

    public DayDto(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DayDto{" +
                "date=" + date +
                '}';
    }
}
