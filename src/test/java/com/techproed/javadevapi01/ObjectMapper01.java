package com.techproed.javadevapi01;

import java.util.HashMap;

import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import testbaseclasses.TestBaseJsonPlaceHolder;
import utilities.JsonUtil;

/*
	When 
		I send GET Request to the URL https://jsonplaceholder.typicode.com/todos/198
		
	Then 
		Status code is 200
		And response body is like {
							    "userId": 10,
							    "id": 198,
							    "title": "quis eius est sint explicabo",
							    "completed": true
							  }
*/
public class ObjectMapper01 extends TestBaseJsonPlaceHolder {
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("todosName","todos",
							"id",198);
		
		//Set the expected data
		String expectedJson = "{\r\n"
				+ "	 \"userId\": 10,\r\n"
				+ "	 \"id\": 198,\r\n"
				+ "	 \"title\": \"quis eius est sint explicabo\",\r\n"
				+ "	 \"completed\": true\r\n"
				+ "	 }";
		
		@SuppressWarnings("unchecked")
		HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(expectedJson, HashMap.class);
		System.out.println(expectedData);

		//Send the request
		Response response = given().spec(spec).when().get("/{todosName}/{id}");
//		response.prettyPrint();
		HashMap<String, Object> actualData =JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
		System.out.println(actualData);
		
		//Assertion
		assertEquals(expectedData.get("completed"), actualData.get("completed"));
		assertEquals(expectedData.get("title"), actualData.get("title"));
		assertEquals(expectedData.get("userId"), actualData.get("userId"));
				
		//Convert actualData HashMap to Json data
		String jsonFromJavaObject = JsonUtil.convertJavaObjectToJson(actualData);
		System.out.println(jsonFromJavaObject);
	}

}
