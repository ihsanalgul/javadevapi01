package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Data;
import pojos.Employees;
import testbaseclasses.TestBaseDummyRestApi;

public class PostRequest01  extends TestBaseDummyRestApi{
	/*
	 * POST Request is used to create a new data in database
	 * In order to create POST Request:
	 * 1- POST Method 2- URL 3- Data
	 */

	/*
	 *  When
	 	  	I send a POST Request to the Url http://dummy.restapiexample.com/api/v1/create
	 	  	by using the following Request Body {
												    "name":"ERKAN DORMEN",
												    "salary":"1000",
												    "age":"18",
												    "profile_image": ""
												}
	 	 Then 
	 	  	Status code is 200
	 	  	And response body should be like {
											    "status": "success",
											    "data": {
											        "name": "ERKAN DORMEN",
											        "salary": "1000",
											        "age": "18",
											        "profile_image": null
											    },
											    "message": "Successfully! Record has been added."
											 }	*/
	 @Test
	 public void post01() {
		 //1.step-> Set the URL
		 spec.pathParams("api","api",
				 "version","v1","create","create");
		 
		 //2.step-> Set the posted data
		 //1st Way: Use constructor without parameter
//		 Data postedData = new Data();
//		 postedData.setEmployeeAge(22);
//		 postedData.setEmployeeName("Erkan Dormen");
//		 postedData.setEmployeeSalary(33333);
//		 postedData.setProfileImage("");
		 
		//2nd Way: Use constructor without parameter
		 Data postedData = new Data(0,"Erkan Dormen",3333,22,"");
		 if(postedData.getProfileImage().equals("")) {
			 postedData.setProfileImage(null);
		 }
		 
		 //3.rd Way: Use Map
//		 HashMap<String, Object> postedData = new HashMap<String, Object>();
//		 postedData.put("id", 0);
//		 postedData.put("employee_name", "Erkan Dormen");
//		 postedData.put("employee_salary", 3333);
//		 postedData.put("employee_age", 22);
//		 postedData.put("profile_image", "");
		 
		 
		 //Send POST Request
		 //
		 Response response = 
				given()
				 .contentType(ContentType.JSON)
				 .spec(spec).auth().basic("admin", "password123")
				 .body(postedData)
				.when()
				 .post("/{api}/{version}/{create}");
		 response.prettyPrint();
		 
		 //Assert
		 Employees actualData = response.as(Employees.class);
		 System.out.println(actualData);//by the help of toString()
		 
		 assertEquals(postedData.getEmployeeName(), actualData.getData().getEmployeeName());
		 assertEquals(postedData.getEmployeeSalary(), actualData.getData().getEmployeeSalary());
		 assertEquals(postedData.getEmployeeAge(), actualData.getData().getEmployeeAge());
		 assertEquals(postedData.getProfileImage(), actualData.getData().getProfileImage());
				 
	 }
}
