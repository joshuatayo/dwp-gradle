package com.bpdts.dwp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpdts.dwp.helper.ResultNotFoundException;
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
		
		Result result = distanceService.getUsersWithinLocation(locationName);
		
		//check if result contain user within the city mention
		if(result.getUsers().isEmpty()) {
			throw new ResultNotFoundException("City Not Found");
		}
		
		return result;
		
    }
	
	
	@GetMapping("/api/users-within-distance-of-location")
	public Result getUsersWithinDistanceOfLocation(@RequestParam(name = "locationName") String locationName) {
		
		//check if city exist in location enum list
		if(Locations.checkIfLocationExistInEnum(locationName) == null) {
			throw new ResultNotFoundException(locationName + " not found in the Enum List in com.bpdts.dwp.models.locations");
		}
		
		//default distance 50.0 miles
		Double distance = 50.0;
		String location = Locations.checkIfLocationExistInEnum(locationName).getName();
		Coordinates coordinate = Locations.checkIfLocationExistInEnum(locationName).getCoordinates();
		Double latitude = coordinate.getLatitude();
		Double longitude = coordinate.getLongitude();
		
		
		return distanceService.getUsersWithinDistanceOfLocation(location, distance, latitude, longitude);
		
	}

}
