package equipaggiamento;

import java.io.Serializable;

/**
 * Un' arma è un oggetto equipaggiabile in grado di fare danno o difendere da un danno in arrivo
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 * 
 */

public abstract class Arma implements Cloneable, Serializable{
	/**
	 * Istanzia un oggetto di tipo arma definendone determinate caratteristiche.
	 *
	 *Precondizioni: myPrezzo>0, myValore>0, myDurab>0
	 *
	 * @param myNome nome dell'arma
	 * @param myPrezzo	prezzo al negozio dell'arma
	 * @param myValore valore di atk o def dell'arma a seconda del tipo
	 * @param myDurab numero massimo di combattimenti in cui si può usare l'arma
	 */
	public Arma(String myNome, double myPrezzo, int myValore, int myDurab)
	{
		nome = myNome;
		prezzo = myPrezzo;
		valore = myValore;
		durab = myDurab;
	}
	
	//metodi di accesso
	/**
	 * Restituisce il nome dell'arma
	 * @return	restituisce la stringa contenente il nome dell'arma
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Restituisce il prezzo dell'arma
	 * @return il prezzo dell'arma come valore double
	 */
	public double getPrezzo() {
		return prezzo;
	}
	
	/**
	 * Restituisce il valore di atk o def dell'arma
	 * @return il valore di atk se l'arma è un'arma di attacco, il valore di def se l'arma è difensiva
	 */
	public int getValore() {
		return valore;
	}
	
	/**
	 * Restituisce la durabilità dell'arma
	 * @return il valore intero che rappresenta la durabilità dell'arma
	 */
	public int getDurabilita() {
		return durab;
	}
	
	//metodi modificatori
	/**
	 * Decrementa di uno la durabilità dell'arma
	 */
	public void setDurabilita() {
		durab--;
	}
	
	//Metodi classe Object
	
	/**
	 * Restituisce la stringa descrittiva dell'arma
	 * @return lo stato delle variabili d'istanza dell'arma sottoforma di stringa
	 */
	
	public String toString() 
	{
		return getClass().getName() + "[Nome=" + nome + ", Prezzo=" + prezzo + ", Valore=" + valore + ", DurabilitÃ =" + durab + "]";
	}
	
	/**
	 * Controlla se due oggetti hanno lo stesso stato
	 * 
	 * @return true se lo stato dei due oggetti è uguale, false altrimenti
	 */
	public boolean equals(Object o)
	{
		if(o==null) return false;
		if(getClass()!=o.getClass()) return false;
		Arma a = (Arma) o;
		return nome.equals(a.nome) && prezzo==a.prezzo && valore==a.valore && durab==a.durab;
	}
	
	/**
	 * Clona un'oggetto di tipo arma
	 */
	public Arma clone()
	{
		try {
			Arma o = (Arma) super.clone();
			return o;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}

	private String nome;
	private double prezzo;
	private int valore, durab;
}
