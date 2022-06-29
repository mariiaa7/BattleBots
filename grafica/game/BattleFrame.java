package game;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import exception.RejectedPaymentException;
import init.RobotCasuali;
import partita.Sfida;
import robot.Combattente;
import robot.Riparatore;
import robot.Robot;
import squadra.Squadra;

public class BattleFrame extends JFrame{
	public BattleFrame(ArrayList<Robot> squadra1,  Squadra squadra2, Sfida s, Squadra mia, boolean torneo) {
		mySquad = squadra1;
		avversario = squadra2;
		sfida = s;//.clone();
		this.mia = mia;
		isTorneo = torneo;
		riempiSfida();
		setSize(1400, 750);
		setLocationRelativeTo(null);
		setTitle("Let's Battle!!");

		add(mainPanel());
	}
	
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(areaPanel());
		panel.add(choicePanel());
		
		return panel;
	}
	
	private JPanel areaPanel() {
		JPanel panel = new JPanel();
		JPanel p2 = new JPanel();
		area = new JTextArea(38, 48);
		area.setEditable(false);
		JScrollPane scroll = new JScrollPane(area);
		p2.add(scroll);
		area.append("Enemy Team\n");
		for(int i=0; i<sfida.getArrayRobot2().size(); i++)
			area.append(sfida.getRobot2(i).toString() + "\n\n");
		panel.add(p2);
		return panel;
	}
	
	private JPanel choicePanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(nCombattenti() + 2, 1));
		attackGroupButton();
		panel.add(combattiButton());
		return panel;
	}
	
	private void attackGroupButton() {
		radioList = new ArrayList<ButtonGroup>();
		
		for(Robot r : sfida.getArrayRobot1()) {
			if(r instanceof Combattente) {
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(1, nAvversari()+1));
				JLabel lb = new JLabel(r.getNome() + " attacks");
				ButtonGroup bg = new ButtonGroup();
				p.add(lb);
				Robot s;
				int i;
				for(i = 0, s = sfida.getRobot2(i); s != null; i++, s=sfida.getRobot2(i)) {
					JRadioButton rB = new JRadioButton(s.getNome());
					bg.add(rB);	
					p.add(rB);
				}
				radioList.add(bg);
				panel.add(p);
			}
		}
		
	}
	
	
	private void repaireGroupButton() {
		radioList = new ArrayList<ButtonGroup>();
		
		for(Robot r : sfida.getArrayRobot1()) {
			if(r instanceof Riparatore) {
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(1, nCombattenti()));
				JLabel lb = new JLabel(r.getNome() + " repairs");
				ButtonGroup bg = new ButtonGroup();
				p.add(lb);
				Robot s;
				int i;
				for(i = 0, s = sfida.getRobot1(i); s != null; i++, s=sfida.getRobot1(i)) {
					JRadioButton rB = new JRadioButton(s.getNome());
					bg.add(rB);	
					p.add(rB);
				}
				radioList.add(bg);
				panel.add(p);
			}
		}
		
	}
	
	
	private JPanel combattiButton() 
	{
		JPanel panel = new JPanel();
		JPanel p= this.panel;
		combatti = new JButton("Fight");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				int j=0, k;
				for(int i=0; i<radioList.size(); i++) {
					ButtonGroup b = radioList.get(i);
					k=0;
					for (Enumeration<AbstractButton> buttons = b.getElements(); buttons.hasMoreElements();) {
			            AbstractButton button = buttons.nextElement();			            
			            if (button.isSelected()) {			            	
			                for(; j<sfida.getArrayRobot1().size(); j++) {
				                	if(sfida.getArrayRobot1().get(j) instanceof Combattente) {
				        				while(k>=sfida.getArrayRobot2().size())k--;
				        				if(k>=0)
				        					sfida.attacca((Combattente)sfida.getRobot1(j), k, true);
				                		j++;
				                		break;
				                	}
			                }
			            }
			            k++;
			        }
				}
				
				
				for(Robot r : sfida.getArrayRobot2()) {
					if(r instanceof Combattente) {
						Random ran = new Random();
						if(sfida.getArrayRobot1().size() > 0) {
							int num=ran.nextInt(sfida.getArrayRobot1().size());
							sfida.attacca((Combattente)r, num, false);
						}
					}
				}
				
				
				if(nCombattenti() == 0) { 
					sfida.controllo();
					area.setText("\t[ You lose :( ]");
					JOptionPane.showMessageDialog(null, "You lose, try harder!!!");
					mia.getRankingObj().setGiocate(false);
					if(isTorneo) {
						dispose();
					}else {
						JOptionPane.showMessageDialog(null,"Your new Balance is: " + mia.getSaldo());
						dispose();
						RobotCasuali rc = new RobotCasuali(mia);
						rc.setVisible(true);
					}
					return;
				}
				if(sfida.getArrayRobot2().size()==0) { 
					sfida.controllo();
					area.setText("vittoria");
					JOptionPane.showMessageDialog(null, "Congratulations, YOU WIN!!!");
					try {
						mia.setSaldo(sfida.getVincita());
					} catch (RejectedPaymentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mia.getRankingObj().setGiocate(true);
					if(isTorneo) {
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Your new Balance is: " + mia.getSaldo());
						dispose();
						RobotCasuali rc = new RobotCasuali(mia);
						rc.setVisible(true);
					}
					return;
				}
			
				
				area.setText("Enemy Team\n");
				for(int i=0; i<sfida.getArrayRobot2().size(); i++)
					area.append(sfida.getRobot2(i).toString() + "\n\n");
				area.append("\nMy Team\n");
				for(int i=0; i<sfida.getArrayRobot1().size(); i++)
					area.append(sfida.getRobot1(i).toString() + "\n\n");
				
				radioList.removeAll(radioList);
				p.removeAll();
				p.setLayout(new GridLayout(nRiparatori()+1, 1));
				repaireGroupButton();
				p.add(riparaButton());
				p.revalidate();
				p.repaint();
			}	
		}
		
		combatti.addActionListener(new Listener());
		panel.add(combatti);
		return panel;
	}
	
	
	
	private JPanel riparaButton() 
	{
		JPanel panel = new JPanel();
		JPanel p= this.panel;
		combatti = new JButton("Repair");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				
				int j=0, k;
				for(int i=0; i<radioList.size(); i++) {
					ButtonGroup b = radioList.get(i);
					k=0;
					for (Enumeration<AbstractButton> buttons = b.getElements(); buttons.hasMoreElements();) {
			            AbstractButton button = buttons.nextElement();		            
			            if (button.isSelected()) {			            	
			                for(; j<sfida.getArrayRobot1().size(); j++) {
			                	if(sfida.getArrayRobot1().get(j) instanceof Riparatore) {
			                		while(k>=sfida.getArrayRobot1().size())k--;
			        				if(k>=0)
			        					sfida.cura((Riparatore)sfida.getRobot1(j), k, false);
			                		j++;
			                		break;
			                	}
			                }
			            }
			            k++; 
			        }
				}
				
				for(Robot r : sfida.getArrayRobot2()) {
					if(r instanceof Riparatore) {
						Random ran = new Random();
						if(sfida.getArrayRobot2().size()>0) {
							int num = ran.nextInt(sfida.getArrayRobot2().size());
							sfida.cura((Riparatore)r, num, true);
						}
					}
				}
				
				
				area.setText("Enemy Team\n");
				for(int i=0; i<sfida.getArrayRobot2().size(); i++)
					area.append(sfida.getRobot2(i).toString() + "\n\n");
				area.append("\nMy Team\n");
				for(int i=0; i<sfida.getArrayRobot1().size(); i++)
					area.append(sfida.getRobot1(i).toString() + "\n\n");
				
				
				radioList.removeAll(radioList);
				p.removeAll();
				p.setLayout(new GridLayout(nCombattenti() + 2, 1));
				attackGroupButton();
				p.add(combattiButton());
				p.revalidate();
				p.repaint();
			}	
		}
		
		combatti.addActionListener(new Listener());
		panel.add(combatti);
		panel.add(abbandona(), BorderLayout.SOUTH);
		return panel;
	}
	
	
	
	private int nCombattenti() {
		int n=0;
		for(Robot r : sfida.getArrayRobot1()) 
			if(r instanceof Combattente) 
				n++;
		return n;
	}
	
	
	private int nRiparatori() {
		int n=0;
		for(Robot r : sfida.getArrayRobot1()) 
			if(r instanceof Riparatore) 
				n++;
		return n;
	}
	
	private int nAvversari() {
		int n=0, i;
		Robot s;
		for(i = 0, s = avversario.getRobot(i); s != null; i++, s=avversario.getRobot(i)) 
			n++;
		return n;
	}
	
	private void riempiSfida()
	{
		for(Robot r : mySquad) {
			sfida.addRobot1(r);
		}
		int i;
		Robot s;
		for(i = 0, s = avversario.getRobot(i); s != null; i++, s=avversario.getRobot(i)) {
			sfida.addRobot2(s);
		}
	}
	
	private JPanel abbandona()
	{
		JPanel p = new JPanel();
		abbandona = new JButton("Leave");
		
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				sfida.abbandono();
				dispose();
				RobotCasuali rc = new RobotCasuali(mia);
				rc.setVisible(true);
			}
			
		}
		abbandona.addActionListener(new Listener());
		p.add(abbandona);
		return p;
	}
	
	private Sfida sfida;
	private JButton combatti, abbandona;
	private JPanel panel;
	private ArrayList<ButtonGroup> radioList; 
	private JTextArea area;
	private ArrayList<Robot> mySquad;
	private Squadra avversario, mia;
	private boolean isTorneo;
}
