package org.birthdaynotifier.service.model;


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
                ", day=" + day.getDate() +
                '}';
    }
}
