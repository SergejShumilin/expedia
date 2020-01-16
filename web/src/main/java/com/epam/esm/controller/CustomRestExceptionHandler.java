package com.epam.esm.controller;


import com.epam.esm.dao.exception.ApiError;
import com.epam.esm.dao.exception.CertificateNotFoundException;
import com.epam.esm.dao.exception.TagExistsException;
import com.epam.esm.dao.exception.TagNotFoundException;
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
        ApiError apiError = new ApiError("There is no such certificate with resource = " + exception.getMessage(),
                HttpStatus.NOT_FOUND.value() + exception.getLocalizedMessage());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagNotFoundException.class)
    protected ResponseEntity<ApiError> handleTagExistException(TagNotFoundException exception) {
        ApiError apiError = new ApiError("There is no such certificate with resource = " + exception.getMessage(),
                HttpStatus.NOT_FOUND.value() + exception.getLocalizedMessage());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagExistsException.class)
    protected ResponseEntity<ApiError> handleTagExistException(TagExistsException exception) {
        ApiError apiError = new ApiError("There is no such certificate with resource = " + exception.getMessage(),
                HttpStatus.NOT_FOUND.value() + exception.getLocalizedMessage());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }
}
