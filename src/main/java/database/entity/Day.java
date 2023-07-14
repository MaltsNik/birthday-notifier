package database.entity;

import java.time.LocalDate;

public class Day {
    private Long id;
    private LocalDate date;
    private Long userId;

    public Day() {
    }

    public Day(Long id, LocalDate date, Long userId) {
        this.id = id;
        this.date = date;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
