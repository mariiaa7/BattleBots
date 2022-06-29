package robot;
import java.util.ArrayList;
import equipaggiamento.Materiale;
/**
 * Un robot riparatore � un robot in grado di curare i danni di altri robot, utilizzando i materiali che
 * hanno equipaggiati.
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */
public class Riparatore extends Robot{
	/**
	 * Istanzia un nuovo robot riparatore assegnandogli un nome e una lista di materiali da riparazione
	 * @param myNome nome del robot riparatore
	 * @param myMat materiale assegnato al robot inizialmente
	 */
	public Riparatore(String myNome, int p, Materiale myMat)
	{
		super(myNome, p);
		materiaList = new ArrayList<Materiale>();
		materiaList.add(myMat);
	}
	
	//metodi di accesso
	/**
	 * Restituisce un materiale se quest'ultimo � equipaggiato ad un Robot riparatore, la ricerca viene
	 * effettuata attraverso il nome del materiale.
	 * @param myNome nome del materiale da cercare
	 * @return il materiale se equipaggiato, null altrimenti
	 */
	public Materiale getMateriale(String myNome) {
		for(Materiale m: materiaList) 
			if(m.getNome().equals(myNome))
				return m;
		return null;
	}
	
	//metodi modificatori
	/**
	 * Permette di assegnare un nuovo materiale ad un robot riparatore
	 * @param m materiale da equipaggiare
	 * @return true se viene equipaggiato, false altrimenti
	 */
	public boolean addMateriale(Materiale m) {
		if(materiaList.size()==maxMateriali) return false;
		int i=0;
		for(; i<materiaList.size(); i++) 
			if(materiaList.get(i).getCura() > m.getCura()) 
				break;
		materiaList.add(i, m);
		return true;
	}
	/**
	 * Pre-condizioni: curaRichiesta > 0.
	 * Consente ad un robot riparatore di ripararne un altro utilizzando dei materiali di riparazione,
	 * ogni materiale ha una capacit� curativa diversa, la riparazione viene effettuata confrontando i materiali
	 * disponibili al robot e la cura richiesta.
	 * @param curaRichiesta cura richiesta per riparare un altro robot
	 * @return	la quantit� di salute curata o 0 se non vi sono materiali utilizzabili.
	 */
	public int ripara(int curaRichiesta) {
		if(materiaList.size()==0)
			return 0;
		
		if(materiaList.size()==1){
			int cura = materiaList.get(0).getCura();
			materiaList.remove(0);
			return cura;
		}
		
		int i = 1;
		for(;i<materiaList.size();i++) 
			if(materiaList.get(i).getCura() > curaRichiesta) 				
				break;
		int deltaInf = curaRichiesta - materiaList.get(i-1).getCura();	
		int deltaSup = materiaList.get(i).getCura() -  curaRichiesta;
		
		Materiale m;
		if(deltaInf<deltaSup) 
			m = materiaList.get(i-1);
		else 
			m = materiaList.get(i);

		materiaList.remove(m);
		return m.getCura();
	}
	/**
	 * Calcola il danno subito da un robot dopo aver subito un attacco, considerando la statistica di 
	 * attacco del robot che effettua l'attacco e l'arma di difesa equipaggiata al robot che subisce l'attacco.
	 */
	public void subisce(int val) {
		super.setSalute(super.getSalute()-val);
	}
	/**
	 * Restituisce la stringa descrittiva dello stato delle variabili d'istanza
	 */
	public String toString() {
		return super.toString() + "[Lista materiali=" + materiaList + "]";
	}
	/**
	 * Clona un oggetto di tipo robot riparatore
	 */
	public Riparatore clone()
	{
		Riparatore r = (Riparatore) super.clone();
		materiaList.clone();
		return r;
	}
	
	private ArrayList<Materiale> materiaList;
	private static final int maxMateriali = 5;
}
