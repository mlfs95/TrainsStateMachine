package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.TrainManager;

public class FRTrainAdd extends JFrame implements ActionListener {
	private JButton be1,be2,be3,bd1,bd2,bd3;
	private JLabel l1,l2;
	private Container screen;
	private static FRTrainAdd instance = null;
	private int largura = PNRailroad.getInstance().getLargura();
	private int altura = PNRailroad.getInstance().getAltura();
	private TrainManager trainManager = TrainManager.getInstance();
	
	FRTrainAdd(String s){
		super(s);
		
		screen = getContentPane();
		screen.setLayout(null);
		
		be1 = new JButton("60 Km/h");
		be2 = new JButton("80 Km/h");
		be3 = new JButton("100 Km/h");
		bd1 = new JButton("60 Km/h");
		bd2 = new JButton("80 Km/h");
		bd3 = new JButton("100 Km/h");
		
		l1 = new JLabel("Adicionar trens a esquerda:");
		l2 = new JLabel("Adicionar trens a direita:");
		l1.setBounds(20,20,400,30);
		l2.setBounds(270, 20, 400, 30);
		
		be1.setBounds(20, 60, 90, 30);
		be2.setBounds(20, 100, 90, 30);
		be3.setBounds(20, 140, 90, 30);
		
		bd1.setBounds(270, 60, 90, 30);
		bd2.setBounds(270, 100, 90, 30);
		bd3.setBounds(270, 140, 90, 30);
		
		screen.add(l1);
		screen.add(l2);
		screen.add(be1);
		screen.add(be2);
		screen.add(be3);
		screen.add(bd1);
		screen.add(bd2);
		screen.add(bd3);
		
		be1.addActionListener(this);
		be2.addActionListener(this);
		bd1.addActionListener(this);
		bd2.addActionListener(this);
		bd3.addActionListener(this);
		be3.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static FRTrainAdd getInstance(){
		if(instance == null){
			instance = new FRTrainAdd("janela trens");
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == be1){
			trainManager.addTrainLeftSlow(largura, altura);
		}
		else if(e.getSource() == be2){
			trainManager.addTrainLeftMedium(largura, altura);
		}
		else if(e.getSource() == be3){
			trainManager.addTrainLeftFast(largura, altura);
		}
		else if(e.getSource() == bd1){
			trainManager.addTrainRightSlow(largura, altura);
		}
		else if(e.getSource() == bd2){
			trainManager.addTrainRightMedium(largura, altura);
		}
		else if(e.getSource() == bd3){
			trainManager.addTrainRightFast(largura, altura);
			
		}
		
	}

}
