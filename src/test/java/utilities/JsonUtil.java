package utilities;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * Utilities Package is used to store useful methods
 * If in the project you need some methods repeatedly 
 * then create classes and put the methods
 * in that classes
 */

public class JsonUtil {
	
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
	}
	
	//This will get Json and convert it to Java object 
	//=> De-Serialization
	public static <T> T convertJsonToJava(String json, Class<T> cls) {
		
		T javaObject = null;
		
		try {
			//Converts json to cls type object than assigns it to javaObject variable
			javaObject = mapper.readValue(json, cls);
		} catch (JsonParseException e) {
			System.out.println("Could not convert Json to Java object "+e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Could not convert Json to Java object "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not convert Json to Java object "+e.getMessage());
		}
		return javaObject;
	}
	
	//=> Serialization
	public static String convertJavaObjectToJson (Object obj) {
		
		String jsonResult = null;
		
		try {
			jsonResult =mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			System.out.println("Could not convert Java object to Json data "+e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Could not convert Java object to Json data "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not convert Java object to Json data "+e.getMessage());
		}
		return jsonResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
