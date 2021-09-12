package com.fastlane.user.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedPasswordException extends InternalException {

    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    private final ErrorCode errorCode = ErrorCode.PASSWORD_DUPLICATED;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public DuplicatedPasswordException(String password, String newPassword) {
        super("old passowrd and new password are the same. old password: " + password + ", new password: " + newPassword);
    }

}
