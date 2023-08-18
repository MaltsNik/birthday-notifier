package org.birthdaynotifier.service.model;


import java.time.LocalDate;

public class UserDto {

    private String fullName;
    private LocalDate date;

    public UserDto() {
    }

    public UserDto(String fullName, LocalDate date) {
        this.fullName = fullName;
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
