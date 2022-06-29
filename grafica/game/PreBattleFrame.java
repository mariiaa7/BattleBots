package game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import catalogo.CatalogoRobot;
import catalogo.CatalogoSquadre;
import exception.RejectedPaymentException;
import init.RobotCasuali;
import partita.Sfida;
import robot.Combattente;
import robot.Riparatore;
import robot.Robot;
import squadra.Squadra;

public class PreBattleFrame extends JFrame{
	public PreBattleFrame(Squadra mySquadra, Sfida mySfida, boolean torneo) {
		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Select the Robots in your Team");
		isTorneo = torneo;
		squadra = mySquadra.clone();
		sfida = mySfida;
		add(mainPanel());
	}
	
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(northPanel(), BorderLayout.NORTH);
		panel.add(centerPanel(), BorderLayout.CENTER);
		panel.add(nextButton(), BorderLayout.SOUTH);
		return panel;
	}
	
	private JPanel northPanel() {
		CatalogoSquadre cs = new CatalogoSquadre();
		avversario = cs.getSquadra(squadra);
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("Favourited Type: " + CatalogoRobot.getTipo(sfida.getScenario()) + ", ");
		JLabel label2 = new JLabel("Enemy's Ranking: " + avversario.getRanking());
		panel.add(label1);
		panel.add(label2);
		return panel;
	}
	
	private JPanel centerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10,1));
		Robot r;
		int i=0;
		check = new ArrayList<JCheckBox>();
		for(r=squadra.getRobot(i); r!=null; i++, r=squadra.getRobot(i)) {
			if(r instanceof Riparatore)
				check.add(new JCheckBox(r.getNome()));
			else
				check.add(new JCheckBox(r.getNome() + "  " + ((Combattente) r).getAttacco().getNome() 
						+ "=" + ((Combattente) r).getAttacco().getValore() + " ATK, " +
						((Combattente) r).getDifesa().getNome() + "=" + 
						((Combattente) r).getDifesa().getValore() + " DEF"));
			panel.add(check.get(i));
		}
		return panel;
	}
	
	private boolean checkChoice() {
		int count = 0;
		mySquad = new ArrayList<Robot>();
		for(JCheckBox c: check) {
			if(c.isSelected()) {
				if(c.getText().indexOf(" ")>0) {
					mySquad.add(squadra.getRobot(c.getText().substring(0, c.getText().indexOf(" "))));
					count++;
				}else {
					mySquad.add(squadra.getRobot(c.getText()));
					count++;
				}
			}
		}
		if(count==nMaxRobot) 
			return true;
		return false;
	}
	
	private JButton nextButton() {
		JButton next = new JButton("Next");
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkChoice()) {
					if(isTorneo) {
						setVisible(false);
						JFrame bf = new BattleFrame(mySquad, avversario, sfida, squadra, isTorneo);
						bf.setVisible(true);
						bf.setDefaultCloseOperation(EXIT_ON_CLOSE);
						bf.addWindowListener(new java.awt.event.WindowAdapter() {
					        @Override
					        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					            // your code
								dispose();
					        }
					    });
					}else {
						try {
							squadra.setSaldo(-sfida.getQuota());
							dispose();

							JFrame bf = new BattleFrame(mySquad, avversario, sfida, squadra, isTorneo);
							bf.setVisible(true);
							bf.setDefaultCloseOperation(EXIT_ON_CLOSE);
						} catch (RejectedPaymentException err) {
							// TODO Auto-generated catch block
							dispose();
							JOptionPane.showMessageDialog(null, err.getMessage());
							JFrame frame = new RobotCasuali(squadra);
							frame.setVisible(true);
							frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
						}
					}
				}
				
			}
			
		});
		
		next.setPreferredSize(new Dimension(100,50));
		return next;
	}
	
	
	private ArrayList<Robot> mySquad;
	private ArrayList<JCheckBox> check;
	private final static int nMaxRobot = 5;
	private Squadra squadra, avversario;
	private Sfida sfida;
	private boolean isTorneo;
}
