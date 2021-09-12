package com.fastlane.user.exception;

import org.springframework.http.HttpStatus;

public abstract class InternalException extends RuntimeException {

    public abstract HttpStatus getHttpStatus();

    public abstract ErrorCode getErrorCode();

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }


}
