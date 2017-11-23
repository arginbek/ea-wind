package edu.mum.cs544.wind.service;

public interface EmailService {
    void notifyByEmail(String destinationEmail, String subject, String text);
}
