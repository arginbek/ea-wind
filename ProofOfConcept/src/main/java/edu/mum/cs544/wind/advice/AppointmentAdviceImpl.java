package edu.mum.cs544.wind.advice;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import edu.mum.cs544.wind.domain.Session;

@Component
@Aspect
public class AppointmentAdviceImpl {
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SimpleMailMessage message;

	@Async
	@AfterReturning(pointcut = "execution(* edu.mum.cs544.wind.service.SessionServiceImpl.updateSession(..))", returning = "sessionUpdated")
	public void sendSimpleMessage(Session sessionUpdated) {
		String to;
		String subject;
		String text;
		message.setTo("fa.oujeddi@gmail.com");
		message.setSubject("test");
		message.setText("test");
		emailSender.send(message);
	}

}
