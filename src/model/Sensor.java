package model;

public class Sensor {
	private int x;
	private int y;
	private int cont = 0;
	
	public Sensor(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int get_x(){
		return this.x;
	}
	
	public int get_y(){
		return this.y;
	}

}
