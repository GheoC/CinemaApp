package com.daw.cinema.exception;

import com.daw.cinema.enums.ErrorCategory;
import com.daw.cinema.exception.exceptions.InconsistentDataException;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.exception.payload.ComplexExceptionPayload;
import com.daw.cinema.exception.payload.SimpleExceptionPayload;
import com.daw.cinema.exception.payload.model.Error;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    List<Error> errors = new ArrayList<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            fieldError ->
                errors.add(
                    new Error(
                        fieldError.getObjectName(),
                        fieldError.getField(),
                        fieldError.getDefaultMessage())));
    ex.getBindingResult()
        .getGlobalErrors()
        .forEach(
            objectError ->
                errors.add(
                    new Error(
                        objectError.getObjectName(),
                        "check object",
                        objectError.getDefaultMessage())));

    return new ResponseEntity<>(
        new ComplexExceptionPayload(
            ErrorCategory.VALIDATION_ERROR, "Inputs are not correct", errors),
        status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return new ResponseEntity<>(
        new SimpleExceptionPayload(ErrorCategory.CLIENT_ERROR, ex.getMessage()), status);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public SimpleExceptionPayload handleHibernateConstraintViolationException(
      DataIntegrityViolationException ex) {
    return new SimpleExceptionPayload(ErrorCategory.CLIENT_ERROR, ex.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public SimpleExceptionPayload handleResourceNotFoundExceptionException(
      ResourceNotFoundException ex) {
    return new SimpleExceptionPayload(ErrorCategory.ENTITY_NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(InconsistentDataException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public SimpleExceptionPayload handleInconsistentDataException(InconsistentDataException ex) {
    return new SimpleExceptionPayload(ErrorCategory.CLIENT_ERROR, ex.getMessage());
  }

  @ExceptionHandler(ExpiredJwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public SimpleExceptionPayload handleTokenExpiredException(ExpiredJwtException ex){
    return new SimpleExceptionPayload(ErrorCategory.CLIENT_ERROR, ex.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ComplexExceptionPayload handleValidationConstraintsException(
      ConstraintViolationException ex) {
    List<Error> errors = new ArrayList<>();
    ex.getConstraintViolations()
        .forEach(
            constraintViolation ->
                errors.add(
                    new Error(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getInvalidValue().toString(),
                        constraintViolation.getMessage())));

    return new ComplexExceptionPayload(
        ErrorCategory.VALIDATION_ERROR, "Inputs are not correct", errors);
  }
}
