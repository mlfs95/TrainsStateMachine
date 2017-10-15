package view;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import controller.Facade;
import controller.TrafficManager;

public class FRRailroad extends JFrame implements MouseListener {
	private static FRRailroad instance = null;
	
	public FRRailroad(String s){
		super(s);
		
		PNRailroad p = PNRailroad.getInstance(); 
		getContentPane().add(p);
		getContentPane().addMouseListener(this);
		p.addObserver(TrafficManager.getInstance());
	}
	
	public static FRRailroad getInstance(){
		if(instance == null){
			instance = new FRRailroad("ferrovia");
		}
		return instance;
	}
	
	public void mouseClicked(MouseEvent e) {
	  //  System.out.println(e.getX()+","+e.getY());
	} 
	
	public void mousePressed(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

}
