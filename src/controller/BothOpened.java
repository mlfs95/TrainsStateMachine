package controller;

public class BothOpened extends TrafficState {
	
	public BothOpened(){
		
	}

	@Override
	public TrafficState trainsLeftIn() {
		
		return new LeftOpened();
	}

	@Override
	public TrafficState trainsLeftOut() {
		
		return this;
	}

	@Override
	public TrafficState trainsRightIn() {
		
		return new RightOpened();
	}

	@Override
	public TrafficState trainsRightOut() {
		
		return this;
	}

}
