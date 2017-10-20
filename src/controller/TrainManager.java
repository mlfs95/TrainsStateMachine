package controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.Timer;

import model.Lista;
import model.No;
import model.Observer;
import model.Sensor;
import model.TrafficLight;
import model.Train;
import model.Train.speed;
import view.PNRailroad;

public class TrainManager {

	private static TrainManager instance = null;
	private Lista trainsLeft;
	private Lista trainsRight;
	private Sensor sensorin1, sensorout1, sensorin2,sensorout2;
	private TrafficLight trafficLeft;
	private TrafficLight trafficRight;
	private int qtdtrainsRight = 0;
	private int qtdtrainsLeft = 0;
	private boolean checkLeft = false;
	private boolean checkRight = false;
	
	private int timerSpeed = 33;
	
	public TrainManager(){
		
		trainsRight = new Lista();
		trainsLeft = new Lista();
		
	    //cria sensores
		sensorin1 = new Sensor(165,270);
		sensorin2 = new Sensor(1100,370);
		sensorout1 = new Sensor(1100,270);
		sensorout2 = new Sensor(165,370);
		
		// Cria os sinais
        trafficLeft = new TrafficLight();
        trafficRight = new TrafficLight();
        trafficRight.setIsGreen(false);
        trafficLeft.setIsGreen(true);
        
		
		Timer timer = new Timer(timerSpeed, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("qtdTrainsLeft: " + qtdtrainsLeft);
				System.out.println("qtdTrainsRight: " + qtdtrainsRight);
				moveTrains();
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
	

	public void moveTrains() {
		
		trainsLeft.posIni();
		Train train;
		
		// Pra cada trem
		for (train = (Train) trainsLeft.prox(); train != null; train = (Train) trainsLeft.prox()){
			
			if((int)(train.getPositionX()) >= sensorin1.get_x()+5 && !train.getIsPassedTheSensorIn()){
				train.passedTheSensorIn();
				qtdtrainsLeft++;
			}
			
			if((int)(train.getPositionX()) >= sensorout1.get_x() && !train.getIsPassedTheSensorOut()){
				train.passedTheSensorOut();
				qtdtrainsLeft--;
			}
			
			// Caso o semaforo esteja vermelho ele para
			if ((int)(train.getPositionX()) >= sensorin1.get_x() && !trafficLeft.getIsGreen() && (int)(train.getPositionX()) <= sensorout1.get_x()){  }
			
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
			
			if((int)(train.getPositionX()) <= sensorin2.get_x()-5 && !train.getIsPassedTheSensorIn()){
				train.passedTheSensorIn();
				qtdtrainsRight++;
			}
			
			if((int)(train.getPositionX()) <= sensorout2.get_x() && !train.getIsPassedTheSensorOut()){
				train.passedTheSensorOut();
				qtdtrainsRight--;
			}
			
			if ((int)(train.getPositionX()) <= sensorin2.get_x() && !trafficRight.getIsGreen() && (int)(train.getPositionX()) >= sensorout2.get_x()){  }
			
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
		
		if(qtdtrainsLeft == 0) {
			trafficRight.setIsGreen(true);
		} else {
			trafficRight.setIsGreen(false);
		}
		
		if(qtdtrainsRight == 0) {
			trafficLeft.setIsGreen(true);
		} else {
			trafficLeft.setIsGreen(false);
		}
	}
	
	public void addTrainLeft(int largura, int altura) {
		Train train = new Train(true, largura, altura, speed.FAST);
		train.addObserver(PNRailroad.getInstance());
		trainsLeft.insFin(train);
	}
		
	public void addTrainRight(int largura, int altura) {
		Train train = new Train(false, largura, altura, speed.FAST);
		train.addObserver(PNRailroad.getInstance());
		trainsRight.insFin(train);
	}

	public Sensor getSensorin1(){
		return sensorin1;
	}
	
	public Sensor getSensorout1(){
		return sensorout1;
	}
	
	public Sensor getSensorin2(){
		return sensorin2;
	}
	
	public Sensor getSensorout2(){
		return sensorout2;
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

    public TrafficLight getTrafficLeft() {
		return trafficLeft;
	}
	
	public TrafficLight getTrafficRight() {
		return trafficRight;
	}
}










