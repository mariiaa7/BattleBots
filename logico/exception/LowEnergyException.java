package exception;

//controllata

/**
 * Eccezione controllata che viene lanciata quando la saluta del robot � inferiore al 40% 
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class LowEnergyException extends Exception{
	/**
	 * Istanzia l'eccezione lanciando il relativo messaggio di errore
	 */
	public LowEnergyException() {
		super("Livello di energia inferiore al 40%\n");
	}
	
	private static final long serialVersionUID = 1L;
}