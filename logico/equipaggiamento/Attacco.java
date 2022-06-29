package equipaggiamento;

/**
 * Estende la classe Arma e rappresenta un'arma di attacco in grado di infliggere danni
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */
public class Attacco extends Arma{
	
	/**
	 * Istanza un oggetto di tipo Arma di Attacco
	 * 
	 * Precondizioni: myPrezzo>0, myValore>0, myDurab>0
	 * 
	 * @param myNome nome dell'arma di attacco
	 * @param myPrezzo prezzo al negozio dell'arma di attacco
	 * @param myValore	valore che rappresenta la capacità di infliggere danni di un'arma
	 * @param myDurab numero massimo di combattimenti in cui si può usare l'arma
	 */
	public Attacco(String myNome, double myPrezzo, int myValore, int myDurab)
	{
		super(myNome, myPrezzo, myValore, myDurab);
	}
	
	//Metodi classe Object
	/**
	 * Restituisce la stringa descrittiva dell'arma di attacco
	 * @return lo stato delle variabili d'istanza dell'arma di attacco sottoforma di stringa
	 */
	public String toString() 
	{
		return super.toString();
	}
	
	/**
	 * Controlla se due oggetti hanno lo stesso stato
	 * @return true se lo stato dei due oggetti è uguale, false altrimenti
	 */
	public boolean equals(Object o)
	{
		if(!super.equals(o)) return false;
		return true;
	}
	
	/**
	 * Clona un'oggetto di tipo arma di attacco
	 */
	public Attacco clone()
	{
		Attacco o = (Attacco) super.clone();
		return o;
	}
	
}
