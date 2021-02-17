package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseDummyRestApi;
import testdata.TestDataDummyRestApi;

public class GetRequest11review extends TestBaseDummyRestApi {
	/*
	 * When I send GET Request to the Url
	 * http://dummy.restapiexample.com/api/v1/employees 
	 * Then Status code is 200 
	 * And 5th employee name is Airi Satou 
	 * And the number of employees is 24 
	 * And the salary of 2nd last employee is 106450
	 * And 40, 21, and 19 are among the ages
	 * And 11th employee is like { "id": "11", 
	 * 								"employee_name": "Jena Gaines",
	 * 								"employee_salary": "90560",
	 * 								"employee_age": "30", 
	 * 								"profile_image": "" }
	 */

	@Test
	public void get01() {
		// Set the URL
		spec.pathParams("api", "api", "version", "v1", "employees", "employees");

		// Set the expected data
		TestDataDummyRestApi obj = new TestDataDummyRestApi();
		HashMap<String, Object> expectedData = obj.setUpData();

		// Send the request
		Response response = given().spec(spec).when().get("/{api}/{version}/{employees}");
		response.prettyPrint();
		
		//Assert
		//1.Way: JsonPath Class + expected data Map
		JsonPath json = response.jsonPath(); 
		/*
		 * json object will be used just to get body 
		 * there is no need to use json object for headers and status code
		 * response.getStatusCode() is enough to get status code
		 */
		
		assertEquals(expectedData.get("statusCode"), response.getStatusCode());
		assertEquals(expectedData.get("fifthEmployee"), json.getString("data[4].employee_name"));
		assertEquals(expectedData.get("numOfEmployee"), json.getList("data").size());
		assertEquals(expectedData.get("secondSalary"), json.getString("data[-2].employee_salary"));
		assertTrue(json.getList("data.employee_age").containsAll((List)expectedData.get("ages")));
		assertTrue(json.getString("data[10].id").equals(((HashMap)expectedData.get("eleventhEmployee")).get("id")));
		assertTrue(json.getString("data[10].employee_name").equals(((HashMap)expectedData.get("eleventhEmployee")).get("employee_name")));
		assertTrue(json.getString("data[10].employee_salary").equals(((HashMap)expectedData.get("eleventhEmployee")).get("employee_salary")));
		assertTrue(json.getString("data[10].employee_age").equals(((HashMap)expectedData.get("eleventhEmployee")).get("employee_age")));

	}
}
