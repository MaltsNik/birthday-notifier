package database.entity;

import java.util.Calendar;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String fullName;
    private final int age;
    private final Calendar calendar;

    public User(UUID uuid, String fullName, int age, Calendar calendar) {
        this.id = uuid;
        this.fullName = fullName;
        this.age = age;
        this.calendar = calendar;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public Calendar getCalendar() {
        return calendar;
    }
}
