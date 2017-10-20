package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FRTrainAdd extends JFrame implements ActionListener {
	private JButton b1, b2,be1,be2,be3,bd1,bd2,bd3;
	private JLabel l1,l2;
	private Container screen;
	private static FRTrainAdd instance = null;
	
	FRTrainAdd(String s){
		super(s);
		
		screen = getContentPane();
		screen.setLayout(null);
		
		b1 = new JButton("Adicionar trens a esquerda");
		b2 = new JButton("adicionar trens a direita");
		b1.setBounds(20, 20, 200, 30);
		b2.setBounds(350, 20, 200, 30);
		
		be1 = new JButton("60 Km/h");
		be2 = new JButton("80 Km/h");
		be3 = new JButton("100 Km/h");
		bd1 = new JButton("60 Km/h");
		bd2 = new JButton("80 Km/h");
		bd3 = new JButton("100 Km/h");
		
		l1 = new JLabel("Alterar velocidade dos trens a esquerda:");
		l2 = new JLabel("alterar velocidade dos trens a direita:");
		l1.setBounds(20,60,400,30);
		l2.setBounds(350, 60, 400, 30);
		
		be1.setBounds(20, 100, 90, 30);
		be2.setBounds(20, 140, 90, 30);
		be3.setBounds(20, 180, 90, 30);
		
		bd1.setBounds(350, 100, 90, 30);
		bd2.setBounds(350, 140, 90, 30);
		bd3.setBounds(350, 180, 90, 30);
		
		screen.add(b1);
		screen.add(b2);
		screen.add(l1);
		screen.add(l2);
		screen.add(be1);
		screen.add(be2);
		screen.add(be3);
		screen.add(bd1);
		screen.add(bd2);
		screen.add(bd3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
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
			instance = new FRTrainAdd("alterar trens");
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
