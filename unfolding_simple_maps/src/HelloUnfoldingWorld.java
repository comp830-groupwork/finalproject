import processing.core.PApplet;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.Google;
public class HelloUnfoldingWorld extends PApplet {

    UnfoldingMap map;

    public void setup() {
        size(800, 600, OPENGL);

        map = new UnfoldingMap(this, new Google.GoogleMapProvider());
        map.zoomAndPanTo(10, new Location(52.5f, 13.4f));

        MapUtils.createDefaultEventDispatcher(this, map);
    }
    public void draw() {
        background(0);
        map.draw();
    }

}