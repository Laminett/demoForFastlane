package com.fastlane.user.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends InternalException {

    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    private final ErrorCode errorCode = ErrorCode.PASSWORD_INCORRECT;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public IncorrectPasswordException(String password) {
        super("password is incorrect. password: " + password);
    }
}
