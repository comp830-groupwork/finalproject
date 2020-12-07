package demos;

import java.util.Scanner;

public class MapVisualization {

	public static void main(String[] args) {
		
		int select =0;
		System.out.println("Please select what map details you want to see");
		Scanner sc= new Scanner(System.in); 
		System.out.print("Enter 1 for Weather or 2 for US National Parks or 3 for COVID ");
		select = sc.nextInt();
		
		if(select==1) {
			
			AllMaps amaps = new AllWeatherMap();			
			amaps.performSetup();
			
		}
		
		if(select==2) {
			
			AllMaps amaps = new USParksMap();			
			amaps.performSetup();
			
			
						
		}
		
		if(select==3) {
			
			AllMaps amaps = new CovidWorldMap();			
			amaps.performSetup();
			
			
		}
		
		sc.close();

	}

}
