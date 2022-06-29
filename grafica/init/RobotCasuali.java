package init;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import catalogo.CatalogoRobot;
import catalogo.CatalogoSquadre;
import game.Quota;
import gestione.InventarioFrame;
import gestione.Negozio;
import interfaces.Sorter;
import robot.Combattente;
import robot.Robot;
import squadra.Squadra;
import utility.Dat;

public class RobotCasuali extends JFrame{
	public RobotCasuali(Squadra mySquadra) {
		squadra = mySquadra.clone();
		setSize(770, 540);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("BattleBots Lobby");
		add(createRobotPanel(), BorderLayout.WEST);
		add(createTeamPanel(), BorderLayout.EAST);
		add(createButtonsPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel createRobotPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "My Robots"));
		panel.add(createRobotTextArea());
		return panel;
	}
	
	private JScrollPane createRobotTextArea() {
		areaRobot = new JTextArea(26, 37);
		areaRobot.setEditable(false);
		JScrollPane scroll = new JScrollPane(areaRobot);
		areaRobot.append(" Name\t\tRole\t\tType\n\n");
		Robot robot;
		int i;
		for(i=0, robot=squadra.getRobot(i); robot!=null;) {
			if(robot instanceof Combattente) {
				areaRobot.append(" " + robot.getNome() + "\t\t" + "Fighter\t\t" + CatalogoRobot.getTipo(((Combattente) robot).getTipo()) + "\n");
			} else
				areaRobot.append(" " + robot.getNome() + "\t\t" + "Repairer\n");
			robot=squadra.getRobot(++i);
		}
		return scroll;
	}
	
	private JPanel createTeamPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Opposing Teams"));
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(gridBag);
		JScrollPane p = createTeamTextArea();
		gbc.fill = GridBagConstraints.PAGE_START;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 17; 
		gridBag.setConstraints(p, gbc);
		panel.add(p);
		JPanel p2 = createOrdinaPanel();
		gbc.fill = GridBagConstraints.PAGE_END;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gridBag.setConstraints(p2, gbc);
		panel.add(p2);
		return panel;
	}
	
	private JScrollPane createTeamTextArea() {
		areaTeams = new JTextArea(20, 23);
		areaTeams.setEditable(false);
		JScrollPane scroll = new JScrollPane(areaTeams);
		areaTeams.setText(" Name\t\tRanking\n\n");
		CatalogoSquadre cs = new CatalogoSquadre(); 
		for(int i=0; i<cs.maxSquadre; i++)
			areaTeams.append(" " + cs.getSquadra(i).getNome() + "\t\t" + cs.getSquadra(i).getRanking() + "\n");
		areaTeams.append(" " + squadra.getNome() + "\t\t" + squadra.getRanking() + "\n");
		return scroll;
	}
	
	private JPanel createButtonsPanel() {
		JPanel panel = new JPanel();
		panel.add(negozioButton());
		panel.add(vendiButton());
		panel.add(salvaButton());
		panel.add(torneoButton());
		panel.add(sfidaButton());
		return panel;
	}
	
	private JButton negozioButton() {
		JButton button = new JButton("Shop");
		button.setPreferredSize(new Dimension(100,50));
			
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame f = new Negozio(squadra);
				f.setDefaultCloseOperation(EXIT_ON_CLOSE);
				f.setVisible(true);
			}
			
		}
		button.addActionListener(new Listener());
		return button;
	}
	
	private JButton vendiButton() {
		JButton button = new JButton("TeamSetUp");
		button.setPreferredSize(new Dimension(100,50));
			
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame f = new InventarioFrame(squadra);
				f.setDefaultCloseOperation(EXIT_ON_CLOSE);
				f.setVisible(true);
			}
			
		}
		button.addActionListener(new Listener());
		return button;
	}
	
	private JButton salvaButton() {
		JButton button = new JButton("Save");
		button.setPreferredSize(new Dimension(100,50));
			
		class Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				File f = new File("partita.dat");
				Dat d = new Dat(f.getName());
				d.salva(squadra);
				JOptionPane.showMessageDialog(null, "Game Saved");
			}
			
		}
		button.addActionListener(new Listener());
		return button;
	}
	
	private JButton torneoButton() {
		JButton button = new JButton("Tournament");
		button.setPreferredSize(new Dimension(100,50));
		
		class torneoButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame quota = new Quota(squadra, true);
				quota.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				quota.setVisible(true);
			}
		}
		
		button.addActionListener(new torneoButtonListener());
		
		return button;
	}
	
	private JButton sfidaButton() {
		JButton button = new JButton("Challenge");
		button.setPreferredSize(new Dimension(100,50));
		
		class sfidaButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame quota = new Quota(squadra, false);
				quota.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				quota.setVisible(true);
			}
		}
		
		button.addActionListener(new sfidaButtonListener());
		return button;
	}
	
	private JPanel createOrdinaPanel() {
		JPanel panel = new JPanel();
		panel.add(alfaButton());
		panel.add(rankingButton());
		return panel;
	}
	
	private JButton alfaButton() {
		JButton button = new JButton("Alphabetical");
		button.setPreferredSize(new Dimension(100,50));
		button.addActionListener((e) -> {
			Sorter<Squadra> sorter = new Sorter<Squadra>((x,y)-> {
				return x.getNome().compareTo(y.getNome());
			});
			CatalogoSquadre cs = new CatalogoSquadre(); 
			for(int i=0; i<cs.maxSquadre; i++)
				sorter.add(cs.getSquadra(i));
			sorter.add(squadra);
			areaTeams.setText(" Name\t\tRanking\n\n");
			for(int i=0; i<cs.maxSquadre+1; i++)
				areaTeams.append(" " + sorter.getObj(i).getNome() + "\t\t" + sorter.getObj(i).getRanking() + "\n");
		});
		
		return button;
	}
	
	private JButton rankingButton() {
		JButton button = new JButton("Ranking");
		button.setPreferredSize(new Dimension(100,50));
		button.addActionListener((e) -> {
			Sorter<Squadra> sorter = new Sorter<Squadra>((x,y)-> {
				return y.getRanking() - x.getRanking();
			});
			CatalogoSquadre cs = new CatalogoSquadre(); 
			for(int i=0; i<cs.maxSquadre; i++)
				sorter.add(cs.getSquadra(i));
			sorter.add(squadra);
			areaTeams.setText(" Name\t\tRanking\n\n");
			for(int i=0; i<cs.maxSquadre+1; i++)
				areaTeams.append(" " + sorter.getObj(i).getNome() + "\t\t" + sorter.getObj(i).getRanking() + "\n");
		});
		
		return button;
	}
	
	private JTextArea areaRobot, areaTeams;
	private Squadra squadra;
	private static final long serialVersionUID = 1L;
}
