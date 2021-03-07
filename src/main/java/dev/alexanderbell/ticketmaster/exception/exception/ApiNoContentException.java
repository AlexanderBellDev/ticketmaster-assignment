package dev.alexanderbell.ticketmaster.exception.exception;

public class ApiNoContentException extends RuntimeException{
    public ApiNoContentException(String message) {
        super(message);
    }
}
