package com.bpdts.dwp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpdts.dwp.models.Coordinates;
import com.bpdts.dwp.models.Locations;
import com.bpdts.dwp.models.Result;
import com.bpdts.dwp.services.DistanceService;

@RestController
public class ApiController {
	
	@Autowired
    private DistanceService distanceService;
	
	@GetMapping("/api/users-within-location")
    public Result getUsersWithinLocation(@RequestParam(name = "locationName") String locationName) {
		
		return distanceService.getUsersWithinLocation(locationName);
		
    }
	
	
	@GetMapping("/api/users-within-distance-of-location")
	public Result getUsersWithinDistanceOfLocation(@RequestParam(name = "locationName") String locationName) {

		//default distance 50.0 miles
		Double distance = 50.0;
		String location = Locations.checkIfLocationExistInEnum(locationName).getName();
		Coordinates coordinate = Locations.checkIfLocationExistInEnum(locationName).getCoordinates();
		Double latitude = coordinate.getLatitude();
		Double longitude = coordinate.getLongitude();
		
		
		return distanceService.getUsersWithinDistanceOfLocation(location, distance, latitude, longitude);
		
	}

}
