package exception;

//controllata

/**
* Eccezione controllata che viene lanciata quando il conto del giocatore è inferiore all'importo dovuto. 
* @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
*
*/

public class RejectedPaymentException extends Exception{
	/**
	 * Istanzia l'eccezione lanciando il relativo messaggio di errore
	 */
	
	public RejectedPaymentException() {
		super("pagamento rifiutato");
	}
	
	private static final long serialVersionUID = 1L;
}
