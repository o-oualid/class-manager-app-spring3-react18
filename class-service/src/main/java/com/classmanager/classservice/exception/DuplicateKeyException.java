package com.classmanager.classservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DuplicateKeyException extends ApiError {
    private String field;
    private String message;

    public DuplicateKeyException(String field) {
        super();
        this.field = field;
        this.message = "Violated unique constraint on: " + this.field;
    }

    public DuplicateKeyException(List<String> errors) {
        super(errors);
    }

}
