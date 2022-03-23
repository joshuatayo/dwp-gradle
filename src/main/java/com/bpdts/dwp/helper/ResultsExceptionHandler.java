package com.bpdts.dwp.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bpdts.dwp.models.ApiResponse;


@RestControllerAdvice
public class ResultsExceptionHandler {

	@ExceptionHandler(ResultNotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundApiException(
            ResultNotFoundException ex) {
        ApiResponse response =
                ApiResponse.builder()
                        .error("404")
                        .message(ex.getMsg().toString())
                        .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
