package edu.mum.cs544.wind.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Session {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate date;
    private LocalDate startTime;
    private Duration duration;
    private int capacity;
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "counselor_id")
    private Person counselor;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Session() {
        appointments = new ArrayList<>();
    }

    public Session(LocalDate date, LocalDate startTime, Duration duration, int capacity, String location, Person counselor) {
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
