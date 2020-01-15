package com.epam.esm.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Certificates Not Found")
public class CertificateNotFoundException extends RuntimeException {

    public CertificateNotFoundException(String name) {
        super("Requested resource not found name =" + name);
    }

}
