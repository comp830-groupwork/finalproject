package demos;

public abstract class AllMaps {
	
	setUpBehaviour setupbehaviour;
		
	public AllMaps() {
		
	}
	
	public void performSetup() {
		
		setupbehaviour.setup();
	}
	
	
	
	public void setSetupBehaviour(setUpBehaviour sb) {
		
		setupbehaviour = sb;
	}
	

}
