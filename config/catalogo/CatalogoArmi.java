package catalogo;
import java.util.ArrayList;
import java.util.Random;

import equipaggiamento.Arma;
import equipaggiamento.Attacco;
import equipaggiamento.Difesa;

/**
 * Catalogo di tutte le armi di attacco e difesa acquistabili nel negozio e equipaggiabili ai robot
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class CatalogoArmi {
	/**
	 * Istanzia tutte le armi acquistabili assegnandogli un nome, un prezzo, un valore di attacco o difesa
	 * e una durabilità
	 */
	public CatalogoArmi() {
		
		//String myNome, double myPrezzo, int myValore, int myDurab
		
		catalogo = new ArrayList<Arma>();
		catalogo.add(new Attacco("Wooden Sword",23, 29, 18));
		catalogo.add(new Attacco("Iron Sword",36, 11, 14));
		catalogo.add(new Attacco("Golden Sword", 42, 18, 11));
		catalogo.add(new Attacco("Diamond Sword", 54, 42, 8));
		catalogo.add(new Attacco("Netherite Sword", 71, 53, 5));
		catalogo.add(new Difesa("Wooden Shield", 24, 30, 17));
		catalogo.add(new Difesa("Iron Shield", 34, 12, 13));
		catalogo.add(new Difesa("Golden Shield", 41, 17, 10));
		catalogo.add(new Difesa("Diamond Shield", 69, 51, 7));
		catalogo.add(new Difesa("Netherite Shield", 70, 52, 6));
	}
	
	/**
	 * Restituisce randomicamente un'arma di attacco
	 * @return un'arma di attacco casuale fra quelle disponibili nel catalogo
	 */
	public Attacco getArmaAttacco() {
		Random r = new Random();
		do {
			int v = r.nextInt(9);
			if(catalogo.get(v) instanceof Attacco)
				return (Attacco) catalogo.get(v).clone();
		}while(true);
	}
	
	/**
	 * Restituisce randomicamente un'arma di difesa
	 * @return un'arma di difesa casuale fra quelle disponibili nel catalogo
	 */
	public Difesa getArmaDifesa() {
		Random r = new Random();
		do {
			int v = r.nextInt(9);
			if(catalogo.get(v) instanceof Difesa)
				return (Difesa) catalogo.get(v).clone();
		}while(true);
	}
	
	/**
	 * Ricerca nel catalogo delle armi, un'arma specifica in base al suo prezzo
	 * @param prezzo il prezzo dell'arma che si vuole cercare
	 * @return l'arma del catalogo avente prezzo uguale a quello inserito
	 */
	public Arma getArma(int prezzo) {
		for(Arma a : catalogo)
			if(prezzo == a.getPrezzo())
				return a.clone();
		return null;
	}
	
	public ArrayList<Arma> getCatalogo()
	{
		return catalogo;
	}
	
	private ArrayList<Arma> catalogo;
}
