package edu.mum.cs544.wind.exception;

public class SessionDeletePastException extends RuntimeException {
    public SessionDeletePastException(String s) {
        super(s);
    }
}
