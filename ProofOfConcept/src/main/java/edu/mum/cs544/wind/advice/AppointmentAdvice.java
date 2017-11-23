package edu.mum.cs544.wind.advice;

import java.time.temporal.TemporalUnit;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.repository.PersonRepository;
import edu.mum.cs544.wind.repository.SessionRepository;
import edu.mum.cs544.wind.service.EmailService;

@Component
@Aspect
public class AppointmentAdvice {

	@Autowired
	private EmailService emailService;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private SessionRepository sessionRepository;

	@AfterReturning(pointcut = "execution(* edu.mum.cs544.wind.service.AppointmentServiceImpl.addAppointment(..)) && args(personId, sessionId)", returning = "message")

	public void notifyAppointmentCreation(String message, Long personId, Long sessionId) {
		Person person = personRepository.findOne(personId);
		Session session = sessionRepository.findOne(sessionId);
		if (message.equals("Booking Success: You have successfully booked an appointment.")) {

			String counselorEmail = session.getCounselor().getEmail();
			String customerEmail = person.getEmail();
			String customerSuccessMessage = "Dear " + person.getFirstName() + ",\r\n\r\n"
					+ "Congratulations, You have successfully registered to the TM retreat session offered to"
					+ " all our meditators who are interested in rest and "
					+ "rejuvenation after a long period of stress." + "Orientation will " + "be held by the counslor "
					+ session.getCounselor().getFirstName() + " " + session.getCounselor().getLastName() + " be in "
					+ session.getLocation() + " at " + session.getStartTime() + " on " + session.getDate() + "."
					+ "This meeting will be short but is important." + "The session will last for "
					+ session.getDuration().toHours() + "hours.\r\n" + "\r\n" + "Best wishes,\r\n\r\n" + ""
					+ "Wind Team";
			String counselorMessage = "Dear " + session.getCounselor().getFirstName() + ",\r\n\r\n"
					+ "You have new registartion to the TM retreat session which " + "will be held on "
					+ session.getDate() + " be in " + session.getLocation() + " at " + session.getStartTime()
					+ ". The current capacity of this session is : " + session.getCapacity() + ",\r\n\r\n"
					+ "Wind Team";

			emailService.notifyByEmail(counselorEmail, message, customerSuccessMessage);
			emailService.notifyByEmail(customerEmail, "New registration notification", counselorMessage);

		} else if (message.equals("Booking Failed: Session is full, please choose another session.")) {
			String customerFailureMessage = "Hi " + person.getLastName() + ",\r\n"
					+ "Our team thanks you for trying to register to our retreat session."
					+ "Infotunatly this session is full.\r\n"

					+ "Please contact our customer service if you need more information windteamrocks@gmail.com!\r\n"
					+ "\r\n" + "Best wishes,\r\n\r\n" + "Wind Team";

			emailService.notifyByEmail(person.getEmail(), message, customerFailureMessage);
		}
	}


	@AfterReturning(pointcut = "execution(* edu.mum.cs544.wind.service.AppointmentServiceImpl.removeAppointment(..)) && args(personId, sessionId, isAdmin)", returning = "message")
	public void notifyAppointmentUpdate(String message, Long personId, Long sessionId, boolean isAdmin) {
		Person person = personRepository.findOne(personId);
		Session session = sessionRepository.findOne(sessionId);
		if (message.equals("Cancel Booking Success: You have successfully cancel an appointment.")) {
			String counselorEmail = session.getCounselor().getEmail();
			String customerEmail = person.getEmail();
			String customerSuccessMessage = "Dear " + person.getFirstName() + ",\r\n\r\n"
					+ "Your booking of the session held by the counslor " + session.getCounselor().getFirstName() + " "
					+ session.getCounselor().getLastName() + " on " + session.getDate() + "." + "\r\n" + "\r\n"
					+ "Best wishes,\r\n\r\n" + "" + "Wind Team";
			String counselorMessage = "Dear " + session.getCounselor().getFirstName() + ",\r\n\r\n"
					+ "One appointment has been canceled for the session of " + session.getDate()
					+ ". The current capacity of this session is : " + session.getCapacity() + ",\r\n\r\n"
					+ "Wind Team";

			emailService.notifyByEmail(counselorEmail, message, customerSuccessMessage);
			emailService.notifyByEmail(customerEmail, "New registration notification", counselorMessage);
		}
	}

}
