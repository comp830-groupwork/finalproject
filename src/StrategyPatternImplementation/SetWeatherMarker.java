package demos;

import processing.core.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.*;
import java.util.List;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.json.JsonArray;

import org.json.JSONObject;

import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

public class SetWeatherMarker extends PApplet implements setUpBehaviour, setWeatherDetails {

	private static final long serialVersionUID = 1L;
	
	UnfoldingMap  map=null;
	List<Feature> countries;
	List<Feature> cities;
	List<Marker> countryMarkers;
	List<Marker> cityMarkers;
	String city;
	String country;
	JsonArray coordinates;

		public void setup() {
			getPreferredsize(12000, 10000, OPENGL);	
			map = new UnfoldingMap(this, new Microsoft.RoadProvider());
			map.zoomToLevel(2);
			MapUtils.createDefaultEventDispatcher(this, map);
			countries = GeoJSONReader.loadData(this, "countries.geo.json");
			countryMarkers = MapUtils.createSimpleMarkers(countries);
			map.addMarkers(countryMarkers);
			cities = GeoJSONReader.loadData(this, "city-data.json");
			cityMarkers = MapUtils.createSimpleMarkers(cities);
			map.addMarkers(cityMarkers);
			
			System.out.println(setWeather(mouseX, mouseY));
			
		}

		private void getPreferredsize(int i, int j, String OPENGL) {
			// TODO Auto-generated method stub
			
		}		
			
		
		public Map<String, String> setWeather(double latitude, double longitude) {
		
			String API_KEY = "e788cf4552a040ad38b0435f2827420a";
			String urlString = "http://api.openweathermap.org/data/2.5/weather?lat="+latitude+ "&lon="+longitude+ "&APPID=" + API_KEY + "&units=imperial";
			Map<String , String> returnMap = new HashMap<>();
			
				StringBuilder result = new StringBuilder();	
				URL url = null;
				try {
					url = new URL(urlString);
					URLConnection conn = null;
					conn = url.openConnection();
					BufferedReader rd = null;
				
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				    String line;
					while((line= rd.readLine())!=null)
						result.append(line);
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				JSONObject jo = new JSONObject (result.toString());		
				String city = (String) jo.get("name");
				JSONObject main = jo.getJSONObject("main");
				JSONObject wind = jo.getJSONObject("wind");
				JSONObject sys = jo.getJSONObject("sys");
				returnMap.put("city",city);
				
				if (sys.get("country")!=null)
					returnMap.put("country", sys.get("country").toString());
				if (main.get("temp")!=null)
					returnMap.put("temp", main.get("temp").toString());
				if (main.get("humidity")!=null)
					returnMap.put("humidity",  main.get("humidity").toString());
				if (wind.get("speed")!=null)
					returnMap.put("speed",  wind.get("speed").toString());

			System.out.print("printing returnMap" + returnMap);
		    return returnMap;
		}
}




