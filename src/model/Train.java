package model;

import java.util.ArrayList;
import java.util.ListIterator;

public class Train implements model.Observable {

	private ArrayList<Observer> lst = new ArrayList<Observer>();
	private float positionX;
	private float positionY;
	private boolean isPassedTheSensorIn = false;
	private boolean isPassedTheSensorOut = false;
	private boolean isGoingRight;
	private boolean isMoving;
	private float velocity;
	public enum speed {
		FAST, MEDIUM, SLOW;
	}
	
	public Train (boolean isGoingRight, int largura, int altura, speed speed) {
		
		this.isGoingRight = isGoingRight;
		this.isMoving = true;
		
		switch (speed) {
		
			case FAST:
				velocity = (float) 1.1;
				break;
			case MEDIUM:
				velocity = (float) 0.92;
				break;
			case SLOW:
				velocity = (float) 0.69;
				break;
		}
		
		if (isGoingRight) {
			
			positionX = 0+10;
			positionY = 270;
		} else {	
			positionX = largura - 10;
			positionY = 373;
		}
	}
	
	
	public float getPositionX() {
		return positionX;
	}
	
	public float getPositionY() {
		return positionY;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public boolean getIsGoingRight() {
		return isGoingRight;
	}
	
	public boolean getIsPassedTheSensorIn(){
		return isPassedTheSensorIn;
	}
	
	public boolean getIsPassedTheSensorOut(){
		return isPassedTheSensorOut;
	}
	
	public void setPositionX(int x) {
		positionX = x;
	}
	
	public void setPositionY(int y) {
		positionY = y;
	}
	
	public void setIsGoingRight(boolean isGoingRight) {
		this.isGoingRight = isGoingRight;
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void passedTheSensorIn(){
		isPassedTheSensorIn = true;
	}
	
	public void passedTheSensorOut(){
		isPassedTheSensorOut = true;
	}
	
	public void move() {
		
		if (isGoingRight) {
			
			// Antes da curva pro tunel
			if (positionX <= 170) {
				
				positionX += velocity;
			}
			
			// Na curva para entrar no tunel
			else if (positionX >= 170 && positionX <= 370) {
				
				positionX += velocity;
				positionY = (float) (0.208*positionX+235);
			}
			
			// Dentro do tunel
			else if (positionX >= 370 && positionX <= 910) {
				
				positionX += velocity;
			}
			
			// Curva na saida do tunel
			else if (positionX >= 910 && positionX <= 1100) {
				
				positionX += velocity;
				positionY = (float) (-0.208*positionX+502);
			}
			
			// Reta final
			else {
				
				positionX += velocity;
			}
			
		} else {

			// Antes da curva pro tunel
			if (positionX >= 1100) {
				
				positionX -= velocity;
			}
			
			// Na curva para entrar no tunel
			else if (positionX <= 1100 && positionX >= 920) {
				
				positionX -= velocity;
				positionY = (float) (0.364*positionX-27);
			}
			
			// Dentro do tunel
			else if (positionX <= 920 && positionX >= 370) {
				
				positionX -= velocity;
			}
			
			// Curva na saida do tunel
			else if (positionX <= 370 && positionX >= 160) {
				
				positionX -= velocity;
				positionY = (float) (-0.364*positionX+440);
			}
			
			// Reta final
			else {
				
				positionX -= velocity;
			}
		}
		
		ListIterator<Observer> li = lst.listIterator();
		
		while(li.hasNext()){
			li.next().update();
		}
	}

	@Override
	public void addObserver(Observer o) {
		lst.add(o);
	}

	@Override
	public void removeObserver(Observer o) { }

	@Override
	public void notifyObserver(Observer o) { }
	
}
