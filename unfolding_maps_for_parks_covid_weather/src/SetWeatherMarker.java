import processing.core.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.*;
import java.util.List;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import javax.json.JsonArray;

import de.fhpotsdam.unfolding.marker.Marker;

public class SetWeatherMarker extends PApplet implements setUpBehaviour {

	private static final long serialVersionUID = 1L;
	
	public UnfoldingMap map;
	List<Feature> countries;
	List<Feature> cities;
	List<Marker> countryMarkers;
	List<Marker> cityMarkers;
	String city;
	String country;
	JsonArray coordinates;

	public void setup() {
		System.out.println("Setting up");
		size(800, 600, OPENGL);
		System.out.println("Setting up");
		map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		cities = GeoJSONReader.loadData(this, "city-data.json");
		cityMarkers = MapUtils.createSimpleMarkers(cities);
		map.addMarkers(cityMarkers);
		System.out.println(map);
	}

}
