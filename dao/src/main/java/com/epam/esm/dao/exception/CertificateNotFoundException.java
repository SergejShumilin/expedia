package com.epam.esm.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Certificates Not Found") //404
public class CertificateNotFoundException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public CertificateNotFoundException(String name){
        super("Requested resource not found " + name);
    }
}
