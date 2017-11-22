package edu.mum.cs544.wind.exception;

public class SessionUpdatePastException extends RuntimeException {
    public SessionUpdatePastException(String s) {
    	super(s);
    }
}
