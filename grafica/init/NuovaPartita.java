package init;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import catalogo.CatalogoRobot;
import squadra.Squadra;
import java.awt.Dimension;
import java.awt.GridLayout;

public class NuovaPartita extends JFrame{
	public NuovaPartita() {
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Insert your team's name");
		add(createPanel());
	}
	
	private JPanel createPanel() {
		mPanel = new JPanel();
		mPanel.setLayout(new GridLayout(4, 1));
		//CLASSE ANONIMA
		mPanel.add(createIPanel());
		mPanel.add(createJPanel("Balance:   200$ "));
		mPanel.add(createJPanel("Ranking:   0"));
		mPanel.add(createButton());
		
		return mPanel;
	}

	private JLabel createJLabel(String str) {
		JLabel label = new JLabel(str);
		
		return label; 
	}
	
	private JTextField createInput() {
		field = new JTextField(15); 
		
		return field;
	}
	
	private JPanel createIPanel() {
		JPanel panel = new JPanel();
		panel.add(createJLabel("Name"));
		panel.add(createInput());
		
		return panel;
	}
	
	private JPanel createJPanel(String str) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(str));
		
		return panel;
	}
	
	private JButton createButton() {
		JButton button = new JButton("Start");
		button.setPreferredSize(new Dimension(5,10));
		class ButtonActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				dispose();
				Squadra mySquadra = new Squadra(field.getText());
				CatalogoRobot cr = new CatalogoRobot();
				for(int i=0; i<3; i++)
					mySquadra.addRobot(cr.getRobotCombattente());
				for(int i=0; i<2; i++)
					mySquadra.addRobot(cr.getRobotRiparatore());
				JFrame robotCasuali = new RobotCasuali(mySquadra);
				robotCasuali.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				robotCasuali.setVisible(true);
			}
			
		}
		button.addActionListener(new ButtonActionListener());
		return button;
	}
	
	private JPanel mPanel;
	private JTextField field;
	private static final long serialVersionUID = 1L;
}
