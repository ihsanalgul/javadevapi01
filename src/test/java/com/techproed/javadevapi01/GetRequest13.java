package com.techproed.javadevapi01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.response.Response;
import pojos.Bookingdates;
import pojos.Bookings;
import testbaseclasses.TestBaseHerOkuApp;
import static io.restassured.RestAssured.*;



public class GetRequest13 extends TestBaseHerOkuApp {
	/*
	 * When I send GET Request to https://restful-booker.herokuapp.com/booking/1
	 * Then Response body should be like that; 
	 * 											{ "firstname": "Susan", 
	 * 												"lastname": "Brown", 
	 * 												"totalprice": 628, 
	 * 												"depositpaid": false, 
	 * 												"bookingdates": {
	 * 												"checkin": "2016-09-09", 
	 * 												"checkout": "2017-09-21" 
	 * 												} 
	 * 											}
	 */

	@Test
	public void get01() {

		// Set the URL
		spec.pathParams("booking", "booking", "id", 1);

		// Set the expected data
		Bookingdates bookingdates = new Bookingdates("2017-08-15", "2020-04-17");
		Bookings expectedData = new Bookings("Sally","Jones",628,true,bookingdates);
//		System.out.println(expectedData);
		
		//Send the request
		Response response = given().spec(spec).when().get("/{booking}/{id}");
		response.prettyPrint();
		
		//Assertion: GSON + POJO
		Bookings actualData = response.as(Bookings.class);
		System.out.println(actualData);
		
//		assertEquals(expectedData.getFirstname(), actualData.getFirstname());
//		assertEquals(expectedData.getLastname(), actualData.getLastname());
//		assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
//		assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
		assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
		assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
	}

}