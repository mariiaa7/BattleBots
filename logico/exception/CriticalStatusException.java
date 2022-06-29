package exception;

//Eccezione non controllata

/**
 * Eccezione non controllata che viene lanciata quando un robot � danneggiato per almeno il 75%
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class CriticalStatusException extends RuntimeException{
	/**
	 * Istanzia l'eccezione lanciando il relativo messaggio di errore
	 */
	public CriticalStatusException() {
		super("Robot danneggiato oltre il 75%\n");
	}
	
	private static final long serialVersionUID = 1L;
}
