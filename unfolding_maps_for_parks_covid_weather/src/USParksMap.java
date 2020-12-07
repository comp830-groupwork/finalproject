
import processing.core.*;
import de.fhpotsdam.unfolding.*;

import java.util.List;
import de.fhpotsdam.unfolding.data.*;

import de.fhpotsdam.unfolding.geo.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.*;


public class USParksMap extends PApplet {
	
	private static final long serialVersionUID = 1L;
	SimplePointMarker parkMarker;
	ArrayList<String> parkName = new ArrayList<>();
	ArrayList<Marker> parksMarker = new ArrayList<>();
	UnfoldingMap map;
	HashMap<String, String> parksByState;
	List<Feature> countries;
	List<Marker> countryMarkers;	
	List<Feature> usStates;
	List<Marker> usStateMarkers;
	List<Marker> parksMarkers;

	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);

		// Load country polygons and adds them as markers
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		
		
		usStates = GeoJSONReader.loadData(this, "us-states.json");
		usStateMarkers = MapUtils.createSimpleMarkers(usStates);
		map.addMarkers(usStateMarkers);
		
		parksByState = loadparksByStateFromCSV("parks.csv");
		
		
		println("Loaded " + parksByState.size() + " data entries");
						
		Set <String>keyset = parksByState.keySet();
		
		ArrayList<Float> lat = new ArrayList<>();
		
		ArrayList<Float> lon = new ArrayList<>();
		
		for(String key: keyset)
			
			{			
						
			Float Lat = Float.parseFloat(key);	
			
			lat.add(Lat);
			
			}
			
			
			
		for(String parkLon: parksByState.values()) {
			
			Float Lon = Float.parseFloat(parkLon);
				
			lon.add(Lon);
			
		}
		
		
		for(int i=0; i < lat.size(); i++) {
			
			Location park = new Location(lat.get(i), lon.get(i));
			
			parkMarker = new SimplePointMarker(park);
			
			parksMarker.add(parkMarker);
			
			map.addMarkers(parkMarker);
			
			//parkMarker.setColor(color(255, 0, 0, 100));
				
			//parkMarker.setStrokeColor(color(255, 0, 0));
				
			//parkMarker.setStrokeWeight(4);
			
		}
		
		println("National parks in US");
		
		for(int i = 0; i < lat.size(); i++) {	
			
						
			println(parkName.get(i) + " " + "location on the map:" + " " + lat.get(i) +"," + lon.get(i));
		}
			
			}
	
	
				

	public void draw() {
		// Draw map tiles and country markers
		map.draw();
		bgColorButton();
		
		for(int i=0; i < parksMarker.size(); i++) {
			
			Marker parkMark;
			parkMark = parksMarker.get(i);
			
			ScreenPosition parkPos =  ((AbstractMarker) parkMark).getScreenPosition(map);
			
			strokeWeight(5);
			stroke(67, 211, 227, 100);
			noFill();
			
			ellipse(parkPos.x, parkPos.y, 10, 10);	
			
}
	}
	
	public void bgColorButton() {
		
		fill(255, 255, 255);
		rect(75, 75, 15, 15);
		
		fill(100, 100, 100);
		rect(75, 150, 15, 15);
	}
	
	
	public void mouseReleased() {
		if(mouseX > 75 && mouseX < 150
				&& mouseY > 75 && mouseY < 150) {
			
			background(255, 255, 255);
		
		} else if (mouseX > 75 && mouseX < 150
				&& mouseY > 150 && mouseY < 175) {
			
			background(0, 0, 0);
		}
	}
	

	private void arc(float x, float y, float s, float s2, double d, double e) {
		// TODO Auto-generated method stub
		
	}




	private HashMap<String, String> loadparksByStateFromCSV(String fileName) {
		
		HashMap<String, String> parklatlon= new HashMap<String, String>();		
		
		String[] rows = loadStrings(fileName);
		
		for(int i = 0; i< rows.length; i++) {
			
			String[] ecolumn = rows[i].split(",");
			
			parkName.add(ecolumn[0]);
			
			parklatlon.put(ecolumn[1], ecolumn[2]);
			
		
		}
		return parklatlon;
		
		
		}
	
		
	}


	
		
		
		