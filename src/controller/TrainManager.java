package controller;

import model.Lista;
import model.TrafficLight;
import model.Train;

public class TrainManager {

	private static TrainManager instance = null;
	private Lista trainsLeft;
	private Lista trainsRight;
	private TrafficLight trafficLeft;
	private TrafficLight trafficRight;
	
	public TrainManager(){
		
		trainsRight = new Lista();
		trainsLeft = new Lista();
		trafficLeft = new TrafficLight();
		trafficRight = new TrafficLight();
		trafficRight.setIsGreen(false);
	}
	
	public static TrainManager getInstance(){
		if(instance == null){
			instance = new TrainManager();
		}
		return instance;
	}
	
	public Lista getTrainsLeft() {
		return trainsLeft;
	}
	
	public Lista getTrainsRight(){
		return trainsRight;
	}
	
	public void moveTrains() {
		
		trainsLeft.posIni();
		Train train;
		
		for (train = (Train) trainsLeft.prox(); train != null; train = (Train) trainsLeft.prox()){
			
			// Cuidado na hora dessa conversao pois podemos desligar o semaforo e ficar presos por estarmos convertendo float pra int
			
			// Caso o semaforo esteja vermelho ele para
			if ((int)(train.getPositionX()) == 174 && !trafficLeft.getIsGreen()){  }
			
			else { train.move(); }
			
		}
		
		trainsRight.posIni();
		
		for (train = (Train) trainsRight.prox(); train != null; train = (Train) trainsRight.prox()){
			
			if ((int)(train.getPositionX()) == 1105 && !trafficRight.getIsGreen()){  }
			
			else { train.move(); }
		}
	}
	
	public void addTrainLeft(int largura, int altura) {
		trainsLeft.insFin(new Train(true, largura, altura));
	}
	
	public void addTrainRight(int largura, int altura) {
		trainsRight.insFin(new Train(false, largura, altura));
	}
	
	public TrafficLight getTrafficLeft() {
		return trafficLeft;
	}
	
	public TrafficLight getTrafficRight() {
		return trafficRight;
	}
}










