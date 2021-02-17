package com.techproed.javadevapi01;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import testbaseclasses.TestBaseJsonPlaceHolder;
import testdata.TestDataJsonPlaceHolderApi;

/*
 	De-Serialization: Converting JSON Data to Java Object
 	Serialization: Converting Java Object to JSON Data
 	
 	For De-Serialization and Serialization, we have 2 main ways;
 	1) GSON Library ==> It converts "response body" to Java Objects
 	2) Object Mapper Library ==> It converts "response body" to Java Objects
*/

/*
  When 
  		I send GET Request to https://jsonplaceholder.typicode.com/todos/2
  Then 
  		Status code is 200
  		And "completed" is false
  		And "userId" is 1
  		And "title" is "quis ut nam facilis et officia qui"
  		And header "Via" is "1.1 vegur"
  		And header "Server" is "cloudflare"
 */

public class GetRequest10 extends TestBaseJsonPlaceHolder {
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("todos", "todos", 
				        "id", 2);
		
		//Set the expected data
		TestDataJsonPlaceHolderApi obj = new TestDataJsonPlaceHolderApi();
		HashMap<String, Object> expectedData = obj.setUpData();
		
		
		//Send Request
		Response response = given().spec(spec).when().get("/{todos}/{id}");
		
		response.prettyPrint();
		
		/////////////////////////////////////////////////Assert\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		//1.Way: body() + expected data Map
//		response.
//			then().
//			assertThat().
//			statusCode((int)expectedData.get("statusCode")).
//			body("completed", equalTo(expectedData.get("completed")),
//				 "userId", equalTo(expectedData.get("userId")),
//				 "title", equalTo(expectedData.get("title"))).
//			headers("Via", expectedData.get("Via"), //Notice that you don't have to use equalTo() for headers !
//					"Server", expectedData.get("Server"));
		
		//2.Way: assertEquals() + expected data Map
 		@SuppressWarnings("unchecked")
		HashMap<String, Object> actualData = response.as(HashMap.class); //Using as method() means that using GSON
		//System.out.println(actualData);
		/*
		 * In GSON you can just get body part, there is not a method headers
		 * Actually it can be accessible over response obj.
		 */
		assertEquals(expectedData.get("statusCode"), response.getStatusCode());
		
		assertEquals(expectedData.get("completed"), actualData.get("completed"));
		
		assertEquals(expectedData.get("userId"), actualData.get("userId"));
		
		assertEquals(expectedData.get("title"), actualData.get("title"));
		
		assertEquals(expectedData.get("Via"), response.getHeader("Via"));
		
		assertEquals(expectedData.get("Server"), response.getHeader("Server"));
		
	}

}
