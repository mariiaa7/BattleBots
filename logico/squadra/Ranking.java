package squadra;

import java.io.Serializable;
/**
 * Classe che calcola in termini di punteggio la media di partite vinte sul totale delle partite disputate di una squadra.
 * Serve a classificare una squadra in base alla sua forza.
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 */

public class Ranking implements Cloneable, Serializable{
	/**
	 * Istanzia un nuovo ranking inizializzando a 0 tutte le varabili d'istanza
	 */
	public Ranking() {
		valore = 0;
		vittorie = 0;
		giocate = 0;
	}
	
	//metodi di accesso
	/**
	 * Restituisce il valore del ranking di una squadra
	 * @return il ranking di una squadra
	 */
	public int getValore() {
		return valore;
	}
	/**
	 * Restituisce il numero di vittorie ottenute da una squadra
	 * @return numero di vittorie di una squadra
	 */
	public int getVittorie() {
		return vittorie;
	}
	/**
	 * Restituisce il numero totale di partite giocate da una squadra
	 * @return numero di partite giocare di una squadra
	 */
	public int getGiocate() {
		return giocate;
	}
	
	//metodi modificatori
	/**
	 * Modifica il valore di ranking di una squadra a seguito di una vittoria o di un sconfitta
	 * @param myValore numero di punti da assegnare/rimuovere alla squadra
	 */
	public void setValore(int myValore) {
		if(valore==0 && myValore<0) return;
		valore += myValore;
	}

	/**
	 * Incrementa il numero di vittorie ottenute da una squadra
	 */
	private void setVittorie() {
		vittorie++;
	}
	/**
	 * Incrementa il numero delle partite disputate da una squadra, modificando il ranking
	 * in base al risultato ottenuto dalla squadra
	 * @param statoPartita true = partita vinta e false = partita persa
	 */
	public void setGiocate(boolean statoPartita) {
		giocate++;
		if(statoPartita) {
			setVittorie();
			setValore(10);
		}
		if(valore>5)
			setValore(-5);	
		else {
			if(valore!=0)
				valore=0;
		}
	}
	/**
	 * Restituisce la stringa descrittiva dello stato delle variabili di istanza del ranking di una squadra
	 */
	public String toString() {
		return getClass().getName() + "[valore=" + valore + ", vittorie=" + vittorie + ", giocate=" + giocate + "]";
	}
	
	public Ranking clone()
	{
		try {
			Ranking r = (Ranking) super.clone();
			return r;
		}catch(CloneNotSupportedException e) {
			return null;
		}
	}
	
	private int valore, vittorie, giocate;
}
