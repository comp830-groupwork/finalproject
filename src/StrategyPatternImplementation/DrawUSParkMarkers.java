package demos;

import de.fhpotsdam.unfolding.marker.AbstractMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.ScreenPosition;

public class DrawUSParkMarkers extends SetMarker implements drawBehaviour{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void draw() {
		
	//System.out.println("displaying national parks in US");
	map.draw();
	
		
	for(int i=0; i < parksMarker.size(); i++) {
		
		
		Marker parkMark;
		
		parkMark = parksMarker.get(i);
		
		ScreenPosition parkPos =  ((AbstractMarker) parkMark).getScreenPosition(map);
		
		strokeWeight(5);
		
		stroke(67, 211, 227, 100);
		
		noFill();
		
		ellipse(parkPos.x, parkPos.y, 10, 10);	
				
	}
	
	mouseMoved();
	
		}
	
	
	public void mouseMoved() {
		
		for(int i=0; i < parksMarker.size(); i++) {
			
			
			Marker parkMark;
			
			parkMark = parksMarker.get(i);
			
			ScreenPosition parkPos =  ((AbstractMarker) parkMark).getScreenPosition(map);
			
			if(mouseX == parkPos.x && mouseY == parkPos.y) {
				
				text(parkName.get(i), parkPos.x, parkPos.y);
				
				fill(255);
			}
	}
	}
}


