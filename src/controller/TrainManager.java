package controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import model.Lista;
import model.No;
import model.Observer;
import model.Sensor;
import model.TrafficLight;
import model.Train;
import model.Train.speed;
import view.PNRailroad;

public class TrainManager implements model.Observable {

	private Observer observer;
	private ArrayList<Observer> observerList = new ArrayList<Observer>();
	private static TrainManager instance = null;
	private Lista trainsLeft;
	private Lista trainsRight;
	private TrafficLight trafficLeft = TrafficManager.getInstance().getTrafficLeft();
	private TrafficLight trafficRight = TrafficManager.getInstance().getTrafficRight();
	private int qtdtrainsRight = 0;
	private int qtdtrainsLeft = 0;
	private boolean checkLeft = false;
	private boolean checkRight = false;
	
	private int timerSpeed = 33;
	
	public TrainManager(){
		
		trainsRight = new Lista();
		trainsLeft = new Lista();
		
		Timer timer = new Timer(timerSpeed, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				moveTrains();
				
				// notificar observer
				observer.update();
				
				
			}
		});
		timer.start();
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
	
	public int getqtdtrainsLeft(){
		return qtdtrainsLeft;
	}
	
	public int getqtdtrainsRight(){
		return qtdtrainsRight;
	}
	
	public boolean getcheckLeft(){
		return checkLeft;
	}
	
	public void setcheckLeft(boolean check){
		 this.checkLeft = check;
	}
	
	public boolean getcheckRight(){
		return checkRight;
	}
	
	public void setcheckRight(boolean check){
		 this.checkRight = check;
	}
	
	public void moveTrains() {
		
		trainsLeft.posIni();
		Train train;
		
		for (train = (Train) trainsLeft.prox(); train != null; train = (Train) trainsLeft.prox()){
			
			if((int)(train.getPositionX()) >= PNRailroad.getInstance().getSensorout1().get_x()){
				qtdtrainsLeft++;
				if(qtdtrainsLeft == trainsLeft.getTam()){
					checkLeft = true;
				}
			}
			
			// Cuidado na hora dessa conversao pois podemos desligar o semaforo e ficar presos por estarmos convertendo float pra int
			
			// Caso o semaforo esteja vermelho ele para
			if ((int)(train.getPositionX()) >= PNRailroad.getInstance().getSensorin1().get_x() && !trafficLeft.getIsGreen() && qtdtrainsLeft == 0){  }
			
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
			
			if((int)(train.getPositionX()) <= PNRailroad.getInstance().getSensorout2().get_x()){
				qtdtrainsRight++;
				if(qtdtrainsRight == trainsRight.getTam()){
					checkRight = true;
				}
			}
			
			if ((int)(train.getPositionX()) <= PNRailroad.getInstance().getSensorin2().get_x() && !trafficRight.getIsGreen() && qtdtrainsRight == 0){  }
			
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

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		observer = o;
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}
	
}










