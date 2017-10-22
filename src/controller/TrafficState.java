package controller;

public abstract class TrafficState {
	
	private static TrafficManager traffic;
	
	public static TrafficState getInitialState(TrafficManager t){
		traffic = t;
		return new BothOpened();
	}
	
	public abstract TrafficState trainsLeftIn();
	public abstract TrafficState trainsLeftOut();
	public abstract TrafficState trainsRightIn();
	public abstract TrafficState trainsRightOut();

}
