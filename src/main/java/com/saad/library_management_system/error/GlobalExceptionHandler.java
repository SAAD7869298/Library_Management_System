package com.saad.library_management_system.error;

import com.saad.library_management_system.error.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleBookNotAvailableException(BookNotAvailableException exception) {
        log.error("Book Not Available Exception with code: [{}] and message: [{}]",
                BookNotAvailableException.STATUS_CODE, BookNotAvailableException.MESSAGE);

        return new ResponseEntity<>(
                new ErrorResponse(BookNotAvailableException.STATUS_CODE, BookNotAvailableException.MESSAGE,
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

    @ExceptionHandler(BorrowNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBorrowNotFoundExceptionException(BorrowNotFoundException exception) {
        log.error("Borrow Not Found Exception Exception with code: [{}] and message: [{}]",
                BorrowNotFoundException.STATUS_CODE, BorrowNotFoundException.MESSAGE);

        return new ResponseEntity<>(
                new ErrorResponse(BorrowNotFoundException.STATUS_CODE, BorrowNotFoundException.MESSAGE,
                        exception.getDescription(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder message = new StringBuilder();
        StringBuilder description = new StringBuilder();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            log.error("Field: [{}] with message: [{}]", fieldError.getField(), fieldError.getDefaultMessage());
            message.append(fieldError.getField()).append(",");
            description.append(fieldError.getDefaultMessage()).append(",");
        });

        return new ResponseEntity<>(
                new ErrorResponse(10002, message.toString() , description.toString() ,
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
