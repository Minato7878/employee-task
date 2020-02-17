package com.company.interview.employeetask.controller;

import com.company.interview.employeetask.dto.ApiError;
import com.company.interview.employeetask.exception.DepartmentServiceException;
import com.company.interview.employeetask.exception.EmployeeServiceException;
import com.company.interview.employeetask.exception.EntityNotFoundException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
@CrossOrigin("http://localhost:4200")
public class ExceptionControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        ApiError apiError = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        log.error(apiError.toString(), e);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentServiceException.class)
    public ResponseEntity<ApiError> handleEmployeeServiceException(DepartmentServiceException e, HttpServletRequest request) {
        ApiError apiError = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        log.error(apiError.toString(), e);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeServiceException.class)
    public ResponseEntity<ApiError> handleEmployeeServiceException(EmployeeServiceException e, HttpServletRequest request) {
        ApiError apiError = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        log.error(apiError.toString(), e);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
        ApiError apiError = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        log.error(apiError.toString(), e);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException e, HttpServletRequest request) {
        ApiError apiError = ApiError.buildError(e, HttpStatus.UNAUTHORIZED, request);
        log.error(apiError.toString(), e);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleBadValidateException(ConstraintViolationException e, HttpServletRequest request) {
        ApiError apiError = ApiError.buildError(e, HttpStatus.BAD_REQUEST, request);
        StringJoiner joiner = new StringJoiner(";");
        ArrayList<ConstraintViolation<?>> constraintViolations = Lists.newArrayList(e.getConstraintViolations().iterator());
        constraintViolations.forEach(constraintViolation -> joiner.add(constraintViolation.getMessage()));
        constructMessage(e, apiError, joiner);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    private void constructMessage(Exception exception, ApiError apiError, StringJoiner joiner) {
        String errorMessage = joiner.toString();
        apiError.message = errorMessage;
        log.error(errorMessage, exception);
    }
}