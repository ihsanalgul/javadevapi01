package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseDummyRestApi;

public class GetRequest08 extends TestBaseDummyRestApi{
	/*
	When 
            I send Get Request to http://dummy.restapiexample.com/api/v1/employees
        Then
            The Status code is 200
            And The name of 3rd employee is "Ashton Cox"
            And The Salary of 6th employee is 372000
            And The age of the last employee is 23
            And 21, 23, 61 are among the ages
	 */
	@Test
	public void get01() {
		//Set the URL
		spec.pathParams("apiName", "api",
						"version", "v1",
						"employees", "employees");
		
		//Set the expected data
		
		//Send the request
		Response response = given().spec(spec).when().get("/{apiName}/{version}/{employees}");
		
		response.prettyPrint();
				
		JsonPath json = response.jsonPath();
		
		assertEquals(200, response.getStatusCode());
		
		//The name of the 3rd employee is "Ashton Cox"
		assertEquals("Ashton Cox", json.getString("data[2].employee_name"));
		
		//The salary of 6th employee is 372000
		assertEquals("372000", json.getString("data[5].employee_salary"));
		
		//The age of last employee
		assertEquals("23", json.getString("data[-1].employee_age")); //last element
		
		//The age of second last employee
		assertEquals("21", json.getString("data[-2].employee_age")); //2nd last element
		
		//21, 23, 61 are among the ages
		//json.getList("data.employee_age") collects all values into a list
//		System.out.println(json.getList("data.employee_age"));
		
		List<String> ageList = new ArrayList<String>();
		ageList.add("21");
		ageList.add("23");
		ageList.add("61");
		
		assertTrue(json.getList("data.employee_age").containsAll(ageList));

	}

}
