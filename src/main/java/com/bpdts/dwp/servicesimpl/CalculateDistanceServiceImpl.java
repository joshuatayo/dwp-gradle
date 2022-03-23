package com.bpdts.dwp.servicesimpl;

import org.springframework.stereotype.Service;

import com.bpdts.dwp.models.Coordinates;
import com.bpdts.dwp.services.CalculateDistanceService;

@Service
public class CalculateDistanceServiceImpl implements CalculateDistanceService {

	private static final double EARTH_RADIUS = 3958.8;
	
	public double calculateDistanceBetween2Points(Coordinates far, Coordinates wide) {
		
		if ((far.getLatitude() == wide.getLatitude()) && (far.getLongitude() == wide.getLongitude())) {
            return 0;
        } else {

            double farLon = Math.toRadians(far.getLongitude());
            double farLat = Math.toRadians(far.getLatitude());

            double wideLon = Math.toRadians(wide.getLongitude());
            double wideLat = Math.toRadians(wide.getLatitude());

			/*
			 * Using Haversine formula 
			 * Definition:- The haversine formula determines the great-circle distance between
			 * two points on a sphere given their longitudes and latitudes
			 */
            double lonDist = wideLon - farLon;
            double latDist = wideLat - farLat;
            double a = Math.pow(Math.sin(latDist / 2), 2)
                    + Math.cos(farLat) * Math.cos(wideLat)
                    * Math.pow(Math.sin(lonDist / 2), 2);

            double c = 2 * Math.asin(Math.sqrt(a));

            // calculate the result
            return (c * EARTH_RADIUS);
            
        }
	}
	
}
