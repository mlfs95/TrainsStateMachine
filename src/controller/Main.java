package controller;
import view.FRRailroad;

public class Main {

	public static void main(String[] args) {
		FRRailroad f = FRRailroad.getInstance();
		f.setSize(1280, 670);
		f.setVisible(true);
	}

}
