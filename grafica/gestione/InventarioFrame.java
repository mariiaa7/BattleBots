package gestione;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
import java.awt.BorderLayout;
public class InventarioFrame extends JFrame{

	public InventarioFrame(Squadra s)
	{
		mia = s.clone();
		setSize(1300, 700);
		setTitle("Inventory");
		setResizable(false);
		setLocationRelativeTo(null);
		add(mainPanel());
	}
	
	private JPanel mainPanel()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(vendiArmiMaterialiPanel());
		p.add(assegnaPanel());
		p.add(vendiRobotsPanel());
		return p;
	}
	
	private JPanel vendiRobotsPanel()
	{
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Your Robots"));
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(robotsArea());
		p.add(sceltaRobotPanel());
		p.add(buttonRobotsPanel());
		setAreaRobots();
		p.add(backButton());
		return p;
	}
	
	private JScrollPane robotsArea()
	{
		areaRobots = new JTextArea(20, 20);
		areaRobots.setEditable(false);
		JScrollPane s = new JScrollPane(areaRobots);
		return s;
	}
	
	public void setAreaRobots() {
		areaRobots.setText("Robots\n");
		for(int i=0; i<mia.getRobots().size(); i++)
			areaRobots.append(i + " " + mia.getRobots().get(i).toString() + "\n");
	}
	
	private JPanel sceltaRobotPanel()
	{
		JPanel p = new JPanel();
		p.add(creaLabel("Selling Robot's id: "));
		robotsField = new JTextField(13);
		p.add(robotsField);
		return p;
	}
	
	private JPanel buttonRobotsPanel()
	{
		JPanel p = new JPanel();
		JButton vendiRobot = new JButton("Sell Robot");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {											
				try {
					int i = Integer.parseInt(robotsField.getText());
					if(i < mia.getRobotSize()) {
						mia.setSaldo((int) mia.getRobots().get(i).getPrezzo());
						mia.getRobots().remove(i);
						setAreaRobots();
						setComboRobots();
						setComboRiparatore();
					}
				} catch (RejectedPaymentException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		vendiRobot.addActionListener(new Listener());
		p.add(vendiRobot);
		return p;
	}
	
	private JPanel vendiArmiMaterialiPanel()
	{
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(armiArea());
		p.add(sceltaArmaPanel());
		p.add(buttonArmiPanel());
		p.add(buttonMaterialiPanel());
		setAreaInventario();
		return p;
	}
	
	private JScrollPane armiArea()
	{
		areaArmi = new JTextArea(20, 20);
		areaArmi.setEditable(false);
		JScrollPane s = new JScrollPane(areaArmi);
		return s;
	}
	
	public void setAreaInventario() {
		areaArmi.setText("Weapons\n");
		for(int i=0; i<mia.getArmi().size(); i++)
			areaArmi.append(i + " " + mia.getArmi().get(i).toString() + "\n");
		areaArmi.append("\n\nMaterials\n");
		for(int i=0; i<mia.getMateriali().size(); i++)
			areaArmi.append(i + " " + mia.getMateriali().get(i).toString() + "\n");
	}
	
	private JPanel sceltaArmaPanel()
	{
		JPanel p = new JPanel();
		p.add(creaLabel("Selling Material's or Weapon's id: "));
		armiField = new JTextField(13);
		p.add(armiField);
		return p;
	}
	
	private JPanel buttonArmiPanel()
	{
		JPanel p = new JPanel();
		JButton vendiArma = new JButton("Sell Weapon");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {						
				try {
					int i = Integer.parseInt(armiField.getText());
					if(i < mia.getArmi().size()) {
						mia.setSaldo((int) mia.getArmi().get(i).getPrezzo());
						mia.getArmi().remove(i);
					}
				} catch (RejectedPaymentException e1) {
					e1.printStackTrace();
				}
				setAreaInventario();
			}
			
		}
		
		vendiArma.addActionListener(new Listener());
		p.add(vendiArma);
		return p;
	}
	
	
	private JPanel buttonMaterialiPanel()
	{
		JPanel p = new JPanel();
		JButton vendiMateriale = new JButton("Sell Material");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {											
				try {
					int i = Integer.parseInt(armiField.getText());
					if(i < mia.getMateriali().size()) {
						mia.setSaldo((int) mia.getMateriali().get(i).getPrezzo());
						mia.getMateriali().remove(i);
					}
				} catch (RejectedPaymentException e1) {
					e1.printStackTrace();
				}
				setAreaInventario();
			}
			
		}
		
		vendiMateriale.addActionListener(new Listener());
		p.add(vendiMateriale);
		return p;
	}
	
	private JPanel assegnaPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(assegnaRobotsPanel());
		panel.add(assegnaMaterialiPanel());
		return panel;
	}
	
	private void setComboRobots() {
		comboAttaccante.removeAllItems();
		for(int i=0; i < mia.getRobotSize(); i++) {
			if(mia.getRobot(i) instanceof Combattente) {
				comboAttaccante.addItem(i + " " + mia.getRobot(i).getNome());
			}	
		}
	}
	
	private JPanel assegnaRobotsPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Equip a Fighter with a Weapon"));

		comboAttaccante = new JComboBox<String>();
		setComboRobots();

		JTextField area = new JTextField(30);
		area.setEditable(false);
		JTextField armaField = new JTextField(10);
		
		comboAttaccante.addActionListener((e)->{
			if(comboAttaccante.getSelectedItem() != null) {
				//prende arma dall'inventario
				indiceRobotSelected = Integer.parseInt(((String)comboAttaccante.getSelectedItem()).substring(0, 1));
				if(!armaField.getText().isEmpty()) {
					if(Integer.parseInt(armaField.getText()) < mia.getArmi().size()) {
						armaInventario = mia.getArmi().get(Integer.parseInt(armaField.getText()));
						if(armaInventario instanceof Attacco) {
							//prende arma di attacco dal robot
							armaRobot = ((Combattente)mia.getRobots().get(indiceRobotSelected)).getAttacco();
						}else {
							//prende l'arma di difesa dal robot
							armaRobot = ((Combattente)mia.getRobots().get(indiceRobotSelected)).getDifesa();
						}
						area.setText(armaRobot.toString());
					}
				}
			}
			
		});
		panel.add(creaLabel("Insert the id of the weapon"));
		panel.add(armaField);
		panel.add(comboAttaccante);
		panel.add(area);
		panel.add(assegnaRobotsButton());

		return panel;
	}
	
	private JButton assegnaRobotsButton() {
		JButton button = new JButton("Equip Weapon");
		button.addActionListener((e)->{
			if(mia.getArmi().contains(armaInventario)) {
				if(armaInventario instanceof Attacco) {
					//assegna l'arma del robot all'inventario
					mia.getArmi().add(armaRobot);
					//assegna l'arma dell'inventario al robot
					((Combattente)mia.getRobots().get(indiceRobotSelected)).setAttacco((Attacco)armaInventario);
				}
				else {
					//assegna l'arma di difesa del robot all'inventario
					mia.getArmi().add(armaRobot);
					//assegna l'arma di difesa dell'inventario al robot
					((Combattente)mia.getRobots().get(indiceRobotSelected)).setDifesa((Difesa)armaInventario);
				}
				mia.getArmi().remove(armaInventario);
				setAreaInventario();
			}
		});
		return button;
	}
	
	private void setComboRiparatore() {
		comboRiparatore.removeAllItems();
		for(int i=0; i < mia.getRobotSize(); i++) {
			if(mia.getRobot(i) instanceof Riparatore)
				comboRiparatore.addItem(i + " " + mia.getRobot(i).getNome());	
		}
	}
	
	private JPanel assegnaMaterialiPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Equip a Repairer with a Material"));

		comboRiparatore = new JComboBox<String>();
		setComboRiparatore();
		
		//JTextArea area = new JTextArea(30,30);
		JTextField materialeField = new JTextField(10);
		setComboRiparatore();
		comboRiparatore.addActionListener((e)->{
			//prende arma dall'inventario
			if(!materialeField.getText().isEmpty()) {
				indiceRobotSelected = Integer.parseInt(((String)comboRiparatore.getSelectedItem()).substring(0, 1));
				indiceMaterialeSelected = Integer.parseInt(materialeField.getText());
				if(indiceMaterialeSelected < mia.getMateriali().size()) {
					materialeInventario = mia.getMateriali().get(indiceMaterialeSelected);
				}
			}
			
		});
		panel.add(creaLabel("Material's id "));
		panel.add(materialeField);
		panel.add(comboRiparatore);
		//panel.add(area);
		panel.add(assegnaMaterialiButton());


		return panel;
	}
	
	private JButton assegnaMaterialiButton() {
		JButton button = new JButton("Equip Material");
		button.addActionListener((e)->{
			((Riparatore)mia.getRobots().get(indiceRobotSelected)).addMateriale(materialeInventario);
			mia.getMateriali().remove(materialeInventario);
			setAreaInventario();
		});
		return button;
	}
	
	private JLabel creaLabel(String s)
	{
		JLabel l = new JLabel(s);
		return l;
	}
	
	private JButton backButton() {
		JButton button = new JButton("Back");
		button.addActionListener((e)->{
			dispose();
			System.out.println(mia);
			JFrame frame = new RobotCasuali(mia);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		});
		return button;
	}
	private JTextField armiField, robotsField;
	private JTextArea areaArmi, areaRobots;
	private Squadra mia;
	private Arma armaInventario, armaRobot;
	private Materiale materialeInventario, materialeRobot;
	private int indiceRobotSelected, indiceMaterialeSelected;
	JComboBox<String> comboAttaccante, comboRiparatore;
}
