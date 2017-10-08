package model;

public class Train {

	private float positionX;
	private float positionY;
	private boolean isGoingRight;
	private boolean isMoving;
	
	public Train (boolean isGoingRight, int largura, int altura) {
		
		this.isGoingRight = isGoingRight;
		this.isMoving = true;
		
		if (isGoingRight) {
			
			positionX = 0+10;
			positionY = 270;
		} else {
			
			positionX = largura - 10;
			positionY = 370;
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
				
				positionX += 10;
			}
			
			// Na curva para entrar no tunel
			else if (positionX >= 170 && positionX <= 370) {
				
				positionX += 9;
				positionY = (float) (0.208*positionX+243.822);
			}
			
			// Dentro do tunel
			else if (positionX >= 370 && positionX <= 910) {
				
				positionX += 10;
			}
			
			// Curva na saida do tunel
			else if (positionX >= 910 && positionX <= 1100) {
				
				positionX += 9;
				positionY = (float) (0.283*positionX+428.415);
			}
			
			// Reta final
			else {
				
				positionX += 10;
			}
			
		} else {

			// Antes da curva pro tunel
			if (positionX >= 1100) {
				
				positionX -= 10;
			}
			
			// Na curva para entrar no tunel
			else if (positionX <= 1100 && positionX >= 920) {
				
				positionX -= 9;
				positionY = (float) (0.364*positionX-19.09);
			}
			
			// Dentro do tunel
			else if (positionX <= 920 && positionX >= 370) {
				
				positionX -= 10;
			}
			
			// Curva na saida do tunel
			else if (positionX <= 370 && positionX >= 160) {
				
				positionX -= 8;
				positionY = (float) (-0.204*positionX+507.614);
			}
			
			// Reta final
			else {
				
				positionX -= 10;
			}
		}
	}
}
