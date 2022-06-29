package gestione;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import catalogo.CatalogoArmi;
import catalogo.CatalogoMateriali;
import catalogo.CatalogoRobot;
import equipaggiamento.Arma;
import equipaggiamento.Attacco;
import equipaggiamento.Difesa;
import equipaggiamento.Materiale;
import exception.RejectedPaymentException;
import init.RobotCasuali;
import robot.Combattente;
import robot.Riparatore;
import robot.Robot;
import squadra.Squadra;

public class Negozio extends JFrame{

	public Negozio(Squadra s)
	{
		mia = s.clone();
		setSize(1300, 700);
		setTitle("Shop");
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 3));
		add(armiPanel());
		add(robotPanel());
		add(materialiPanel());
	}
	
	
	
	public JPanel armiPanel()
	{
		JPanel p1 = new JPanel();
		p1.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		p1.add(sceltaTipoArmi());
		p1.add(armiAreaPanel());
		p1.add(sceltaArma());
		
		
		return p1;
	}
	
	private JPanel sceltaTipoArmi()
	{
		JPanel scelta = new JPanel();
		JComboBox c1 = new JComboBox();
		c1.setEditable(false);
		c1.addItem("Attack");
		c1.addItem("Defence");
		c1.setSelectedIndex(0);
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				String s = (String) c1.getSelectedItem();
				if(s.equals("Attack")){
					areaArmi.setText("");
					for(Arma a : ca.getCatalogo()) 
						if(a instanceof Attacco) 
							areaArmi.append(a.toString() + "\n");
				}
				else {
					areaArmi.setText("");
					for(Arma a : ca.getCatalogo()) 
						if(a instanceof Difesa) 
							areaArmi.append(a.toString() + "\n");
				}
			}
			
		}
		
		c1.addActionListener(new Listener());
		scelta.add(creaLabel("Type"));
		scelta.add(c1);
		return scelta;
	}
	
	private JPanel armiAreaPanel()
	{
		JPanel p = new JPanel();
		areaArmi = new JTextArea(26, 28);
		areaArmi.setEditable(false);
		JScrollPane s = new JScrollPane(areaArmi);
		p.add(s);
		areaArmi.append("Choose the type of the Weapon");
		return p;
	}
	
	private JPanel sceltaArma()
	{
		JPanel p = new JPanel();
		p.add(creaLabel("Choice: "));
		fieldArmi = new JTextField(14);
		p.add(fieldArmi);
		p.add(armiButton());
		return p;
	}
	
	private JLabel creaLabel(String s)
	{
		JLabel label = new JLabel(s);
		return label;
	}
	
	private JButton armiButton()
	{
		shopArmi = new JButton("Buy");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				for(Arma a : ca.getCatalogo()) {
					if(a.getNome().equals(fieldArmi.getText())) {
							try {
								mia.setSaldo((int) -a.getPrezzo());
								mia.addArma(a);
								areaArmi.append("\nWeapon purchased\n");
							} catch (RejectedPaymentException e1) {
								
								areaArmi.append("\nInsufficient Balance, current Balance: " + mia.getSaldo() + "$\n");
							}
					}
				}
			}
		}

		shopArmi.addActionListener(new Listener());
		return shopArmi;
	}
	
	public JPanel robotPanel()
	{
		JPanel p1 = new JPanel();
		p1.setBorder(new TitledBorder(new EtchedBorder(), "Robots"));
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		p1.add(sceltaTipoRobot());
		p1.add(robotAreaPanel());
		p1.add(sceltaRobot());
		return p1;
	}
	
	private JPanel sceltaTipoRobot()
	{
		JPanel scelta = new JPanel();
		JComboBox c1 = new JComboBox();
		c1.setEditable(false);
		c1.addItem("Fighter");
		c1.addItem("Repairer");
		c1.setSelectedIndex(0);
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				String s = (String) c1.getSelectedItem();
				if(s.equals("Fighter")){
					areaRobot.setText("");
					CatalogoRobot ca = new CatalogoRobot();
					for(Robot a : ca.getCatalogo()) 
						if(a instanceof Combattente) 
							areaRobot.append(a.toString() + "\n");
				}
				else {
					areaRobot.setText("");
					CatalogoRobot ca = new CatalogoRobot();
					for(Robot a : ca.getCatalogo()) 
						if(a instanceof Riparatore) 
							areaRobot.append(a.toString() + "\n");
				}
			}
			
		}
		
		c1.addActionListener(new Listener());
		scelta.add(creaLabel("Type"));
		scelta.add(c1);
		return scelta;
	}
	
	private JPanel robotAreaPanel()
	{
		JPanel p = new JPanel();
		areaRobot = new JTextArea(26, 28);
		areaRobot.setEditable(false);
		JScrollPane s = new JScrollPane(areaRobot);
		p.add(s);
		areaRobot.append("Choose the type of the Robots");
		return p;
	}
	
	private JPanel sceltaRobot()
	{
		JPanel p = new JPanel();
		p.add(creaLabel("Choice: "));
		fieldRobot = new JTextField(14);
		p.add(fieldRobot);
		p.add(robotButton());
		return p;
	}
	
	private JButton robotButton()
	{
		shopRobot = new JButton("Buy");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(mia.getRobotSize()<10){
					for(Robot a : cr.getCatalogo()) {
						if(a.getNome().equals(fieldRobot.getText())) {
								try {
									mia.setSaldo((int) -a.getPrezzo());
									mia.addRobot(a);
									areaRobot.append("\nRobot purchased\n");

								} catch (RejectedPaymentException e1) {
									
									areaRobot.append("\nInsufficient Balance, current Balance: " + mia.getSaldo() + "$\n");
								}
						}
					}
				}
				else {
					areaRobot.setText("You have reached the maximum number of Robots for your team\n");
				}
			}
		}

		shopRobot.addActionListener(new Listener());
		return shopRobot;
	}
	
	
	public JPanel materialiPanel()
	{
		JPanel p1 = new JPanel();
		p1.setBorder(new TitledBorder(new EtchedBorder(), "Repairer's Materials"));
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		p1.add(matPanel());
		p1.add(matButton());
		p1.add(indietroButton());
		return p1;
	}
	
	private JPanel matPanel()
	{
		JPanel p = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		mat1 = new JRadioButton("Potion, Cures: 15hp, Price: 7$");
		mat2 = new JRadioButton("SuperPotion, Cures: 24hp, Price: 14$");
		mat3 = new JRadioButton("IperPotion, Cures: 47hp, Price: 26$");
		mat4 = new JRadioButton("Revive, Cures: 69hp, Price: 44$");
		mat5 = new JRadioButton("Max Revive, Cures: 90hp, Price: 79$");
		bg.add(mat1);
		bg.add(mat2);
		bg.add(mat3);
		bg.add(mat4);
		bg.add(mat5);
		p.add(mat1);
		p.add(mat2);
		p.add(mat3);
		p.add(mat4);
		p.add(mat5);
		
		JPanel p2 = new JPanel();
		areaMat = new JTextArea(23, 28);
		areaMat.setEditable(false);
		JScrollPane s = new JScrollPane(areaMat);
		p2.add(s);
		areaMat.append("Choose a material\n");
		p.add(p2);
		return p;
	}
	
	private JPanel matButton()
	{
		JPanel p = new JPanel();
		shopMat = new JButton("Buy");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				if(mat1.isSelected()) {
					for(Materiale m : cm.getCatalogo()) {
						if(m.getNome().equals(mat1.getText().substring(0, mat1.getText().indexOf(","))))
								try {
									mia.setSaldo((int)-m.getPrezzo());
									mia.addMateriali(m);
									areaMat.append("Material purchased\n");
								} catch (RejectedPaymentException e1) {
									
									areaMat.append("Insufficient Balance, current Balance: " + mia.getSaldo() + "$\n");
								}
					}
				}
				if(mat2.isSelected()) {
					for(Materiale m : cm.getCatalogo()) {
							if(m.getNome().equals(mat2.getText().substring(0, mat2.getText().indexOf(","))))
								try {
									mia.setSaldo((int)-m.getPrezzo());
									mia.addMateriali(m);
									areaMat.append("Material purchased\n");
								} catch (RejectedPaymentException e1) {
									
									areaMat.append("Insufficient Balance, current Balance: " + mia.getSaldo() + "$\n");
								}
					}
				}
				if(mat3.isSelected()) {
					for(Materiale m : cm.getCatalogo()) {
							if(m.getNome().equals(mat3.getText().substring(0, mat3.getText().indexOf(","))))
								try {
									mia.setSaldo((int)-m.getPrezzo());
									mia.addMateriali(m);
									areaMat.append("Material purchased\n");
								} catch (RejectedPaymentException e1) {
									
									areaMat.append("Insufficient Balance, current Balance: " + mia.getSaldo() + "$\n");
								}
					}
				}
				if(mat4.isSelected()) {
					for(Materiale m : cm.getCatalogo()) {
							if(m.getNome().equals(mat4.getText().substring(0, mat4.getText().indexOf(","))))
								try {
									mia.setSaldo((int)-m.getPrezzo());
									mia.addMateriali(m);
									areaMat.append("Material purchased\n");
								} catch (RejectedPaymentException e1) {
									
									areaMat.append("Insufficient Balance, current Balance: " + mia.getSaldo() + "$\n");
								}
					}
				}
				if(mat5.isSelected()) {
					for(Materiale m : cm.getCatalogo()) {
							if(m.getNome().equals(mat5.getText().substring(0, mat5.getText().indexOf(","))))
								try {
									mia.setSaldo((int)-m.getPrezzo());
									mia.addMateriali(m);
									areaMat.append("Material purchased\n");
								} catch (RejectedPaymentException e1) {
									
									areaMat.append("Insufficient Balance, current Balance: " + mia.getSaldo() + "$\n");
								}
					}
				}
			}
			
		}
		
		shopMat.addActionListener(new Listener());
		p.add(shopMat);
		return p;
	}
	
	private JPanel indietroButton()
	{
		JPanel p = new JPanel();
		ind = new JButton("Back");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				JFrame f = new RobotCasuali(mia.clone());
				f.setDefaultCloseOperation(EXIT_ON_CLOSE);
				f.setVisible(true);
			}
		}
		
		ind.addActionListener(new Listener());
		p.add(ind);
		return p;
	}
	
	private static CatalogoMateriali cm = new CatalogoMateriali();
	private static CatalogoRobot cr = new CatalogoRobot();
	private static CatalogoArmi ca = new CatalogoArmi();
	private JRadioButton mat1, mat2, mat3, mat4, mat5;
	private JButton shopArmi, shopRobot, shopMat, ind;
	private JTextField fieldArmi, fieldRobot;
	private JTextArea areaArmi, areaRobot, areaMat;
	private Squadra mia;
}
