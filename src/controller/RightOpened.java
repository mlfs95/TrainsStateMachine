package controller;

public class RightOpened extends TrafficState {
	
	public RightOpened(){ }
	
	public String status(){
		return "RightOpened";
	}

	@Override
	public TrafficState trainsLeftIn() {
	
		return this;
	}

	@Override
	public TrafficState trainsLeftOut() {
		
		return this;
	}

	@Override
	public TrafficState trainsRightIn() {

		TrainManager.getInstance().setqtdtrainsRight(TrainManager.getInstance().getqtdtrainsRight()+1);
		return this;
	}

	@Override
	public TrafficState trainsRightOut() {

		TrainManager.getInstance().setqtdtrainsRight(TrainManager.getInstance().getqtdtrainsRight()-1);
		
		if (TrainManager.getInstance().getqtdtrainsRight() == 0){
			return new BothOpened();
		}
		return this;
	}

}
