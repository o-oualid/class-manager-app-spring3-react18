package com.classmanager.classservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UncaughtException extends ApiError {
    private Exception exception;
    public UncaughtException(Exception exception) {
        super(new ArrayList<>(List.of("An internal server error occurred while processing your request.")));
    }
}
