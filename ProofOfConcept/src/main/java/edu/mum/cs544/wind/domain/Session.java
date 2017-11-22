package edu.mum.cs544.wind.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Session {
    @Id
    @GeneratedValue
    private long id;

    @JsonFormat
    private LocalDate date;

    @JsonFormat
    private LocalTime startTime;

    @JsonFormat
    private Duration duration;

    private int capacity;
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "counselor_id")
    private Person counselor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Appointment",
            joinColumns = {@JoinColumn(name = "session_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private List<Person> persons;

    public Session() {
        persons = new ArrayList<>();
    }

    public Session(LocalDate date, LocalTime startTime, Duration duration, int capacity, String location, Person counselor) {
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.capacity = capacity;
        this.location = location;
        this.counselor = counselor;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Person getCounselor() {
        return counselor;
    }

    public void setCounselor(Person counselor) {
        this.counselor = counselor;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;

        Session session = (Session) o;

        if (id != session.id) return false;
        if (capacity != session.capacity) return false;
        if (date != null ? !date.equals(session.date) : session.date != null) return false;
        if (startTime != null ? !startTime.equals(session.startTime) : session.startTime != null) return false;
        if (duration != null ? !duration.equals(session.duration) : session.duration != null) return false;
        if (location != null ? !location.equals(session.location) : session.location != null) return false;
        if (counselor != null ? !counselor.equals(session.counselor) : session.counselor != null) return false;
        return persons != null ? persons.equals(session.persons) : session.persons == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (counselor != null ? counselor.hashCode() : 0);
        result = 31 * result + (persons != null ? persons.hashCode() : 0);
        return result;
    }
}
