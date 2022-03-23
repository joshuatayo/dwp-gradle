package com.bpdts.dwp.services;

import com.bpdts.dwp.models.Coordinates;

public interface CalculateDistanceService {
	
	double calculateDistanceBetween2Points(Coordinates far, Coordinates wide);
	
}
