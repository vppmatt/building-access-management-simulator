package com.neueda.buildingaccessmanagementsimulator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateCannotBeInTheFutureException extends Exception{

    public DateCannotBeInTheFutureException(String message) {
        super (message);
    }

}
