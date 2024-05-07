package dev.kei.productservice.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.kei.productservice.dto.ApiErrorResponse;
import dev.kei.productservice.util.ApiResponseUtil;
import dev.kei.productservice.util.ResourceNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestApiErrorHandler {
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse resourceNotFound(ResourceNotFoundException ex) {
        return ApiResponseUtil.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
