package org.birthdaynotifier.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "day")
    private LocalDate date;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day")
    private Set<User> users;

    public Day() {
    }

    public Day(Long id, LocalDate date, Set<User> users) {
        this.id = id;
        this.date = date;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", day=" + date +
                ", users=" + users +
                '}';
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Day day = (Day) o;
    return Objects.equals(id, day.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
