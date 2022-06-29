package robot;
import java.io.Serializable;

import exception.CriticalStatusException;
import exception.LowEnergyException;
/**
 * Un robot � un'entit� utilizzata nei combattimenti tra squadre, ve ne sono di due tipi: Combattenti o Riparatori. I primi in grado di infliggere danni e i secondi in grado di riparare altri robot dai propri danni
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public abstract class Robot implements Cloneable, Serializable{
	/**
	 * Istanzia un oggetto di tipo robot assegnandogli un nome
	 * @param s nome identificativo del robot
	 */
	
	public Robot(String s, int p)
	{
		salute = 100;
		energia = 100;
		nome = s;
		prezzo = p;
	}
	
	//metodi di accesso
	/**
	 * Restituisce la quantit� di salute del robot
	 * @return salute del robot
	 */
	
	public int getSalute() {
		return salute;
	}
	/**
	 * Restituisce la quantit� di energia del robot
	 * @return energia del robot
	 */
	
	public int getEnergia() {
		return energia;
	}
	/**
	 * Restituisce la stringa contenente il nome del robot
	 * @return nome del robot
	 */
	public String getNome() {
		return nome;
	}
	
	public int getPrezzo()
	{
		return prezzo;
	}
	
	//metodi modificatori
	/**
	 * Pre-condizioni: mySalute >= 0.
	 * Modifica la quantit� di salute di un robot se questo viene curato o subisce danni
	 * 
	 * @param mySalute nuova quantit� di salute del robot
	 */
	
	public void setSalute(int mySalute) {
		if(salute<40)
			try {
				throw new CriticalStatusException();
			}catch(CriticalStatusException e)
			{
				System.out.print(e.getMessage());
			}
		if(mySalute>100)mySalute = 100;
		salute = mySalute;
	}
	/**
	 * Pre-condizioni: myEnergia >= 0.
	 * Modifica la quantit� di salute rimanente al robot quando esso esegue un azione o � finito il combattimento
	 * @param myEnergia quantit� di energia da assegnare al robot
	 */
	public void setEnergia(int myEnergia) {
		if(energia<40)
			try {
				throw new LowEnergyException();
			}catch(LowEnergyException e)
			{
				System.out.print(e.getMessage());
			}
		energia = myEnergia;
	}
	/**
	 * Modifica il nome di un robot
	 * @param myNome nuovo nome da dare al robot
	 */
	public void setNome(String myNome) {
		nome = myNome;
	}
	/**
	 * Pre-condizioni: val >= 0.
	 * Calcola il danno subito da un robot a seguito di un attacco ricevuto da parte di un altro robot
	 * @param val valore del danno subito
	 */
	public abstract void subisce(int val);
	/**
	 * Restituisce la stringa descrittiva dello stato delle variabili d'istanza del robot
	 */
	public String toString() {
		return getClass().getName() + "[Nome=" + nome + ", salute=" + salute + ", energia=" + energia + ", prezzo=" + prezzo + "]"; 
	}
	/**
	 * Clona un oggetto di tipo robot
	 */
	public Robot clone()
	{
		try {
			Robot r = (Robot) super.clone();
			return r;
		}catch(CloneNotSupportedException e) {
			return null;
		}
	}
	
	private int salute, energia, prezzo; //entrambe tra 0 e 100
	private String nome;
}
