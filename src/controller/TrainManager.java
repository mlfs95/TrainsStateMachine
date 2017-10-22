package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Lista;
import model.No;
import model.Sensor;
import model.Traffic;
import model.Train;
import model.Train.speed;
import view.PNRailroad;

public class TrainManager {

	private static TrainManager instance = null;
	private Lista trainsLeft;
	private Lista trainsRight;
	private Sensor sensorin1, sensorout1, sensorin2,sensorout2;
	private Traffic traffic;
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
        
        traffic = new Traffic();
		
		Timer timer = new Timer(timerSpeed, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
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
				traffic.trainsLeftIn();
			}
			
			if((int)(train.getPositionX()) >= sensorout1.get_x() && !train.getIsPassedTheSensorOut()){
				train.passedTheSensorOut();
				traffic.trainsLeftOut();
			}
			
			// Caso o semaforo esteja vermelho ele não se move
			if ((int)(train.getPositionX()) >= sensorin1.get_x() && traffic.status().equals("RightOpened") && (int)(train.getPositionX()) <= sensorout1.get_x()){  }
			
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
				traffic.trainsRightIn();
			}
			
			if((int)(train.getPositionX()) <= sensorout2.get_x() && !train.getIsPassedTheSensorOut()){
				train.passedTheSensorOut();
				traffic.trainsRightOut();
			}
			
			if ((int)(train.getPositionX()) <= sensorin2.get_x() && traffic.status().equals("LeftOpened") && (int)(train.getPositionX()) >= sensorout2.get_x()){  }
			
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
	
	
	
	public void addTrainLeftFast(int largura, int altura) {
		Train train = new Train(true, largura, altura, speed.FAST);
		train.addObserver(PNRailroad.getInstance());
		trainsLeft.insFin(train);
	}
	
	public void addTrainLeftMedium(int largura, int altura) {
		Train train = new Train(true, largura, altura, speed.MEDIUM);
		train.addObserver(PNRailroad.getInstance());
		trainsLeft.insFin(train);
	}
	
	public void addTrainLeftSlow(int largura, int altura) {
		Train train = new Train(true, largura, altura, speed.SLOW);
		train.addObserver(PNRailroad.getInstance());
		trainsLeft.insFin(train);
	}
		
	public void addTrainRightFast(int largura, int altura) {
		Train train = new Train(false, largura, altura, speed.FAST);
		train.addObserver(PNRailroad.getInstance());
		trainsRight.insFin(train);
	}
	
	public void addTrainRightMedium(int largura, int altura) {
		Train train = new Train(false, largura, altura, speed.MEDIUM);
		train.addObserver(PNRailroad.getInstance());
		trainsRight.insFin(train);
	}
	
	public void addTrainRightSlow(int largura, int altura) {
		Train train = new Train(false, largura, altura, speed.SLOW);
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
	
	public void setqtdtrainLeft(int qtd){
		qtdtrainsLeft = qtd;
	}
	
	public void setqtdtrainsRight(int qtd){
		qtdtrainsRight = qtd;
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
	
	public Traffic getTraffic(){
		return traffic;
	}
}










