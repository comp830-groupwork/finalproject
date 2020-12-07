

public abstract class AllMaps {
	
	setUpBehaviour setupbehaviour;
	
	drawBehaviour drawbehaviour;
	
	public AllMaps() {
		
	}
	
	public void performSetup() {
		
		setupbehaviour.setup();
	}
	
	public void performDraw() {
		
		drawbehaviour.draw();
	}
	
	public void setSetupBehaviour(setUpBehaviour sb) {
		
		setupbehaviour = sb;
	}
	
	public void setDrawBehaviour(drawBehaviour db) {
		
		drawbehaviour = db;
	}

}
