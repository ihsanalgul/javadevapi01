package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Todos;
import testbaseclasses.TestBaseJsonPlaceHolder;
	/*
	 * When
	 		I send PUT Requst to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "userId": 21,
										    "title": "Wash the dishes",
										    "completed": false
										   }
	   Then 
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false,
									    "id": 198
									  }
	 */
public class PutRequest01 extends TestBaseJsonPlaceHolder{
	/*
	 * PUT Request is used to update data.
	 * PUT Request is used for fully update.
	 1* PUT Method
	 2* URL
	 3* Data
	 */
	
	 @Test
	 public void post01() {
		 //1.step-> Set the URL
		 spec.pathParams("todos","todos",
				 			"id",198);
		 
		 //2.step-> Set the put data
		 Todos dataToUpdate = new Todos(21,0,"Wash the dishes",false);
	
		 //3.step-> Send the request
		 Response response = given().contentType(ContentType.JSON)
				 				.spec(spec)
				 				.auth().basic("admin", "pasword123")
				 				.body(dataToUpdate)
				 				.when()
				 				.put("/{todos}/{id}");
		 response.prettyPrint();
		 
		 //Assert
		 Todos actualData = response.as(Todos.class);
		 System.out.println(actualData);
		 
		 assertEquals(dataToUpdate.getUserId(), actualData.getUserId());
		 assertEquals(dataToUpdate.getCompleted(), actualData.getCompleted());
		 assertEquals(dataToUpdate.getTitle(), actualData.getTitle());	 
		 
	 }
}
