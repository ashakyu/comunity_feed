package org.fastcampus.common.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(400,"invalid_input_value"),
    NOT_FOUND(404, "404"),
    INTERNAL_ERROR(500, "unexpected_error");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
