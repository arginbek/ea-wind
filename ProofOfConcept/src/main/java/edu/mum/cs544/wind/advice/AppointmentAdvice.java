package edu.mum.cs544.wind.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AppointmentAdvice {
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SimpleMailMessage message;

	public void sendSimpleMessage(String to, String subject, String text) {
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

}
