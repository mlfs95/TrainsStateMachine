package controller;



public class TrafficManager {
	 private static TrafficManager instance = null;
	 public enum State{
		 LEFTOPENED, RIGHTOPENED, BOTHOPENED;
	 }
	 
	 private State state;
	   
	    public TrafficManager(){
	       
	    }
	    
	    public static TrafficManager getInstance(){
	        if(instance == null){
	            instance = new TrafficManager();
	        }
	       
	        return instance;
	    }
	    
	    public String status(){
	    	switch(state)
	    	{
	    		case LEFTOPENED: return "left opened";
	    		case RIGHTOPENED: return "right opened";
	    		case BOTHOPENED: return "both opened";
	    		default: return " both opened";
	    	}
	    }
	    
}
