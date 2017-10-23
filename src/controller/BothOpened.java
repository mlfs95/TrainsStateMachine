package controller;

public class BothOpened extends TrafficState {
	
	public BothOpened(){ }

	public String status(){
		return "BothOpened";
	}
	
	@Override
	public TrafficState trainsLeftIn() {
		
		TrainManager.getInstance().setqtdtrainLeft(TrainManager.getInstance().getqtdtrainsLeft()+1);
		return new LeftOpened();
	}

	@Override
	public TrafficState trainsLeftOut() {
		
		return this;
	}

	@Override
	public TrafficState trainsRightIn() {
		
		TrainManager.getInstance().setqtdtrainsRight(TrainManager.getInstance().getqtdtrainsRight()+1);
		return new RightOpened();
	}

	@Override
	public TrafficState trainsRightOut() {
		
		return this;
	}

}
