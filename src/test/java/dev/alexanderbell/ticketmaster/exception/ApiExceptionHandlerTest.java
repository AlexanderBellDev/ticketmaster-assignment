package dev.alexanderbell.ticketmaster.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ApiExceptionHandlerTest {
    @InjectMocks
    ApiExceptionHandler apiExceptionHandler;

    @Test
    void handleApiBadRequestException() {
        ResponseEntity<Object> badRequestException = apiExceptionHandler.handleApiRequestException(new ApiBadRequestException("Invalid Args"));
        assertEquals(HttpStatus.BAD_REQUEST, badRequestException.getStatusCode());
        assertTrue(Objects.requireNonNull(badRequestException.getBody()).toString().contains("ApiException(message=Invalid Args, httpStatus=400 BAD_REQUEST, timestamp="));
    }

    @Test
    void handleApiNoContentRequestException() {
        ResponseEntity<Object> badRequestException = apiExceptionHandler.handleApiRequestException(new ApiNoContentException("No content for args"));
        assertEquals(HttpStatus.NO_CONTENT, badRequestException.getStatusCode());
        assertTrue(Objects.requireNonNull(badRequestException.getBody()).toString().contains("ApiException(message=No content for args, httpStatus=204 NO_CONTENT, timestamp="));
    }

    @Test
    void handleApiNotFoundRequestException() {
        ResponseEntity<Object> badRequestException = apiExceptionHandler.handleApiRequestException(new ApiNotFoundException("artist doesn't exist"));
        assertEquals(HttpStatus.NOT_FOUND, badRequestException.getStatusCode());
        assertTrue(Objects.requireNonNull(badRequestException.getBody()).toString().contains("ApiException(message=artist doesn't exist, httpStatus=404 NOT_FOUND, timestamp="));
    }


}