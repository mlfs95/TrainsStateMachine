package model;

import controller.TrafficState;

public class Traffic {

	private TrafficState state;
	
	public Traffic(){
		state = TrafficState.getInitialState(this);
	}
	
	public String status(){
		return state.status();
	}
	
	public void trainsLeftIn(){
		
		state = state.trainsLeftIn();
	}
	public void trainsLeftOut(){
		
		state = state.trainsLeftOut();
	}
	public void trainsRightIn(){
		
		state = state.trainsRightIn();
	}
	public void trainsRightOut(){
		state = state.trainsRightOut();
	}
}
