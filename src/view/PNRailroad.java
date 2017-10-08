package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Lista;
import model.TrafficLight;
import model.Train;

public class PNRailroad extends JPanel implements ActionListener {
	private static PNRailroad instance = null;
	private Image i, i2;
	private int speed = 500;
	private Train train11,train12,train13;
	private Train train21,train22,train23;
	private TrafficLight traffic1;
	private TrafficLight traffic2;
	private JButton button1;
	private Lista trainlist,trainlist2;
	private int dist = 20;
	
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
		
		int altura = (int) (i.getHeight(null)*0.8);
		int largura = (int) (i.getWidth(null)*0.8);
		
		//cria lista de trens
		trainlist = new Lista();
		train11 = new Train(true, largura, altura);
		trainlist.insFin(train11);
		train12 = new Train(true, largura, altura);
		trainlist.insFin(train12);
		train13 = new Train(true, largura, altura);
		trainlist.insFin(train13);
		
		trainlist2 = new Lista();
		train21 = new Train(false, largura, altura);
		trainlist2.insFin(train11);
		train22 = new Train(false, largura, altura);
		trainlist2.insFin(train12);
		train23 = new Train(false, largura, altura);
		trainlist2.insFin(train13);
		
		// Criando instâncias de trem
	/*	train1 = new Train(true, largura, altura);
		train2 = new Train(false, largura, altura); */
		
		// Criando instâncias de sinais
		traffic1 = new TrafficLight();
		traffic2 = new TrafficLight(); 
		
		button1 = new JButton("printar posicao");
		button1.setLocation(270, largura/2);
		button1.addActionListener(this);
		this.add(button1);
		
		i2 = i.getScaledInstance(largura, altura, 100 );
		
		Timer timer = new Timer(speed, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				repaint();
				train11.move();
				train12.move();
				train13.move();
				train21.move();
				train22.move();
				train23.move();
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
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(i2, 0, 0, null);
		Graphics2D g2d  = (Graphics2D) g;
		Rectangle2D s1,s2;
		s1 = new Rectangle2D.Double(300,200,30,50);
		s2 = new Rectangle2D.Double(980,400,30,50);
		
		g2d.setColor(Color.BLACK);
		g2d.fillOval((int)(train11.getPositionX()), (int)(train11.getPositionY()), 15, 15);
		g2d.fillOval((int)(train12.getPositionX()), (int)(train12.getPositionY()), 15, 15);
		g2d.fillOval((int)(train13.getPositionX()), (int)(train13.getPositionY()), 15, 15);
		g2d.setColor(Color.RED);
		g2d.fillOval((int)(train21.getPositionX()), (int)(train21.getPositionY()), 15, 15);
		g2d.fillOval((int)(train21.getPositionX()), (int)(train21.getPositionY()), 15, 15);
		g2d.fillOval((int)(train21.getPositionX()), (int)(train21.getPositionY()), 15, 15);
		
		g2d.setColor(Color.BLACK);
		g2d.draw(s1);
		g2d.draw(s2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		printPositions();
	}

	private void printPositions(){
//		System.out.printf("train1: (%d, %d)\n", train1.getPositionX(), train1.getPositionY());
//		System.out.printf("train2: (%d, %d)\n", train2.getPositionX(), train2.getPositionY());
	}
	
	public void mouseClicked(MouseEvent e) {
	    int x=e.getX();
	    int y=e.getY();
	    System.out.println(x+","+y);//these co-ords are relative to the component
	}
}
