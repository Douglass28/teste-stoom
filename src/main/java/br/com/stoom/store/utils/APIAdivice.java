package br.com.stoom.store.utils;

import br.com.stoom.store.exception.CategoryException;
import br.com.stoom.store.exception.ProductException;
import br.com.stoom.store.exception.SuplierException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<Exception> notFound(CategoryException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msg = "Erro de validação para categoria com esse ID ";
        String path = request.getRequestURI();
        Exception exception = new Exception(Instant.now(), status.value(), e.getMessage(), msg, path);
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Exception> notFound(ProductException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msg = "Erro de validação para produto com esse ID";
        String path = request.getRequestURI();
        Exception exception = new Exception(Instant.now(), status.value(), e.getMessage(), msg, path);
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(SuplierException.class)
    public ResponseEntity<Exception> notFound(SuplierException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msg = "Erro de validação para fornecedor com esse ID";
        String path = request.getRequestURI();
        Exception exception = new Exception(Instant.now(), status.value(), e.getMessage(), msg, path);
        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Exception> notFound(EmptyResultDataAccessException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msg = "Erro de validação para esse ID";
        String path = request.getRequestURI();
        Exception exception = new Exception(Instant.now(), status.value(), e.getMessage(), msg, path);
        return ResponseEntity.status(status).body(exception);
    }





}
