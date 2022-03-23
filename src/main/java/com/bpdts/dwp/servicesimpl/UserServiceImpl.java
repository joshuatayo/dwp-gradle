package com.bpdts.dwp.servicesimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bpdts.dwp.models.User;
import com.bpdts.dwp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	//application.properties
    @Value("${api.apiurl}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;
    
    
    @Override
	public Collection<User> getAllUsers() {
		
		logger.debug("Getting Users");
        ResponseEntity<User[]> response = restTemplate.getForEntity(apiUrl + "/users", User[].class);

        Collection<User> users = parseResponse(response);

        logger.debug("[" + users.size() + "] Users returned");
        return users;
		
	}

	@Override
	public Collection<User> getAllUsersFromCity(String city) {
		
		logger.debug("Getting Users from City [" + city + "]");
	    ResponseEntity<User[]> response = restTemplate.getForEntity(apiUrl + "/city/" + city + "/users", User[].class);

	    Collection<User> users = parseResponse(response);

	    for (User user : users) {
	    	user.setCity(city);
	    }

	    logger.debug("[" + users.size() + "] Users returned from City [" + city + "]");
	    return users;
	    
	}
	
	private Collection<User> parseResponse(ResponseEntity<User[]> response) {
		
        Collection<User> users = new ArrayList<>();

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            users = Arrays.asList(response.getBody());
        }
        return users;
        
    }

}
