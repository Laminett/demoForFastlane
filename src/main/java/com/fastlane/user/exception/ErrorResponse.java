package com.fastlane.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorResponse {

    private ErrorCode code;

    private String message;

}
