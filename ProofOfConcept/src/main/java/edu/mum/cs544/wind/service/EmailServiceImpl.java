package edu.mum.cs544.wind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private SimpleMailMessage mailMessage;

	@Async
	@Override
	public void notifyByEmail(String destinationEmail, String subject, String text) {

		mailMessage.setTo(destinationEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(text);

		emailSender.send(mailMessage);
	}

}
