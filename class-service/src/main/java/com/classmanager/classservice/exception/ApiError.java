package com.classmanager.classservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ApiError extends RuntimeException {
    private boolean success = false;
    protected List<String> errors;
    private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(List<String> errors, int status) {
        super(null, null, false, false);
        this.errors = errors;
        this.status = status;
    }

    public ApiError(List<String> errors) {
        super(null, null, false, false);
        this.errors = errors;
    }

    public  ApiError() {
        super(null, null, false, false);
    }
}
