package com.classmanager.classservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UncaughtException extends ApiError {
    private String message = "An internal server error occurred while processing your request.";
    public UncaughtException(String message) {
        this.message = message;
    }

    public UncaughtException(List<String> errors) {
        super(errors);
    }
}
