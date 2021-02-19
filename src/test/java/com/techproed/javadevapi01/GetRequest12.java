
	package com.techproed.javadevapi01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Data;
import pojos.Employees;
import testbaseclasses.TestBaseDummyRestApi;


/*
 	POJO: Plain Old Java Objects
 	
 	POJO(Bean) should contain;
 	1)Private variables
 	2)Constructor without parameter, constructor with all parameters
 	3)All getters() and all setters()
 	4)toString()
*/

/*
 	    When 
	 		I send GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
	 	Then 
	 		Status code is 200
	 		And response body is like {
									    "status": "success",
									    "data": {
									        "id": 1,
									        "employee_name": "Tiger Nixon",
									        "employee_salary": 320800,
									        "employee_age": 61,
									        "profile_image": ""
									    },
									    "message": "Successfully! Record has been fetched."
									   }
 */
public class GetRequest12 extends TestBaseDummyRestApi {
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("api", "api",
				        "version", "v1",
				        "employee", "employee",
				        "id", 1);
		
		//Set the expected data
		Data data = new Data(1, "Tiger Nixon", 320800, 61, "");
		Employees expectedData = new Employees("success", data, "Successfully! Record has been fetched.");
		
//		System.out.println(expectedData);
		
		//Send the request
		Response response = given().spec(spec).contentType(ContentType.HTML).when().get("/{api}/{version}/{employee}/{id}");
		//response.prettyPrint();
		
		//Assert: POJO + Hard Assertion
		//De-Serialization Json Data to "Employees" Java Object 
		Employees actualData = response.as(Employees.class);//This code converts the Response Body to Java Object(GSON)
//		System.out.println(actualData);
		
		assertEquals(expectedData.getStatus(), actualData.getStatus());
		assertEquals(expectedData.getMessage(), actualData.getMessage());
		assertEquals(expectedData.getData().getId(), actualData.getData().getId());
//		System.out.println("expectedData.getData().getId()-> "+expectedData.getData().getId());
		
		assertEquals(expectedData.getData().getEmployeeName(), actualData.getData().getEmployeeName());
		assertEquals(expectedData.getData().getEmployeeSalary(), actualData.getData().getEmployeeSalary());
		assertEquals(expectedData.getData().getEmployeeAge(), actualData.getData().getEmployeeAge());
		assertEquals(expectedData.getData().getProfileImage(), actualData.getData().getProfileImage());

		//How to serialize: Convert "expectedData" Java Object to Json Data
		//1.step-> Create a Gson Object
		Gson gson = new Gson();
		
		//2.step-> Use toJson() method
		String jsonFromExpectedData = gson.toJson(expectedData);
		System.out.println("jsonFromExpectedData-> "+jsonFromExpectedData);

	}

}