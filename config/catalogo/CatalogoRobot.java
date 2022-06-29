package catalogo;
import java.util.ArrayList;
import java.util.Random;
import robot.Combattente;
import robot.Riparatore;
import robot.Robot;

/**
 * Catalogo di tutti i robot acquistabili nel negozio e utilizzabili nelle sfide
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */
public class CatalogoRobot {
	/**
	 * Istanzia tutti i robot assegnandogli un nome, un tipo, un valore di attacco e un arma di attacco e difesa in modo casuale
	 * mentre per i riparatori sarà assegnato un materiale in modo casuale
	 */
	public CatalogoRobot() {
		catalogo = new ArrayList<Robot>();
		CatalogoArmi armi = new CatalogoArmi();
		catalogo.add(new Combattente("Charmander", 23, Tipo.FUOCO.ordinal(), 17, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Cyndaquil", 13, Tipo.FUOCO.ordinal(), 14, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Torchic", 24, Tipo.FUOCO.ordinal(), 11, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Squirtle", 9, Tipo.ACQUA.ordinal(), 21, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Totodile", 37, Tipo.ACQUA.ordinal(), 18, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Mudkip", 28, Tipo.ACQUA.ordinal(), 13, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Bulbasaur", 19, Tipo.ERBA.ordinal(), 9, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Chikorita", 16, Tipo.ERBA.ordinal(), 12, armi.getArmaAttacco(), armi.getArmaDifesa()));
		catalogo.add(new Combattente("Treecko", 9, Tipo.ERBA.ordinal(), 10, armi.getArmaAttacco(), armi.getArmaDifesa()));
		
		CatalogoMateriali materiali = new CatalogoMateriali();
		catalogo.add(new Riparatore("Pikachu", 23,  materiali.getMateriale()));
		catalogo.add(new Riparatore("Eevee", 13,  materiali.getMateriale()));
		catalogo.add(new Riparatore("Mew", 24,  materiali.getMateriale()));
		catalogo.add(new Riparatore("Snorlax", 13, materiali.getMateriale()));
		catalogo.add(new Riparatore("Dratini", 8, materiali.getMateriale()));
	}
	
	/**
	 * Restituisce in modo randomico un robot combattemte fra quelli disponibili nel catalogo
	 * @return un robot combattente casuale
	 */
	public Combattente getRobotCombattente() {
		Random r = new Random();
		do {
			int v = r.nextInt(catalogo.size()-1);
			if(catalogo.get(v) instanceof Combattente)
				return (Combattente) catalogo.get(v).clone();
		}while(true);
	}
	
	/**
	 * Restituisce un robot riparatore in modo randomico fra quelli disponibili nel catalogo
	 * @return un robot riparatore casuale
	 */
	public Riparatore getRobotRiparatore() {
		Random r = new Random();
		do {
			int v = r.nextInt(catalogo.size()-1);
			if(catalogo.get(v) instanceof Riparatore)
				return (Riparatore) catalogo.get(v).clone();
		}while(true);
	}
	
	/**
	 * Restituisce un robot in base al suo nome
	 * @param nome del robot interessato
	 * @return il robot avente il nome inserito
	 */
	public Robot getRobot(String nome) {
		for(Robot r : catalogo)
			if(r.getNome().equals(nome))
				return r;
		return null;
	}
	
	/**
	 * Restituisce i tipi possibili dei robot
	 * @param posizione del tipo
	 * @return tipo
	 */
	public static String getTipo(int i) {
		switch(i) {
			case 0: return "Fuoco";
			case 1: return "Acqua";
			case 2: return "Erba";
			default: return "Error";
		}
	}
	
	public ArrayList<Robot> getCatalogo()
	{
		return catalogo;
	}
	
	public enum Tipo {FUOCO, ACQUA, ERBA};
	private ArrayList<Robot> catalogo;
}