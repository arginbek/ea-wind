package edu.mum.cs544.wind.service;

public interface AppointmentService {

    String addAppointment(Long personId, Long sessionId);

    String removeAppointment(Long personId, Long sessionId, boolean isAdmin);
}
