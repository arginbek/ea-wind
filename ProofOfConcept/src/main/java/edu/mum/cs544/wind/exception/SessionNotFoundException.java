package edu.mum.cs544.wind.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String s) {
        super(s);
    }
}
