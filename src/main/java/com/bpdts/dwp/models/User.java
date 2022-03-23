package com.bpdts.dwp.models;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {
	
	//Json properties
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    @JsonProperty("ip_address")
    private String ipAddress;
    private double latitude;
    private double longitude;
    
    // calculated results
    private String city;
    private double distanceFromCoords;
    
}
