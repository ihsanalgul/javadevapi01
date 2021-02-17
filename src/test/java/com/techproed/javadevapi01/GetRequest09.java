package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseDummyRestApi;

public class GetRequest09 extends TestBaseDummyRestApi{

	/*
	 * When
			 I send GET Request to URL http://dummy.restapiexample.com/api/v1/employees
		 Then
			 Status code is 200
			 1)Print all ids greater than 10 on the console
			   Assert that there are 14 ids greater than 10
			 2)Print all ages less than 30 on the console
			   Assert that maximum age less than 30 is 23
 	 		3)Print all salaries are greater than 100000 on the console
 	 			Assert that minimum salary greater than 100000 is 103600
			 4)Print all employee names whose salaries are greater than 350,000
			   Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
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
		
//		1)Print all ids greater than 10 on the console
//		List<String> idList = json.getList("data.findAll{it.id}.id"); //Groovy Language
//		List<String> idList = json.getList("data.id"); This also get all ids as a List
//		List<String> idList = json.getList("data.findAll{it.id}.employee_name"); //Groovy Language
//		List<String> idList = json.getList("data.findAll{Integer.parseInt(it.id)>10}.id"); //Groovy Language
		List<String> idList = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id"); //Groovy Language
		System.out.println(idList);
		
//		Assert that there are 14 ids greater than 10
		assertEquals(14, idList.size());
		
//		 2)Print all ages less than 30 on the console
		List<String> ageList = json.getList("data.findAll{Integer.parseInt(it.employee_age)<30}.employee_age");
		System.out.println(ageList);
		
//		Assert that maximum age less than 30 is 23
		List<Integer> ageListInt = new ArrayList<Integer>();
		for(String w: ageList) {
			ageListInt.add(Integer.parseInt(w));
		}
		Collections.sort(ageListInt);
		assertEquals(23, (int) ageListInt.get(ageListInt.size()-1));
				
//		 3)Print all salaries are greater than 100000 on the console
		List<String> salaryList = json.getList("data.findAll{Integer.parseInt(it.employee_salary)>100000}.employee_salary");
		System.out.println("salary list: "+salaryList);
		
//		Assert that minimum salary greater than 100000 is 103600
		Collections.sort(salaryList);
		assertEquals(103600, Integer.parseInt(salaryList.get(0)));
		
//		4)Print all employee names whose salaries are greater than 350,000
		List<String> nameList = json.getList("data.findAll{Integer.parseInt(it.employee_salary)>350000}.employee_name");
		System.out.println("nameList: "+nameList);
		
//		   Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
		assertTrue(nameList.contains("Charde Marshall"));
		
	}
}
