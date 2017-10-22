package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.TrainManager;
import model.Lista;
import model.Train;

public class PNRailroad extends JPanel implements model.Observer {
	private static PNRailroad instance = null;
	private Image i, i2;
	TrainManager trainManager = TrainManager.getInstance();
	private int altura, largura;
	
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
		
		i2 = i.getScaledInstance(largura, altura, 100 );
	}
	
	public static PNRailroad getInstance(){
		if(instance == null){
			instance = new PNRailroad();
		}
		return instance;
	}
	
	public int getLargura(){
		return largura;
	}
	
	public int getAltura(){
		return altura;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Prepara para desenhar
		g.drawImage(i2, 0, 0, null);
		Graphics2D g2d  = (Graphics2D) g;
		Rectangle2D s1,s2;
		s1 = new Rectangle2D.Double(300,200,30,50);
		s2 = new Rectangle2D.Double(980,400,30,50);
		
		g2d.setColor(Color.BLACK);
		Train train;
		Lista trains = trainManager.getTrainsLeft();
		trains.posIni();
		
		// desenha todos os trens da esquerda
		for (train = (Train) trains.prox(); train != null; train = (Train) trains.prox()){
			g2d.fillOval((int)(train.getPositionX()), (int)(train.getPositionY()), 15, 15);
		}
		
		g2d.setColor(Color.RED);
		trains = trainManager.getTrainsRight();
		trains.posIni();
		
		// desenha todos os trens da direita
		for (train = (Train) trains.prox(); train != null; train = (Train) trains.prox()){
			g2d.fillOval((int)(train.getPositionX()), (int)(train.getPositionY()), 15, 15);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.draw(s1);
		g2d.draw(s2);
		
		// Desenha as luzes dos sinais
		if(TrainManager.getInstance().getTraffic().status().equals("BothOpened")){
			g2d.setColor(Color.GREEN);
			g2d.fillOval(307, 210, 15, 15);
			g2d.fillOval(990, 430, 15, 15);
		}
		else if (TrainManager.getInstance().getTraffic().status().equals("LeftOpened")){
			g2d.setColor(Color.GREEN);
			g2d.fillOval(307, 210, 15, 15);
			g2d.setColor(Color.RED);
			g2d.fillOval(990, 430, 15, 15);
		}
		else {
			g2d.setColor(Color.RED);
			g2d.fillOval(307, 210, 15, 15);
			g2d.setColor(Color.GREEN);
			g2d.fillOval(990, 430, 15, 15);
		}
		
		// Desenha sensores
		g2d.setColor(Color.blue);
		g2d.fillOval(TrainManager.getInstance().getSensorin1().get_x(), TrainManager.getInstance().getSensorin1().get_y(), 10, 10);
		g2d.fillOval(TrainManager.getInstance().getSensorin2().get_x(), TrainManager.getInstance().getSensorin2().get_y(), 10, 10);
		g2d.fillOval(TrainManager.getInstance().getSensorout1().get_x(), TrainManager.getInstance().getSensorout1().get_y(), 10, 10);
		g2d.fillOval(TrainManager.getInstance().getSensorout2().get_x(), TrainManager.getInstance().getSensorout2().get_y(), 10, 10);
	}
	
	@Override
	public void update() {
		repaint();
	}

	@Override
	public void update(java.util.Observable o, Object arg) { }
}
