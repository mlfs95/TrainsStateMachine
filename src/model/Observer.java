package model;

import java.util.Observable;

public interface Observer {
	
	public void update();

	void update(Observable o, Object arg);

}
