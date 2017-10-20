package controller;

import java.util.Observable;
import java.util.Observer;

import model.Sensor;
import model.TrafficLight;
import view.PNRailroad;

public class TrafficManager {
	
	 private static TrafficManager instance = null;
	 
	 private int trainsInTunel = 0;
	   
	    public TrafficManager(){
	       
	       
	    }
	    
	   
	    public static TrafficManager getInstance(){
	        if(instance == null){
	            instance = new TrafficManager();
	        }
	       
	        return instance;
	    }
	    
//
//		@Override
//		public void update(Observable o, Object arg) {
//			
//		}
//
//		@Override
//		public void update() {
//			System.out.println("Observer ativado");
//			//System.out.println("quantidade de trens: " + TrainManager.getInstance().getqtdtrainsLeft() );
//			
//			if(trafficLeft.getIsGreen() == true && trafficRight.getIsGreen() == false){
//				trafficLeft.setIsGreen(false);
//				trafficRight.setIsGreen(true);
//			}
//			else if(trafficRight.getIsGreen() == true && trafficLeft.getIsGreen() == false){
//				trafficRight.setIsGreen(false);
//				trafficLeft.setIsGreen(true);
//			}
//			else if(trafficLeft.getIsGreen() == false && trafficRight.getIsGreen() == false){
//				trafficLeft.setIsGreen(true);
//			}
//			
//		}
}
