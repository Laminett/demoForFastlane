package com.fastlane.user.exception.handler;

import com.fastlane.user.exception.ErrorCode;
import com.fastlane.user.exception.ErrorResponse;
import com.fastlane.user.exception.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //========================================================================================================
    //  Handle intentional exceptions
    //========================================================================================================

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<Object> handleInternalException(InternalException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getHttpStatus(), ex.getErrorCode(), ex.getMessage(), request);
    }

    /**
     * Validate request parameter by @Valid.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        String message = StringUtils.EMPTY;
        BindingResult result = ex.getBindingResult();

        for (ObjectError error : result.getAllErrors()) {
            if (StringUtils.isNotBlank(message)) {
                message += StringUtils.SPACE;
            }

            message += error.getDefaultMessage();
        }

        return handleExceptionInternal(ex, HttpStatus.UNPROCESSABLE_ENTITY, ErrorCode.INVALID_REQUEST, message, request);
    }

    //========================================================================================================
    //  Handle base exceptions
    //========================================================================================================

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleBaseException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR, "Server failed to complete the request due to unexpected error.", request);
    }

    //========================================================================================================
    //  Handle exceptions finally
    //========================================================================================================

    private ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpStatus status, ErrorCode code, String message, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(code, message);

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Exception occurred: {}\n" +
                        "{}\n" +
                        "HttpStatus: {} {}\n" +
                        "Response: {}\n" +
                        "StackTrace:",
                ex, request, status.value(), status.getReasonPhrase(), body, ex);

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
