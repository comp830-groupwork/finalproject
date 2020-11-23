import java.util.HashMap;

//import de.fhpotsdam.unfolding.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class GetWeatherRequest {

	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		return map;
	}
	

public static void main(String args[]) {
	String API_KEY = "e788cf4552a040ad38b0435f2827420a";
	String LOCATION = "Manchester,us";
	String urlString = "http://api.openweathermap.org/data/2.5/weather?q="+ LOCATION +"&APPID=" + API_KEY + "&units=imperial";
	try 
	{
	 StringBuilder result = new StringBuilder();	
	 URL url = new URL(urlString);
	 URLConnection conn = url.openConnection();
	 BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 String line;
	 while((line= rd.readLine())!=null)
	 {
		 result.append(line);
	 }
	 rd.close();
	 System.out.println(result);
	 Map<String, Object> respMap = jsonToMap(result.toString());
	 Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
	 Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
	 
	 System.out.println("Current Temperature:" + mainMap.get("temp"));
	 System.out.println("feels_like:" + mainMap.get("feels_like"));
	 
	 System.out.println("Current Humidity:" + mainMap.get("humidity"));
	 System.out.println("Wind Speeds:" + windMap.get("speed"));
	 System.out.println("Wind Angle:" + windMap.get("deg"));
	 
	 
	}catch(IOException e)
	{
		System.out.println(e.getMessage());
	}
	
}
}
