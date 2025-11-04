package com.vijay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseError {
    private String errorMessage;
    private ApplicationError applicationError;

    @Setter
    @Getter
    static class ApplicationError {
        private int errorCode;
        private String errorName;
    }
}