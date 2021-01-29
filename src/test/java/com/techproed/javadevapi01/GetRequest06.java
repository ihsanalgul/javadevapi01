package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;
import testbaseclasses.TestBaseHerOkuApp;

public class GetRequest06 extends TestBaseHerOkuApp{
	
	/*
	 	When
	  		I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/firstname=Susan&&lastname=Brown
	  	 Then
	  		Among the data there should be someone whose first name is "Susan" and last name is "Brown"
     */
	
	@Test
	public void get01() {
		
		//Set the url
		spec.pathParam("bookingName", "booking")
			.queryParams("firstname","Susan",
						"lastname","Brown");
		
		//Set the expected data
		
		//Send the request
		Response response = given().spec(spec).when().get("/{bookingName}");
		
		response.prettyPrint();
		
		//Assertions
		response
		.then()
		.assertThat()
		.statusCode(200);
		
		assertTrue(response.asString().contains("bookingid"));
		
	}
	
}