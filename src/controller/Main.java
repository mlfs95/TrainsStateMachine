package controller;
import view.FRRailroad;

public class Main {

	public static void main(String[] args) {	
		Facade f = Facade.getInstance();
		f.startFRRailroad();
		f.startTrainManager();
		f.startTrafficManager();
		f.startFRTrainAdd();
		
	}

}
