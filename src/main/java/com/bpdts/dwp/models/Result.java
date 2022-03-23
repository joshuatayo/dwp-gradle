package com.bpdts.dwp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class Result {

	private final List<String> errorMessages = new ArrayList<>();
    private Set<User> users;
    
}
