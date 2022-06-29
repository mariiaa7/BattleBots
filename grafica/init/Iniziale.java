package init;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import squadra.Squadra;
import utility.Dat;


public class Iniziale extends JFrame{
	public Iniziale() {
		BackgroundComponent bkc = new BackgroundComponent();
		setSize(600, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Welcome to BattleBots!");
		setJMenuBar(createJMenuBar());	
		add(bkc);
		
	}
	
	private JMenuBar createJMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.add(createMenu());
		return bar;
	}
	
	private JMenu createMenu() {
		JMenu menu = new JMenu("File");
		menu.add(menuItemNuovaPartita());
		menu.add(menuItemCaricaPartita());
		return menu;
	}
	
	private JMenuItem menuItemNuovaPartita() {
		JMenuItem nuova = new JMenuItem("New Game");
		
		class ActionListenerNuovaPartita implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame nuovaPartita = new NuovaPartita();
				nuovaPartita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				nuovaPartita.setVisible(true);
			}
		}
		
		nuova.addActionListener(new ActionListenerNuovaPartita());
		return nuova;
	}
	
	private JMenuItem menuItemCaricaPartita() {
		JMenuItem carica = new JMenuItem("Load Game");
		
		class ActionListenerCaricaPartita implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				File f = new File("partita.dat");
				Dat d = new Dat(f.getName());
				Squadra s = d.leggi();
				JFrame fr = new RobotCasuali(s);
				fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
				fr.setVisible(true);
				dispose();
				
			}

		}
		
		carica.addActionListener(new ActionListenerCaricaPartita());
		return carica;
	}
	
	public class BackgroundComponent extends JComponent {
		public BackgroundComponent() {
			backgroundImage = new ImageIcon("background.JPG");
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(backgroundImage.getImage(),0,0,600,460,null);
			
		}

		private static final long serialVersionUID = 1L;
	}
	
	private ImageIcon backgroundImage;
	private static final long serialVersionUID = 1L;
}
