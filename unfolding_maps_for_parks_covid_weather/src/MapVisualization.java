
public class MapVisualization {

	public static void main(String[] args) {
		//AllMaps amaps = new USParksMap();
		AllMaps amaps = new AllWeatherMap();
		amaps.performSetup();
		amaps.performDraw();

	}

}
