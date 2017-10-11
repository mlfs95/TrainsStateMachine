package controller;

import model.Lista;
import model.No;
import model.TrafficLight;
import model.Train;
import model.Train.speed;

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
			
			else { 
				
				No corr = trainsLeft.getCorr();
				trainsLeft.posIni();
				Train trainNext;
				boolean willHit = false;
				
				for (trainNext = (Train) trainsLeft.prox(); trainNext!=null; trainNext = (Train) trainsLeft.prox()){
					
					if (train.getPositionX() >= trainNext.getPositionX()-126 && train.getPositionX() < trainNext.getPositionX()){
						willHit = true;
					}
				}
				
				trainsLeft.setCorr(corr);
				if (!willHit){
					train.move();
				}
				
				
			}
			
		}
		
		trainsRight.posIni();
		
		for (train = (Train) trainsRight.prox(); train != null; train = (Train) trainsRight.prox()){
			
			if ((int)(train.getPositionX()) == 1105 && !trafficRight.getIsGreen()){  }
			
			else { 

				No corr = trainsRight.getCorr();
				trainsRight.posIni();
				Train trainNext;
				boolean willHit = false;
				
				for (trainNext = (Train) trainsRight.prox(); trainNext!=null; trainNext = (Train) trainsRight.prox()){
					
					if (train.getPositionX() <= trainNext.getPositionX()+126 && train.getPositionX() > trainNext.getPositionX()){
						willHit = true;
					}
				}
				
				trainsRight.setCorr(corr);
				if (!willHit){
					train.move();
				
				}
			}
		}
	}
	
	public void addTrainLeft(int largura, int altura) {
		trainsLeft.insFin(new Train(true, largura, altura, speed.FAST));
	}
	
	public void addTrainRight(int largura, int altura) {
		trainsRight.insFin(new Train(false, largura, altura, speed.FAST));
	}
	
	public TrafficLight getTrafficLeft() {
		return trafficLeft;
	}
	
	public TrafficLight getTrafficRight() {
		return trafficRight;
	}
}










