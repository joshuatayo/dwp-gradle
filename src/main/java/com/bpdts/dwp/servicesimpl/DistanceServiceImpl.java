package com.bpdts.dwp.servicesimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpdts.dwp.models.Coordinates;
import com.bpdts.dwp.models.Result;
import com.bpdts.dwp.models.User;
import com.bpdts.dwp.services.CalculateDistanceService;
import com.bpdts.dwp.services.DistanceService;
import com.bpdts.dwp.services.UserService;


@Service
public class DistanceServiceImpl implements DistanceService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private CalculateDistanceService calculateDistanceService;

	@Override
	public Result getUsersWithinLocation(String locationName) {
		
		Result results = new Result();
        Set<User> users = new HashSet<>();

        if (!(locationName == null) && !locationName.isEmpty()) {
        	logger.debug("Getting users from {}", locationName);
            users.addAll(userService.getAllUsersFromCity(locationName));
        } else {
        	logger.debug("No location name provided");
        }
        
        logger.debug("[{}] users found", users.size());
        results.setUsers(users);

        return results;
		
	}


	@Override
	public Result getUsersWithinDistanceOfLocation(String city, Double distance, Double latitude, Double longitude) {
		
		Result results = new Result();
        Set<User> users = new HashSet<>();

        // If a location name has been provided look up users from that city
        if (!(city == null) && !city.isEmpty()) {
        	logger.debug("Getting users from {}", city);
            users.addAll(userService.getAllUsersFromCity(city));
        } else {
        	logger.debug("No location name provided");
        }

        // If coordinates and distance are provided find all users with in range
        if (distance != null && latitude != null && longitude != null) {
        	logger.debug("Getting users within [{}] miles of [{} , {}]", distance, latitude, longitude);

            boolean validParams = true;

            if (distance < 0) {
                //invalid distance
                results.getErrorMessages().add("Distance must be greater than 0");
                validParams = false;
            }


            if (latitude > 90 || longitude < -90) {
                //invalid latitude
                results.getErrorMessages().add("Latitude must be must be between 90 and -90");
                validParams = false;
            }

            if (latitude > 180 || longitude < -180) {
                //invalid longitude
                results.getErrorMessages().add("Longitude must be between 180 and -180");
                validParams = false;
            }

            if (validParams) {
                Coordinates coords = new Coordinates(latitude, longitude);
                users.addAll(getUsersWithinRangeOfCoords(distance, coords));
            }

        } else {
        	logger.debug("No coordinates provided");
        }

        logger.debug("[{}] users found", users.size());
        results.setUsers(users);

        return results;
        
	}
	
	private List<User> getUsersWithinRangeOfCoords(double distance, Coordinates location) {

        List<User> usersWithinRange = new ArrayList<>();

        // Get all users
        Collection<User> allUsers = userService.getAllUsers();

        for (User user : allUsers) {

            // Calculate the distance between the user and the coordinates provided
            Coordinates userCoords = new Coordinates(user.getLatitude(), user.getLongitude());
            double distanceFromCoords = calculateDistanceService.calculateDistanceBetween2Points(userCoords, location);

            // If the user is within range add it to the list
            if (distanceFromCoords <= distance) {
                user.setDistanceFromCoords(distanceFromCoords);
                usersWithinRange.add(user);
            }
        }

        logger.debug("[{}] users found within range", usersWithinRange.size());
        return usersWithinRange;
    }

}
