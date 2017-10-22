package controller;

import model.Traffic;

public abstract class TrafficState {
	
	
	public static TrafficState getInitialState(Traffic t){
		return new BothOpened();
	}
	
	public abstract TrafficState trainsLeftIn();
	public abstract TrafficState trainsLeftOut();
	public abstract TrafficState trainsRightIn();
	public abstract TrafficState trainsRightOut();
	public abstract String status();

}
