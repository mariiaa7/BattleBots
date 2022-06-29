package partita;
import java.util.ArrayList;

import exception.RejectedPaymentException;
import squadra.Squadra;

/**
 * Un torneo � un insieme di sfide a cui partecipano 8 squadre per contendersi il primo e il secondo posto.
 * Ogni squadra per unirsi deve pagare un quota, l'insieme delle quote pagate dalle squadre
 * rappresenta il premio assegnato al vincitore del torneo.
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class Torneo {
	/**
	 * Istanzia un nuovo torneo, scegliendo un nome per il torneo e una quota d'iscrizione da pagare
	 * @param myNome nome del torneo
	 * @param myQuota quota d'iscrizione
	 */
	public Torneo(String myNome, int myQuota) {
		nome = myNome;
		primoPosto = null;
		secondoPosto = null;
		quota = myQuota;
		squadre = new ArrayList<Squadra>();
		sfide = new ArrayList<Sfida>();
	}
	
	//metodi di accesso
	/**
	 * Restituisce il nome del torneo
	 * @return nome del torneo
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Pre-condizioni: 0 <= i < 8
	 * @param i indice della squadra che si vuole cercare fra quelle che partecipano
	 * @return la squadra all'indice i
	 */
	public Squadra getSquadra(int i) {
		return squadre.get(i).clone();
	}
	
	/**
	 * Restituisce la quota da pagare per iscriversi al torneo
	 * @return la quota del torneo
	 */
	public int getQuota() {
		return quota;
	}

	/**
	 * Precondizione: 0 <= i < 4
	 * @param i indice della sfida da ricercare
	 * @return la sfida del torneo all'indice i
	 */
	public Sfida getSfida(int i) {
		if(i<0 || i >= sfide.size())return null;
		return sfide.get(i);//.clone();
	}
	
	/**
	 * Restituisce la squadra vincitrice del torneo
	 * @return la squadra classificata al primo posto
	 */
	public String getPrimoPosto() {
		return primoPosto;
	}
	
	/**
	 * Restituisce la squadra classificata al secondo posto
	 * @return la squadra classificata al secondo posto
	 */
	public String getSecondoPosto() {
		return secondoPosto;
	}
	
	public int getVincitaPrimoPosto() {
		return (int)((quota*8)/3)*2;
	}
	
	public int getVincitaSecondoPosto() {
		return (int)(quota*8)/3;
	}
	
	//metodi modificatori
	/**
	 * Aggiunge una squadra al torneo dopo che quest'ultima ha pagato la quota
	 * @param sq squadra da iscrivere al torneo
	 */
	public void addSquadra(Squadra sq) {
		if(squadre.size()<maxSquadre)
			squadre.add(sq.clone());
	}
	
	/**
	 * Assegna i premi alla squadra vincitrice e alla squadra al secondo posto
	 * @param s squadra a cui assegnare il premio
	 */
	public void assegnaPremio(Squadra s) throws RejectedPaymentException {
		if(primoPosto.equals(s.getNome())) 
			s.setSaldo((int)((quota*8)/3)*2);
		else if(secondoPosto.equals(s.getNome()))
			s.setSaldo((int)(quota*8)/3);
	}
	
	/**
	 * Crea una nuova sfida per le vari fasi del torneo
	 */
	public void creaSfide() {
		for(int i=0; (i+1)<squadre.size(); i+=2) {
			sfide.add(new Sfida(squadre.get(i).getNome(), squadre.get(i+1).getNome(), 0));
		}
	}
	
	/**
	 * Controlla la squadra vincitrice e la perdente ad ogni fase del torneo, eliminando le squadre perdenti
	 * e facendo proseguire le squadre vincitrici, finch� non si avranno solo due squadre a sfidarsi
	 */
	public void girone() {
		if(squadre.size() == 2) {
			secondoPosto = sfide.get(0).getPerdente();
			primoPosto = sfide.get(0).getVincitrice();
			return;
		}
		
		int i, j;
		for(i=0; i<sfide.size(); i++) {
			String p = sfide.get(i).getPerdente();
			for(j=0; j<squadre.size(); j++) 
				if(p.equals(squadre.get(j).getNome())) {
					squadre.remove(j);
				}
		}
		
		sfide.removeAll(sfide);
	}
	
	public void randomVinc(int i) {
		for(; i<sfide.size(); i++) {
			sfide.get(i).randomVincitrice();
		}
	}
	
	public int squadreSize() {
		for(Sfida sf : sfide) {
			System.out.println(sf.getNome1() + " vs " + sf.getNome2());
		}
		System.out.println("\n");
		return squadre.size();
	}
	
	private String nome, primoPosto, secondoPosto;
	private int quota;
	private ArrayList<Squadra> squadre;
	private ArrayList<Sfida> sfide;
	private static int maxSquadre = 8;
}
