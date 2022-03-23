package com.bpdts.dwp.services;

import com.bpdts.dwp.models.Result;

public interface DistanceService {
	
	Result getUsersWithinLocation(String locationName);

	Result getUsersWithinDistanceOfLocation(String city, 
			Double distance, 
			Double latitude, 
			Double longitude);

}
