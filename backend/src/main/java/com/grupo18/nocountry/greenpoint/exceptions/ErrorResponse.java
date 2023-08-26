package com.grupo18.nocountry.greenpoint.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String status;
    private String message;
    private LocalDateTime timestamp;
}
