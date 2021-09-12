package com.fastlane.user.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends InternalException {

    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    private final ErrorCode errorCode = ErrorCode.USER_NOT_FOUND;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public UserNotFoundException(String id) {
        super("id: " + id + " is not found.");
    }

}
