import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.json.JsonArray;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class WeatherMap extends PApplet {

	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	List<Feature> countries;
	List<Feature> cities;
	List<Marker> countryMarkers;
	List<Marker> cityMarkers;
	String city;
	String country;
	JsonArray coordinates;

	public void setup() {
		size(800, 600, OPENGL);
		smooth();
		map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		cities = GeoJSONReader.loadData(this, "city-data.json");
		cityMarkers = MapUtils.createSimpleMarkers(cities);
		map.addMarkers(cityMarkers);

	}

	public void draw() {
		background(0);
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
			fill(0);
			GetWeatherRequest gwb = new GetWeatherRequest(location.getLat() , location.getLon());
			respMap=gwb.getWeather();
			text("City= "+respMap.get("city")+",\n Country= "+respMap.get("country")+",\n Current Temp= "+respMap.get("temp")+",\n Humidity= "+respMap.get("humidity")+",\n Wind Speed= "+respMap.get("speed"), mouseX, mouseY);
			
			break;
		}
		
		

	}

	}
 	