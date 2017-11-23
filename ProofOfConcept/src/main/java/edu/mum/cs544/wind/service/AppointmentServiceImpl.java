package edu.mum.cs544.wind.service;


import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.repository.PersonRepository;
import edu.mum.cs544.wind.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private static final LocalDateTime FINAL_EDITABLE_DATE = LocalDateTime.now().plusDays(2);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public String addAppointment(Long personId, Long sessionId) {
        StringBuilder response = new StringBuilder();
        response.append("Booking ");

        Person person = personRepository.findOne(personId);
        Session session = sessionRepository.findOne(sessionId);

        if (person == null || session == null) {
            response.append("Failed: Invalid person and/or session, please choose another person/session.");
        } else {
            if (session.getDate().isBefore(LocalDate.now()) || session.getStartTime().isBefore(LocalTime.now())) {
                response.append("Failed: Session is already finished, please choose another session.");
            } else if (session.isFull()) {
                response.append("Failed: Session is full, please choose another session.");
            } else if (session.getPersons().contains(person) || person.getSessions().contains(session)) {
                response.append("Failed: Duplicate person/session, please choose another person/session.");
            } else {
                person.addSession(session);
                session.addPerson(person);
                personRepository.save(person);
                sessionRepository.save(session);
                response.append("Success: You have successfully booked an appointment.");
            }
        }

        return response.toString();
    }

    @Override
    public String removeAppointment(Long personId, Long sessionId, boolean isAdmin) {
        StringBuilder response = new StringBuilder();
        response.append("Cancel Booking ");

        Person person = personRepository.findOne(personId);
        Session session = sessionRepository.findOne(sessionId);

        if (person == null || session == null) {
            response.append("Failed: Invalid person and/or session, please choose another person/session.");
        } else {
            LocalDateTime sessionTime = LocalDateTime.of(session.getDate(), session.getStartTime());
            if (sessionTime.isBefore(FINAL_EDITABLE_DATE) && !isAdmin) {
                response.append("Failed: Session starts in less than 48 hours, please ask an admin to delete it.");
            } else if (!session.getPersons().contains(person) || !person.getSessions().contains(session)) {
                response.append("Failed: Person is not in the session, please choose another person/session.");
            } else {
                person.removeSession(session);
                session.removePerson(person);
                personRepository.save(person);
                sessionRepository.save(session);
                response.append("Success: You have successfully cancel an appointment.");
            }
        }

        return response.toString();
    }

}
