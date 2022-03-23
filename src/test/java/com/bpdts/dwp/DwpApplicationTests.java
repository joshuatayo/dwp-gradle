package com.bpdts.dwp;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.bpdts.dwp.controllers.ApiController;
import com.bpdts.dwp.models.Coordinates;
import com.bpdts.dwp.models.Locations;
import com.bpdts.dwp.models.Result;
import com.bpdts.dwp.models.User;
import com.bpdts.dwp.services.DistanceService;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
class DwpApplicationTests {

	@Autowired
    private MockMvc mvc;

    @MockBean
    DistanceService distanceService;

    @MockBean
    RestTemplate template;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    
    @Test
    void test_getUsersWithinLocation() throws Exception {

    	 Set<User> users = new HashSet<>();
         users.add(new User());

         Result result = new Result();
         result.setUsers(users);

         when(distanceService.getUsersWithinLocation("London"))
                 .thenReturn(result);

         @SuppressWarnings("unused")
         MvcResult mvcResult = mvc.perform(get("/api/users-within-location")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .param("locationName", "London"))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.users", hasSize(1)))
                 .andReturn();
    }
    
    @Test
    void test_checkIfLocationExistInEnum() {
        Locations result = Locations.checkIfLocationExistInEnum("London");
        assertEquals(result, Locations.LONDON);
    }
    
    @Test
    void test_getUsersWithinDistanceOfLocation() throws Exception {
        
    	String locationName = "London";
    	Double distance = 50.0;
		String location = Locations.checkIfLocationExistInEnum(locationName).getName();
		Coordinates coordinate = Locations.checkIfLocationExistInEnum(locationName).getCoordinates();
		Double latitude = coordinate.getLatitude();
		Double longitude = coordinate.getLongitude();
        Set<User> users = new HashSet<>();
        users.add(new User());

        Result result = new Result();
        result.setUsers(users);
        
        when(distanceService.getUsersWithinDistanceOfLocation(location, distance,
        		latitude, longitude))
                .thenReturn(result);

        @SuppressWarnings("unused")
		MvcResult mvcResult = mvc.perform(get("/api/users-within-distance-of-location")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        		.param("locationName", locationName))
                .andExpect(status().isOk()).andExpect(jsonPath("$.users", hasSize(1)))
                .andReturn();
        
    }
   
}