# DWP Online Test

## Task

Using the language of your choice please build your own API which calls the API at https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.

## Solution

The solution I have developed for this online test a Java web app that runs on spring boot. It
offers 2 rest endpoints. 


The endpoints return a JSON list of the relevant users with or the form displays them 
on screen.

## Prerequisites

- Java 11

### To Build

To build the solution run the following commands from the root folder:

```
```

### To Run

To Run the solution run the following commands from the root folder:

```
```

### To Test

To Test the solution run the following commands from the root folder:

```
```

### How to use

The solution offers 2 methods of getting the specified users:

#### Api Call

The solution has 2 GET rest endpoints. One to return users within London or any other location 
and one return within 50 miles of London or any other location. Both endpoints uses parameters. 

To use with parameter, replace the values in the angle brackets: 

To return all users within London or any other location:

GET `http://localhost:8080/api/users-within-location?locationName=<cityname>`

To return all users within 50 miles of London or any other location:

GET `http://localhost:8080/api/users-within-distance-of-location?locationName=<cityname>`



