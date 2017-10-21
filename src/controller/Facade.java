package controller;

import view.FRRailroad;
import view.FRTrainAdd;

public class Facade {
	private static Facade instance = null;
	private FRRailroad frrailroad;
	private TrainManager trainmanager;
	private TrafficManager trafficmanager;
	private FRTrainAdd frtrainadd;
	
	public static Facade getInstance(){
		if(instance == null){
			instance = new Facade();
		}
		
		return instance;
	}
	
	public void startFRRailroad(){
		frrailroad = FRRailroad.getInstance();
		frrailroad.setSize(1280, 670);
		frrailroad.setVisible(true);
	}
	
	public void startTrainManager(){
		trainmanager = TrainManager.getInstance();
	}
	
	public void startTrafficManager(){
		trafficmanager = TrafficManager.getInstance();
	}
	
	public void startFRTrainAdd(){
		frtrainadd = FRTrainAdd.getInstance();
		frtrainadd.setSize(500,220);
		frtrainadd.setVisible(true);
	}
	

}
