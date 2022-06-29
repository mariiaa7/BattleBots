package catalogo;
import java.util.ArrayList;
import java.util.Random;

import squadra.Squadra;

/**
 * Catalogo di tutte le squadre presenti in gioco
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class CatalogoSquadre {
	/**
	 * Crea tutte le squadre presenti nel gioco assegnandogli un nome e un ranking.
	 */
	public CatalogoSquadre() {
		catalogo = new ArrayList<Squadra>();
		catalogo.add(new Squadra("Maria"));
		catalogo.add(new Squadra("Carlo"));
		catalogo.add(new Squadra("Francesco"));
		catalogo.add(new Squadra("Michele"));
		catalogo.add(new Squadra("Demon Slayer"));
		catalogo.add(new Squadra("Gennara"));
		catalogo.add(new Squadra("Torz"));
		catalogo.add(new Squadra("Franco"));
		
		catalogo.get(0).setRanking(6);
		catalogo.get(1).setRanking(14);
		catalogo.get(2).setRanking(20);
		catalogo.get(3).setRanking(28);
		catalogo.get(4).setRanking(36);
		catalogo.get(5).setRanking(49);
		catalogo.get(6).setRanking(61);
		catalogo.get(7).setRanking(70);
		
	}
	
	/**
	 * Restituisce in modo casuale una squadra fra quelle presenti in gioco
	 * @return una squadra casuale
	 */
	public Squadra getSquadra() {
		Random r = new Random();
		Squadra s = catalogo.get(r.nextInt(7));
		setRobots(s.clone());
		return s;
	}
	
	/**
	 * Restituisce una squadra in base al suo indice nell'array list
	 * @param i indice della squadra da ricercare
	 * @return la squadra all'indice i
	 */
	public Squadra getSquadra(int i) {
		if(i<0 || i>(maxSquadre-1)) return null;
		return catalogo.get(i);
	}
	
	/**
	 * Restituisce una squadra in base al suo indice nell'array list
	 * @param squadra da ricercare
	 * @return squadra
	 */
	public Squadra getSquadra(Squadra s) {
		for(int i=0; i<catalogo.size(); i++)
			if(s.getRanking()<catalogo.get(i).getRanking()) {
				setRobots(catalogo.get(i).clone());
				return catalogo.get(i);
			}
		setRobots(catalogo.get(catalogo.size()-1));
		return catalogo.get(catalogo.size()-1);
	}
	
	/**
	 * @param  squadra a cui aggiungere robot
	 * @return la squadra con robot aggiunti
	 */	
	private void setRobots(Squadra s) {
		CatalogoRobot cR = new CatalogoRobot();
		s.addRobot(cR.getRobotCombattente());
		s.addRobot(cR.getRobotCombattente());
		s.addRobot(cR.getRobotCombattente());
		s.addRobot(cR.getRobotRiparatore());
		s.addRobot(cR.getRobotRiparatore());
	}
	
	public static final int maxSquadre = 8;
	private ArrayList<Squadra> catalogo;
}
