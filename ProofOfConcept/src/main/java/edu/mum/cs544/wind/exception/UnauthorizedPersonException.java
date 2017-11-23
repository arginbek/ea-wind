package edu.mum.cs544.wind.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "UNAUTHORIZED")
public class UnauthorizedPersonException extends RuntimeException {
    public UnauthorizedPersonException(String s) {
        super(s);
    }
}
