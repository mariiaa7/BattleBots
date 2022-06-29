package equipaggiamento;

/**
 * Estende la classe Arma e rappresenta un'arma di difesa in grado di proteggere dai danni
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class Difesa extends Arma{
	
	/**
	 * Istanzia un oggetto di tipo arma di difesa
	 * 
	 * Precondizioni: myPrezzo>0, myValore>0, myDurab>0
	 * 
	 * @param myNome nome dell'arma di difesa
	 * @param myPrezzo prezzo dell'arma di difesa al negozio
	 * @param myValore valore che rappresenta la capacit� di un'arma di proteggere dai danni
	 * @param myDurab numero massimo di combattimenti in cui si pu� usare l'arma
	 */
	
	public Difesa(String myNome, double myPrezzo, int myValore, int myDurab)
	{
		super(myNome, myPrezzo, myValore, myDurab);
	}
	
	//Metodi classe Object
	
	/**
	 * Restituisce la stringa descrittiva dell'arma di difesa
	 * @return lo stato delle variabili d'istanza dell'arma di difesa sottoforma di stringa
	 */
	
	public String toString() 
	{
		return super.toString();
	}
	
	/**
	 * Controlla se due oggetti hanno lo stesso stato
	 * @return true se lo stato dei due oggetti � uguale, false altrimenti
	 */
	
	public boolean equals(Object o)
	{
		if(!super.equals(o)) return false;
		return true;
	}
	
	/**
	 * Clona un'oggetto di tipo arma di difesa
	 */
	
	public Difesa clone()
	{
		Difesa o = (Difesa) super.clone();
		return o;
	}
	
}