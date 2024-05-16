package br.com.stoom.store.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.web.client.HttpServerErrorException.*;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class APIAdivice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {

        StringBuilder errorMessage = new StringBuilder();
        ex.getSQLException().forEach(violation -> errorMessage.append(violation.getMessage()).append("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + errorMessage.toString());

    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError(InternalServerError ex) {

        Throwable rootCause = getRootCause(ex);
        String errorMessage = "Erro interno do servidor: " + rootCause.getMessage();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
    private Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        while (cause != null && cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause != null ? cause : throwable;
    }
}
