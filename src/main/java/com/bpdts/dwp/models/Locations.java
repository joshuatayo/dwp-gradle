package com.bpdts.dwp.models;


public enum Locations {
	
	//https://www.latlong.net/
	LONDON("London", new Coordinates(51.507351, -0.127758));

    private final String name;
    private final Coordinates coordinates;
    
    Locations(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public static Locations checkIfLocationExistInEnum(String name) {
    	
		Locations result = null;
	    for (Locations locations : values()) {
	        if (locations.getName().equalsIgnoreCase(name)) {
	            result = locations;
	            break;
	        } 
	    }
	    return result;
	    
	}
    
}
