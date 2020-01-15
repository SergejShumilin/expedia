package com.epam.esm.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Tag Not Found") //404
public class TagNotFoundException extends Exception {
    public TagNotFoundException(int id){
        super("The certificate doesn't exist with the id = " + id);
    }
}
