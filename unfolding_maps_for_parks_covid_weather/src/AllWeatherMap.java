
public class AllWeatherMap extends AllMaps{
	
	public AllWeatherMap() {
		
		setupbehaviour = new SetWeatherMarker();
		
		drawbehaviour = new DrawWeatherMarkers();
	}

}
