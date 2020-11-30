import java.util.HashMap;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class GetWeatherRequest {
	
	static double latitude;
	static double longitude; 

	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		return map;
	}
	
	GetWeatherRequest(double latitude, double longitude )
	{
		this.latitude = latitude;
		this.longitude = longitude;
		
	}

public Map getWeather() {
	
	String API_KEY = "e788cf4552a040ad38b0435f2827420a";
	String urlString = "http://api.openweathermap.org/data/2.5/weather?lat="+latitude+ "&lon="+longitude+ "&APPID=" + API_KEY + "&units=imperial";
	Map<String , String> returnMap = new HashMap<>();
	
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
	 //System.out.println(result);
	 returnMap = new HashMap<>();
	 Map<String, Object> respMap = jsonToMap(result.toString());
	 Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
	 Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
	 Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());
	 
	 returnMap.put("city",respMap.get("name").toString());
	 if (sysMap.get("country")!=null)
	 returnMap.put("country", sysMap.get("country").toString());
	 if (mainMap.get("temp")!=null)
	 returnMap.put("temp", mainMap.get("temp").toString());
	 if (mainMap.get("humidity")!=null)
	 returnMap.put("humidity",  mainMap.get("humidity").toString());
	 if (windMap.get("speed")!=null)
		 returnMap.put("speed",  windMap.get("speed").toString());
	 
	 
	 System.out.println("\nCurrent Temperature:" + mainMap.get("temp"));
	 System.out.println("feels_like:" + mainMap.get("feels_like"));
	 System.out.println("Current Humidity:" + mainMap.get("humidity"));
	 System.out.println("Wind Speeds:" + windMap.get("speed"));
	 System.out.println("Wind Angle:" + windMap.get("deg"));
	 System.out.println("Country:" + sysMap.get("country"));
	 System.out.println("City:" + respMap.get("name"));
	 	 
	}catch(IOException e)
	{
		System.out.println(e.getMessage());
	}
	return returnMap;
	
}
}
