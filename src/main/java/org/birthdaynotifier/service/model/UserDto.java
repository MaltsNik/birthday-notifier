package org.birthdaynotifier.service.model;


import java.util.Objects;

public class UserDto {

    private String fullName;
    private DayDto day;

    public UserDto() {
    }

    public UserDto(String fullName, DayDto day) {
        this.fullName = fullName;
        this.day = day;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public DayDto getDay() {
        return day;
    }

    public void setDay(DayDto day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "fullName='" + fullName + '\'' +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(fullName, userDto.fullName) && Objects.equals(day, userDto.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, day);
    }
}
