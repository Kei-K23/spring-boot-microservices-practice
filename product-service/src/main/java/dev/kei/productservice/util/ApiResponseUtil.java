package dev.kei.productservice.util;

import dev.kei.productservice.dto.ApiError;
import dev.kei.productservice.dto.ApiErrorResponse;

public class ApiResponseUtil {
    public static ApiErrorResponse error(int code, String message) {
        return new ApiErrorResponse(new ApiError(message, code));
    }
}
