package controller;

public class LeftOpened extends TrafficState{
	
	public LeftOpened(){
		
	}

	@Override
	public TrafficState trainsLeftIn() {
		
		return this;
	}

	@Override
	public TrafficState trainsLeftOut() {
		
		return null;
	}

	@Override
	public TrafficState trainsRightIn() {
		
		return null;
	}

	@Override
	public TrafficState trainsRightOut() {
		
		return null;
	}

}
