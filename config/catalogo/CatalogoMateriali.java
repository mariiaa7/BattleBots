package catalogo;
import java.util.ArrayList;
import java.util.Random;
import equipaggiamento.Materiale;

/**
 * Catalogo di tutti i materiali di riparazione acquistabili in negozio ed equipaggiabili ai robot riparatori
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 */

public class CatalogoMateriali {
	/**
	 * Istanzia tutti i materiali disponibili assegnandogli un nome, un prezzo e la quantità di salute curata
	 */
	public CatalogoMateriali() {
		catalogo = new ArrayList<Materiale>();
		catalogo.add(new Materiale("Potion", 7, 15));
		catalogo.add(new Materiale("SuperPotion", 14, 24));
		catalogo.add(new Materiale("IperPotion", 26, 47));
		catalogo.add(new Materiale("Revive", 44, 69));
		catalogo.add(new Materiale("Max Revive", 79, 90));
	}
	
	/**
	 * Restituisce randomicamente un materiale fra quelli disponibili
	 * @return un materiale in modo casuale fra quelli del catalogo
	 */
	public Materiale getMateriale() {
		Random r = new Random();
		return catalogo.get(r.nextInt(4));
	}
	
	/**
	 * Ricerca un materiale nel catalogo in base al prezzo
	 * @param prezzo del materiale da ricercare
	 * @return il materiale avente il prezzo inserito
	 */
	public Materiale getMateriale(int prezzo) {
		for(Materiale m : catalogo)
			if(prezzo == m.getPrezzo())
				return m;
		return null;
	}
	
	
	public ArrayList<Materiale> getCatalogo()
	{
		return catalogo;
	}
	
	private ArrayList<Materiale> catalogo;
}
