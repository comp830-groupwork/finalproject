import java.util.HashMap;

import de.fhpotsdam.unfolding.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class DictionaryGetRequest {

	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		return map;
	}
	} 

public static void main(String args[]) {
	String API_KEY = "e788cf4552a040ad38b0435f2827420a";
	String LOCATION = "Raleigh,NC";
	String urlString = "api.openweathermap.org/data/2.5/weather?q="+ LOCATION +"&appid=" + API_KEY + "&units-imperial";
	try 
	{
	 StringBuilder result = new StringBuilder();	
	 URL url = new URL(urlString);
	}
	
}
}
