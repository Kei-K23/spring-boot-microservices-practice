package dev.kei.productservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class ApiError {
    private String message;
    private Integer code;

    public ApiError(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
