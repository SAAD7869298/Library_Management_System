package com.saad.library_management_system.error;

import com.saad.library_management_system.error.exception.BookNotFoundException;
import com.saad.library_management_system.error.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException exception) {
        log.error("Book Not Found Exception with code: [{}] and message: [{}]",
                BookNotFoundException.STATUS_CODE, BookNotFoundException.MESSAGE);

        return new ResponseEntity<>(
                new ErrorResponse(BookNotFoundException.STATUS_CODE, BookNotFoundException.MESSAGE,
                        exception.getDescription(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        log.error("User Not Found Exception with code: [{}] and message: [{}]",
                UserNotFoundException.STATUS_CODE, UserNotFoundException.MESSAGE);

        return new ResponseEntity<>(
                new ErrorResponse(UserNotFoundException.STATUS_CODE, UserNotFoundException.MESSAGE,
                        exception.getDescription(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}
