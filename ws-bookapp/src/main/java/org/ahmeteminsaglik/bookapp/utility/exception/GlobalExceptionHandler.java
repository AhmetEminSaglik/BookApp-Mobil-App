package org.ahmeteminsaglik.bookapp.utility.exception;

import org.ahmeteminsaglik.bookapp.utility.exception.response.InvalidUsernameOrPasswordException;
import org.ahmeteminsaglik.bookapp.utility.result.ErrorDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {
        ApiException apiException = new ApiException(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public ResponseEntity<ErrorDataResult<String>> handleExceptionInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException ex) {
        log.error("Exception is handled: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult<>(ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDataResult<String>> handleExceptionUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("Exception is handled: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult<>(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDataResult<NullValue>> handleExceptionBadCredentialsException(BadCredentialsException ex) {
        log.error("Exception is handled: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult<>(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDataResult<NullValue>> handleException(Exception ex) {
        log.error("Exception is handled: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult<>(ex.getMessage()));
    }
}
