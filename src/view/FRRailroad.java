package view;

import javax.swing.JFrame;

public class FRRailroad extends JFrame {
	private static FRRailroad instance = null;
	
	public FRRailroad(String s){
		super(s);
		
		PNRailroad p = PNRailroad.getInstance(); 
		getContentPane().add(p);
	}
	
	public static FRRailroad getInstance(){
		if(instance == null){
			instance = new FRRailroad("ferrovia");
		}
		return instance;
	}
}
