package com.neueda.buildingaccessmanagementsimulator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidDataException extends Exception {

    public InvalidDataException(String message) {
        super(message);
    }
}
