import processing.core.PApplet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import de.fhpotsdam.unfolding.providers.Microsoft;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class WeatherMap extends PApplet {

	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
    List<Feature> countries;
	List<Marker> countryMarkers;
	List<Marker> cityMarkers;
    
    public void setup() {
        size(800, 600, OPENGL);
        smooth();
        map = new UnfoldingMap(this, new Microsoft.RoadProvider());
        //map.zoomAndPanTo(10, new Location(52.5f, 13.4f));
        map.zoomToLevel(2);
        MapUtils.createDefaultEventDispatcher(this, map);
        countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);  
		List<Feature> cities = GeoJSONReader.loadData(this, "city-data.json");
		cityMarkers = MapUtils.createSimpleMarkers(cities);
		map.addMarkers(cityMarkers);  
		

    }
    public void draw() {
        background(0);
        map.draw();
        Location location = map.getLocation(mouseX, mouseY);
        fill(0);
        SimplePointMarker locationMarker = new SimplePointMarker(location);
        //map.addMarkers(locationMarker);
        ScreenPosition locationPos = locationMarker.getScreenPosition(map);
        strokeWeight(16);
        stroke(67, 211, 227, 100);
        noFill();
        ellipse(locationPos.x, locationPos.y, 36, 36);
        text(location.getLat() + ", " + location.getLon(), mouseX, mouseY);
      
    }
    
    /*public static Map<String, Object> jsonToMap(String str) {
		
		Gson gson = new GsonBuilder().setLenient().create();
		Map<String, Object> map = gson.fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());

		return map;
	}
    
    /*public String  getCity(Location location){
    	 String result = null;
    	 try
    	 	{
    		BufferedReader rd = new BufferedReader(new FileReader("C:\\Users\\alokm\\OneDrive\\purnya\\comp830\\unfolding_app_template_with_examples_0.9.6\\data\\data\\city-data.json"));
    		//rd.setLenient(true);
    		String line;
   	        while((line= rd.readLine())!=null)
   	         {
   		        result.append(line);
   	         }
   	       rd.close();
   	       System.out.println("Result String:" + result);
   	       
   	       Gson gson = new Gson();
           GeoLocation  geolocation = gson.fromJson(result, GeoLocation.class);
           System.out.println(geolocation);
   	       
   	       
   	       
   	       
   	       //JSONObject json = new JSONObject(rd.toString());
   	       //JSONArray jsonArray = new JSONArray(json.getString("object"));
   	      // System.out.println(json.get("type"));
   	      
   	     /*  Map<String, Object> respMap = jsonToMap(result.toString());
   	       Map<String, Object>  features = jsonToMap(respMap.get("features").toString());
   	       System.out.println("Result Map" + respMap);
	       System.out.println("Features Map 1" + features);
   	       Map<String, Object> featuresMap = jsonToMap(features.toString());
   	      
   	   
   	       System.out.println("Features Map 2" + featuresMap);
   	
   	       Map<String, Object> properties = jsonToMap(respMap.get("properties").toString());
   	       System.out.println("Properties" + properties);
  	       Map<String, Object> city = jsonToMap(properties.get("name").toString());
  	       Map<String, Object> country = jsonToMap(properties.get("country").toString());
  	       System.out.println("City is"+ city + "Country is"+ country);&
    	
  	       
  	       
		
    	}catch(IOException e)
    	{System.out.println(e);}
    	
    	return result;
    }
*/
 

}