package com.bpdts.dwp.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse {
	String error;
    String message;
}
