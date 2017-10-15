package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.TrafficManager;
import controller.TrainManager;
import model.Lista;
import model.Observable;
import model.Observer;
import model.Sensor;
import model.Train;

public class PNRailroad extends JPanel implements ActionListener, model.Observable {
	private static PNRailroad instance = null;
	private Image i, i2;
	private int speed = 33;
	TrainManager trainManager = TrainManager.getInstance();
	TrafficManager trafficManager = TrafficManager.getInstance();
	private int altura, largura;
	private JButton button1;
	private Sensor sensorin1, sensorout1, sensorin2,sensorout2;
	private List<Observer> observers = new ArrayList <Observer>();
	
	public PNRailroad(){
		super();
		
		try
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("Trem.jpg");
			i = ImageIO.read(input);
			
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		altura = (int) (i.getHeight(null)*0.8);
		largura = (int) (i.getWidth(null)*0.8);
		
	//	trainManager.addTrainLeft(largura, altura);
	//	trainManager.addTrainRight(largura, altura);
		
		//cria sensores
		sensorin1 = new Sensor(165,270);
		sensorin2 = new Sensor(1100,370);
		sensorout1 = new Sensor(1100,270);
		sensorout2 = new Sensor(165,370);
		
		button1 = new JButton("Adicionar Trens");
		button1.setLocation(270, largura/2);
		button1.addActionListener(this);
		this.add(button1);
		
		i2 = i.getScaledInstance(largura, altura, 100 );
		
		Timer timer = new Timer(speed, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				trainManager.moveTrains();
				
				if(trainManager.getcheckLeft()){
					notifyObserver(trafficManager);
					trainManager.setcheckLeft(false);
				}
				if(trainManager.getcheckRight()){
					notifyObserver(trafficManager);
					trainManager.setcheckRight(false);
				}
				
				repaint();
			}
		});
		timer.start();
	}
	
	public static PNRailroad getInstance(){
		if(instance == null){
			instance = new PNRailroad();
		}
		return instance;
	}
	
	public Sensor getSensorin1(){
		return sensorin1;
	}
	
	public Sensor getSensorout1(){
		return sensorout1;
	}
	
	public Sensor getSensorin2(){
		return sensorin2;
	}
	
	public Sensor getSensorout2(){
		return sensorout2;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(i2, 0, 0, null);
		Graphics2D g2d  = (Graphics2D) g;
		Rectangle2D s1,s2;
		s1 = new Rectangle2D.Double(300,200,30,50);
		s2 = new Rectangle2D.Double(980,400,30,50);
		
		g2d.setColor(Color.BLACK);
		Train train;
		Lista trains = trainManager.getTrainsLeft();
		trains.posIni();
		
		for (train = (Train) trains.prox(); train != null; train = (Train) trains.prox()){
			g2d.fillOval((int)(train.getPositionX()), (int)(train.getPositionY()), 15, 15);
		}
		
		g2d.setColor(Color.RED);
		trains = trainManager.getTrainsRight();
		trains.posIni();
		
		for (train = (Train) trains.prox(); train != null; train = (Train) trains.prox()){
			g2d.fillOval((int)(train.getPositionX()), (int)(train.getPositionY()), 15, 15);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.draw(s1);
		g2d.draw(s2);
		
		
		if(trafficManager.getTrafficLeft().getIsGreen()){
			g2d.setColor(Color.GREEN);
			g2d.fillOval(307, 210, 15, 15);
		}
		else{
			g2d.setColor(Color.RED);
			g2d.fillOval(307, 230, 15, 15); 
		} 
		
		if(trafficManager.getTrafficRight().getIsGreen()){
			g2d.setColor(Color.GREEN);
			g2d.fillOval(990, 410, 15, 15);
		}
		else{
			g2d.setColor(Color.RED);
			g2d.fillOval(990, 430, 15, 15);
		} 
		
		g2d.setColor(Color.blue);
		g2d.fillOval(sensorin1.get_x(),sensorin1.get_y(), 10, 10);
		g2d.fillOval(sensorin2.get_x(),sensorin2.get_y(), 10, 10);
		g2d.fillOval(sensorout1.get_x(),sensorout1.get_y(), 10, 10);
		g2d.fillOval(sensorout2.get_x(),sensorout2.get_y(), 10, 10);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		printPositions();
	}

	private void printPositions(){
		
		trainManager.addTrainLeft(largura, altura);
		trainManager.addTrainRight(largura, altura);
	}
	
	public void mouseClicked(MouseEvent e) {
	    int x=e.getX();
	    int y=e.getY();
//	    System.out.println(x+","+y);//these co-ords are relative to the component
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		
		if(i > -1){
			observers.remove(o);
		}
		
	}

	@Override
	public void notifyObserver(Observer o) {
		for(Observer p:observers){
			if(p == o){
				p.update();
			}
		}
	}
}
