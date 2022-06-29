package game;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import catalogo.CatalogoSquadre;
import exception.RejectedPaymentException;
import init.RobotCasuali;
import partita.Sfida;
import partita.Torneo;
import squadra.Squadra;

public class Quota extends JFrame{
	public Quota(Squadra mySquadra, boolean torneo) {
		squadra = mySquadra.clone();
		setSize(200, 130);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Challenge");
		add(createPanel(torneo));
	}
	
	private JPanel createPanel(boolean isTorneo) {
		JPanel panel = new JPanel();
		if(isTorneo)
		{
			panel.setLayout(new GridLayout(4, 1));
			torneo = new Torneo(squadra.getNome(), quotaTorneo);
			panel.add(createLabel("      Entry Fee: " + quotaTorneo));
			panel.add(createLabel("    	 Victory Earnings: 1� " + torneo.getVincitaPrimoPosto()));
			panel.add(createLabel("                  2� " + torneo.getVincitaSecondoPosto()));
			panel.add(createButtonNext(isTorneo, torneo));
		}else {
			panel.setLayout(new GridLayout(3, 1));
			CatalogoSquadre cs = new CatalogoSquadre();
			Squadra avversario = cs.getSquadra();
			sfida = new Sfida(squadra.getNome(), avversario.getNome(), quotaSfida);
			panel.add(createLabel("        Entry Fee: " + quotaSfida));
			panel.add(createLabel("        Victory Earnings: " + sfida.getVincita()));
			panel.add(createButtonNext(isTorneo, sfida));
		}
		return panel;
	}
	
	private JLabel createLabel(String str) {
		JLabel label = new JLabel(str);
		
		return label;
	}
	
	private JButton createButtonNext(boolean isTorneo, Object match) {
		JButton button = new JButton("Next");
		
		
		class ActionNext implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(isTorneo) {
					try {
						squadra.setSaldo(-torneo.getQuota());
						dispose();
						JFrame frameTorneo = new TorneoFrame(squadra, (Torneo) match);
						frameTorneo.setDefaultCloseOperation(EXIT_ON_CLOSE);
						frameTorneo.setVisible(true);
					} catch (RejectedPaymentException err) {
						// TODO Auto-generated catch block
						dispose();
						JOptionPane.showMessageDialog(null, err.getMessage());
						JFrame frameRobotCasuali = new RobotCasuali(squadra);
						frameRobotCasuali.setDefaultCloseOperation(EXIT_ON_CLOSE);
						frameRobotCasuali.setVisible(true);
					}

				}else {
					dispose();
					JFrame frameSfida = new PreBattleFrame(squadra, (Sfida) match, false); 
					frameSfida.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frameSfida.setVisible(true);
				}
			}
		}
		
		button.addActionListener(new ActionNext());
		return button;
	}
	
	private Squadra squadra;
	private Torneo torneo;
	private Sfida sfida;
	private final int quotaTorneo=30, quotaSfida=10;
}
