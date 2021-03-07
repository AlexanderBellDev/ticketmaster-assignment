package dev.alexanderbell.ticketmaster.exception;

public class ApiNoContentException extends RuntimeException{
    public ApiNoContentException(String message) {
        super(message);
    }
}
