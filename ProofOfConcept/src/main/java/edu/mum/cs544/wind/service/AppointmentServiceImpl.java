package edu.mum.cs544.wind.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.repository.PersonRepository;
import edu.mum.cs544.wind.repository.SessionRepository;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private SessionRepository sessionRepository;


	@Override
	public String addAppointment(Long personId, Long sessionId) {
		String message = "Currently we can't book your appointment";
		Person person = personRepository.findOne(personId);
		Session session = sessionRepository.findOne(sessionId);
		if(person != null && session != null) {
			if(!session.isFull() && !session.getPersons().contains(person) && !person.getSessions().contains(session)) {
				person.addSession(session);
				session.addPerson(person);
				personRepository.save(person);
				sessionRepository.save(session);
				message = "You successfully booked";
			}
		}
		
		return message;
	}

}
