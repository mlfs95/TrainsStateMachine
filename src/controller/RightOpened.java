package controller;

public class RightOpened extends TrafficState {
	
	public RightOpened(){
		
	}

	@Override
	public TrafficState trainsLeftIn() {
	
		return null;
	}

	@Override
	public TrafficState trainsLeftOut() {
		
		return null;
	}

	@Override
	public TrafficState trainsRightIn() {
		
		return this;
	}

	@Override
	public TrafficState trainsRightOut() {
		
		return null;
	}

}
