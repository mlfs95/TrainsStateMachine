package model;

public class Train {

	private float positionX;
	private float positionY;
	private boolean isGoingRight;
	private boolean isMoving;
	private float velocity;
	public enum speed {
		FAST, MEDIUM, SLOW;
	}
	
	public Train (boolean isGoingRight, int largura, int altura, speed speed) {
		
		this.isGoingRight = isGoingRight;
		this.isMoving = true;
		
		System.out.println(speed);
		switch (speed) {
		
			case FAST:
				System.out.println("it is really fast");
				velocity = (float) 1.1; //1.1
				break;
			case MEDIUM:
				velocity = (float) 0.92;
				break;
			case SLOW:
				System.out.println("it is actually slow");
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
	
	public void move() {
		
		if (isGoingRight) {
			
			// Antes da curva pro tunel
			if (positionX <= 170) {
				
				positionX += velocity;
				System.out.println(positionX);
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
	}
}
