

import java.util.HashMap;
import java.util.List;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class CovidMap extends PApplet {

	UnfoldingMap map2;

	HashMap<String, DataEntry> dataEntriesMap;
	List<Marker> countryMarkers;

	public static void main(String args[]) {
		PApplet.main(new String[] { CovidMap.class.getName() });
	}

	public void setup() {
		getPreferedsize(1200, 1000, OPENGL);
	map2 = new UnfoldingMap(this, new Microsoft.RoadProvider());

		//map = new UnfoldingMap(this, new Google.GoogleMapProvider());
	//	map = new UnfoldingMap(this, 50, 50, 700, 500);
		map2.zoomToLevel(2);
		map2.setBackgroundColor(230);
		map2.setZoomRange(1,7);
		MapUtils.createDefaultEventDispatcher(this, map2);

		List<Feature> countries = GeoJSONReader.loadData(this, "data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map2.addMarkers(countryMarkers);

		dataEntriesMap = loadcovidFromCSV("data/Covid1.csv");
		println("Loaded " + dataEntriesMap.size() + " data entries");

		shadeCountries();
	}

	private void getPreferedsize(int i, int j, String opengl) {
		// TODO Auto-generated method stub
		
	}

	public void draw() {
		background(2000);

		map2.draw();
	}

	public void shadeCountries() {
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			DataEntry dataEntry = dataEntriesMap.get(countryId);

			if (dataEntry != null && dataEntry.Cases.toString() != null) {
				float transparency =  map(dataEntry.Cases);
				marker.setColor(Color(255,0,0, transparency));
			} else {
				marker.setColor(color(255));
			}
		}
	}


	private int Color(int i, int j, int k, float transparency) {
		// TODO Auto-generated method stub
		return 0;
	}

	private float map(String cases) {
		// TODO Auto-generated method stub
		return 0;
	}

	public HashMap<String, DataEntry> loadcovidFromCSV(String fileName) {
		HashMap<String, DataEntry> dataEntriesMap = new HashMap<String, DataEntry>();

		String[] rows = loadStrings(fileName);
		for (String row : rows) {
			String[] columns = row.split(";");
			if (columns.length >= 3) {
				DataEntry dataEntry = new DataEntry();
				dataEntry.countryName = columns[0];
				dataEntry.CountryCode = columns[1];
				dataEntry.Cases =columns[2];
				dataEntriesMap.put(dataEntry.CountryCode, dataEntry);
			}
		}

		return dataEntriesMap;
	}

	class DataEntry {
		String countryName;
		String CountryCode;
		String Cases;
	}

}
