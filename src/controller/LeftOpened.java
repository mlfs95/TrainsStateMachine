package controller;

public class LeftOpened extends TrafficState{
	
	public LeftOpened(){
		
	}
	
	public String status(){
		return "LeftOpened";
	}
	
	@Override
	public TrafficState trainsLeftIn() {
		
		TrainManager.getInstance().setqtdtrainLeft(TrainManager.getInstance().getqtdtrainsLeft()+1);
		return this;
	}

	@Override
	public TrafficState trainsLeftOut() {
		
		TrainManager.getInstance().setqtdtrainLeft(TrainManager.getInstance().getqtdtrainsLeft()-1);
		
		if (TrainManager.getInstance().getqtdtrainsLeft() == 0){
			return new BothOpened();
		}
		return this;
	}

	@Override
	public TrafficState trainsRightIn() {
		return this;
	}

	@Override
	public TrafficState trainsRightOut() {
		
		return this;
	}

}
