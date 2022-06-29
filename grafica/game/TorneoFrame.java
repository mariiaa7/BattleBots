package game;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import catalogo.CatalogoSquadre;
import exception.RejectedPaymentException;
import init.RobotCasuali;

import java.awt.GridLayout;
import java.util.ArrayList;

import partita.Sfida;
import partita.Torneo;
import squadra.Squadra;

public class TorneoFrame extends JFrame{
	public TorneoFrame(Squadra mySquadra, Torneo myTorneo) {
		squadra = mySquadra.clone();
		torneo = myTorneo;
		
		quarti = new ArrayList<JLabel>();
		for(int i=0;i<8; i++)
			quarti.add(title(""));
		
		semifinale = new ArrayList<JLabel>();
		for(int i=0;i<4; i++)
			semifinale.add(title(""));
		
		finale = new ArrayList<JLabel>();
		for(int i=0;i<2; i++)
			finale.add(title(""));
		
		vincitore = title("");
		
		setSize(750, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Challenge");
		add(mainPanel());
		setSquadre();
	}
	
	private void setSquadre() {
		torneo.addSquadra(squadra);
		quarti.get(0).setText(squadra.getNome());
		CatalogoSquadre catalogo = new CatalogoSquadre();
		Squadra sq;
		int i;
		for(i=0, sq = catalogo.getSquadra() ;i<quarti.size(); i++, sq = catalogo.getSquadra()) {
			if((i+1)<quarti.size()) {
				JLabel label;
				int j;
				boolean check=false;
				for(j=0,label=quarti.get(j); j<(i+1); j++, label=quarti.get(j))
					if(label.getText().equals(sq.getNome()))check=true;
				if(!check) {
					torneo.addSquadra(sq);
					quarti.get(i+1).setText(sq.getNome());
				}else
					i--;
			}
		}
	}
	
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(15,7));
		//prima riga
		for(int i=0; i<3; i++)
			panel.add(button(" "));
		panel.add(button("Tournament"));
		for(int i=0; i<3; i++)
			panel.add(button(" "));
		//seconda riga
		panel.add(quarti.get(0));
		for(int i=1; i<6; i++)
			panel.add(button(" "));
		panel.add(quarti.get(4));
		//terza riga
		for(int i=0; i<7; i++)
			panel.add(button(" "));
		//quarta riga
		panel.add(button(" "));
		panel.add(semifinale.get(0));
		panel.add(button(" "));
		panel.add(button(" "));
		panel.add(button(" "));
		panel.add(semifinale.get(2));
		panel.add(button(" "));
		//quinta riga
		for(int i=0; i<7; i++)
			panel.add(button(" "));
		//sesta riga
		panel.add(quarti.get(1));
		for(int i=1; i<6; i++)
			panel.add(button(" "));
		panel.add(quarti.get(5));
		//settima riga
		for(int i=0; i<3; i++)
			panel.add(button(" "));
		panel.add(vincitore);
		for(int i=0; i<3; i++)
			panel.add(button(" "));
		//ottava riga
		panel.add(button(" "));
		panel.add(button(" "));
		panel.add(finale.get(0));
		panel.add(button(" "));
		panel.add(finale.get(1));
		panel.add(button(" "));
		panel.add(button(" "));
		//nona
		for(int i=0; i<7; i++)
			panel.add(button(" "));
		//decima
		panel.add(quarti.get(2));
		for(int i=1; i<6; i++)
			panel.add(button(" "));
		panel.add(quarti.get(6));
		//undicesima
		for(int i=0; i<7; i++)
			panel.add(button(" "));
		//dodicesima
		panel.add(button(" "));
		panel.add(semifinale.get(1));
		panel.add(button(" "));
		panel.add(button(" "));
		panel.add(button(" "));
		panel.add(semifinale.get(3));
		panel.add(button(" "));
		//tredicesima
		for(int i=0; i<7; i++)
			panel.add(button(" "));
		//quattordicesima
		panel.add(quarti.get(3));
		for(int i=1; i<6; i++)
			panel.add(button(" "));
		panel.add(quarti.get(7));
		//quindicesima
		for(int i=0; i<3; i++)
			panel.add(button(" "));
		panel.add(nextButton());
		for(int i=0; i<3; i++)
			panel.add(button(" "));
		return panel;
	}
	
	private JLabel button(String str) {
		JLabel label = new JLabel(str, SwingConstants.CENTER);
		return label;
	}
	
	private JLabel title(String str) {
		JLabel label = new JLabel(str, SwingConstants.CENTER);
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		return label;
	}
	
	private JButton nextButton() {
		JButton button = new JButton("start now");
		
		button.addActionListener((e)->{
			PreBattleFrame frame;
			torneo.creaSfide();
			System.out.println(torneo.squadreSize());
			Squadra mySq = torneo.getSquadra(0);
			if(mySq.toString().equals(squadra.toString())) {
				
				frame = new PreBattleFrame(squadra, torneo.getSfida(0), true);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.addWindowListener(new java.awt.event.WindowAdapter() {
			        @Override
			        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
			            // your code
			        	RobotCasuali frame;
			        	if(torneo.squadreSize() > 2 && torneo.getSfida(0).getPerdente().equals(squadra.getNome())) {
			        		dispose();
							JOptionPane.showMessageDialog(null, "Il tuo saldo e': " + squadra.getSaldo());
			        		frame = new RobotCasuali(squadra);
			        		frame.setVisible(true);
			        		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			        	}else {
				        	torneo.randomVinc(1);
							torneo.girone();
							setGraphic();
							if(squadra.getNome().equals(torneo.getPrimoPosto())) {
								JOptionPane.showMessageDialog(null, "Complimenti, hai fatto il primo posto!!!!");
								squadra.getRankingObj().setGiocate(true);
								squadra.getRankingObj().setValore(5);
								JOptionPane.showMessageDialog(null, "Il tuo saldo e': " + squadra.getSaldo());
								dispose();
				        		frame = new RobotCasuali(squadra);
				        		frame.setVisible(true);
				        		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
							}else if(squadra.getNome().equals(torneo.getSecondoPosto())) {
								JOptionPane.showMessageDialog(null, "Hey, hai fatto il primo dei perdenti, Bravo!!!");
								squadra.getRankingObj().setGiocate(true);
								JOptionPane.showMessageDialog(null, "Il tuo saldo e': " + squadra.getSaldo());
								dispose();
				        		frame = new RobotCasuali(squadra);
				        		frame.setVisible(true);
				        		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
							}
			        	}
			        }
			    });
			}
			else {
				System.out.println("mi disp, ");
			}
		});
		
		return button;
	}
	
	private void setGraphic(){

		if(torneo.squadreSize() == semifinale.size()) {
			
			for(int i=0; i<semifinale.size(); i++)
				semifinale.get(i).setText(torneo.getSquadra(i).getNome());
			
		}else if(torneo.squadreSize() == finale.size()) {
			
			if(torneo.getPrimoPosto() != null){
				vincitore.setText(torneo.getPrimoPosto());
			}else
				for(int i=0; i<finale.size(); i++)
					finale.get(i).setText(torneo.getSquadra(i).getNome());
		}
	}
	
	private Squadra squadra;
	private ArrayList<JLabel> quarti;
	private ArrayList<JLabel> semifinale;
	private ArrayList<JLabel> finale;
	private JLabel vincitore;
	private Torneo torneo;
}
