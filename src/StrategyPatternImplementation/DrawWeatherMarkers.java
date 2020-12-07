package demos;
import javax.json.JsonArray;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.*;
import com.google.gson.reflect.*;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;

public class DrawWeatherMarkers extends SetWeatherMarker implements drawBehaviour{
	
	public void draw() {
		SetWeatherMarker sm = new SetWeatherMarker();
		//System.out.println(map);
		map.draw();
		int select =0;
		Map<String , String> respMap = new HashMap<>();
		System.out.println("Do you want to select a place to see weather");
		Scanner sc= new Scanner(System.in); 
		System.out.print("Enter 1 for YES and 0 for NO ");
		select = sc.nextInt();
		while (select ==1)
		{
			System.out.println("Please Select a place");
			Location location = map.getLocation(mouseX, mouseY);
			Marker marker = map.getFirstHitMarker(mouseX, mouseY);
			System.out.println("Default Location::");
			  if (marker != null) {
				  System.out.print("Selected Location::"+marker.getStringProperty("name")+"\n");
			  }
			//fill(0);
			
			
			respMap= sm.setWeather(location.getLat() , location.getLon());
			text("City= "+respMap.get("city")+",\n Country= "+respMap.get("country")+",\n Current Temp= "+respMap.get("temp")+",\n Humidity= "+respMap.get("humidity")+",\n Wind Speed= "+respMap.get("speed"), mouseX, mouseY);
			
			break;
		}
		
		

	}
	
	
}







