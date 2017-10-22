package controller;

public class Main {

	public static void main(String[] args) {	
		Facade f = Facade.getInstance();
		f.startFRRailroad();
		f.startTrainManager();
		f.startFRTrainAdd();
	}

}
