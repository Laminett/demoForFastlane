package com.fastlane.user.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends InternalException {

    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    private final ErrorCode errorCode = ErrorCode.USER_ALREADY_EXIST;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public UserAlreadyExistsException(String id) {
        super("id: " + id + " is already exists.");
    }

}
