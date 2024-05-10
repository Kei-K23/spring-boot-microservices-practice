package dev.kei.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {
    private ApiError error;

    public ApiErrorResponse(ApiError apiError) {
        this.error = apiError;
    }
}
