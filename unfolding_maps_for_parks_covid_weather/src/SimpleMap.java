package CovidMap;

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

public class SimpleMap extends PApplet {

	UnfoldingMap map;

	HashMap<String, DataEntry> dataEntriesMap;
	List<Marker> countryMarkers;

	public static void main(String args[]) {
		PApplet.main(new String[] { SimpleMap.class.getName() });
	}

	public void setup() {
		getPreferredsize(12000, 10000, OPENGL);
	map = new UnfoldingMap(this, new Microsoft.RoadProvider());

//		map = new UnfoldingMap(this, new Google.GoogleMapProvider());
	//	map = new UnfoldingMap(this, 50, 50, 700, 500);
		map.zoomToLevel(2);
		map.setBackgroundColor(230);
		map.setZoomRange(1,7);
		MapUtils.createDefaultEventDispatcher(this, map);

		List<Feature> countries = GeoJSONReader.loadData(this, "data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);

		dataEntriesMap = loadcovidFromCSV("data/Covid19.csv");
		println("Loaded " + dataEntriesMap.size() + " data entries");

		shadeCountries();
	}

	private void getPreferredsize(int i, int j, String OPENGL) {
		// TODO Auto-generated method stub
		
	}

	public void draw() {
		background(2000);

		map.draw();
	}

	public void shadeCountries() {
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			DataEntry dataEntry = dataEntriesMap.get(countryId);

			if (dataEntry != null && dataEntry.Cases != 0) {
				float transparency =  map(dataEntry.Cases, 0, 13619437, 100, 300 );
				marker.setColor(color(255,0,0, transparency));
			}
//			 else {
//				marker.setColor(color(154));
//			}
		}
	}

	public HashMap<String, DataEntry> loadcovidFromCSV(String fileName) {
		HashMap<String, DataEntry> dataEntriesMap = new HashMap<String, DataEntry>();

		String[] rows = loadStrings(fileName);
		for (String row : rows) {
			String[] columns = row.split(",");
			if (columns.length >= 3) {
				DataEntry dataEntry = new DataEntry();
				dataEntry.countryName = columns[0];
				dataEntry.CountryCode = columns[1];
				dataEntry.Cases =Float.parseFloat(columns[2]);
				dataEntriesMap.put(dataEntry.CountryCode, dataEntry);
			}
		}

		return dataEntriesMap;
	}

	class DataEntry {
		String countryName;
		String CountryCode;
		float Cases;
	}

}
