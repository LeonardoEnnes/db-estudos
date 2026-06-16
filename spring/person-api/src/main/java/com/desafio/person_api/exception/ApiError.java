package com.desafio.person_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
    LocalDateTime timestamp,
    Integer status,
    String error,
    String message,
    String path,
    Map<String, String> fieldErrors
) {

}
