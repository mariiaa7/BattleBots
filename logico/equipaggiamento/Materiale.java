package equipaggiamento;

import java.io.Serializable;

/**
 * Materiale di riparazione consente di curare la salute di un robot 
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class Materiale implements Cloneable, Serializable{
	
	/**
	 * Istanzia un oggetto di tipo materiale
	 * 
	 * Precondizioni: myPrezzo>0, myCura>0
	 * 
	 * @param myNome nome del materiale
	 * @param myPrezzo prezzo al negozio del materiale
	 * @param myCura capacita riparativa del materiale
	 */
	
	public Materiale(String myNome, double myPrezzo, int myCura)
	{
		nome = myNome;
		prezzo = myPrezzo;
		cura = myCura;
	}
	
	//metodi di accesso
	
	/**
	 * Ritorna il nome del materiale
	 * @return restituisce la stringa contenente il nome del materiale
	 */
	public String getNome(){
		return nome;
	}
	
	/**
	 * Restituisce il prezzo del materiale
	 * @return il prezzo del materiale come valore double
	 */
	
	public double getPrezzo() {
		return prezzo;
	}
	
	/**
	 * Restituisce la cura del materiale 
	 * @return capacita curativa del materiale
	 */
	
	public int getCura() {
		return cura;
	}
	
	//Metodi classe Object
	
	/**
	 * Restituisce la stringa descrittiva del materiale
	 * @return lo stato delle variabili d'istanza del materiale sottoforma di stringa
	 */
	
	public String toString() 
	{
		return getClass().getName() + "[Nome=" + nome + ", Prezzo=" + prezzo + ", Cura=" + cura + "]";
	}
	
	/**
	 * Controlla se due oggetti hanno lo stesso stato
	 * @return true se lo stato dei due oggetti � uguale, false altrimenti
	 */

	public boolean equals(Object o)
	{
		if(o==null) return false;
		if(getClass()!=o.getClass()) return false;
		Materiale m = (Materiale) o;
		return nome.equals(m.nome) && prezzo==m.prezzo && cura==m.cura;
	}
	
	/**
	 * Clona un'oggetto di tipo arma
	 */
	
	public Materiale clone()
	{
		try {
			Materiale o = (Materiale) super.clone();
			return o;
		}catch(CloneNotSupportedException e) {
			return null;
		}
	}

	private String nome;
	private double prezzo;
	private int cura;
}