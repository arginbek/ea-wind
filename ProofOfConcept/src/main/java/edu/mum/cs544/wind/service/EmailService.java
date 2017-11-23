package edu.mum.cs544.wind.service;

public interface EmailService {
	public void notifyByEmail(String destinationEmail, String subject, String text);
}
