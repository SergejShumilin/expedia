package com.epam.esm.controller;


import com.epam.esm.dao.exception.ApiError;
import com.epam.esm.dao.exception.CertificateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
//            MethodArgumentTypeMismatchException ex, WebRequest request) {
//        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), error);
//        return new ResponseEntity<Object>(
//                apiError, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(CertificateNotFoundException.class)
    protected ResponseEntity<ApiError> handleThereIsNoSuchCertificatesException(CertificateNotFoundException exception) {
        String message = exception.getMessage();
        String localizedMessage = HttpStatus.NOT_FOUND.value() + exception.getLocalizedMessage();
        ApiError notFound = new ApiError("There is no such certificate with resource = " + message, localizedMessage);
        return new ResponseEntity<ApiError>(notFound, HttpStatus.NOT_FOUND);
    }
}
