package squadra;
import java.io.Serializable;
import java.util.ArrayList;

import equipaggiamento.Arma;
import equipaggiamento.Attacco;
import equipaggiamento.Difesa;
import equipaggiamento.Materiale;
import exception.RejectedPaymentException;
import robot.Combattente;
import robot.Riparatore;
import robot.Robot;

/**
 * Insieme di robot gestiti da un utente, ogni squadra ha un nome, dei soldi che pu� utilizzare per comprare
 * armi, materiali e partecipare a sfide o tornei. Inoltre ogni squadra ha un ranking, che rappresenta in termini
 * di punteggio la media statistica delle sue vittorie e sconfitte.
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class Squadra implements Cloneable, Serializable{
	/**
	 * Istanzia una nuova squadra assegnandole un nome, casualmente dei robot,casualmente delle armi e dei materiali
	 * e un saldo iniziale di 200
	 * @param myNome nome della squadra
	 */
	public Squadra(String myNome)
	{
		team = new ArrayList<Robot>();
		inventario = new ArrayList<Arma>();
		materiali = new ArrayList<Materiale>();
		nome = myNome;
		saldo = 200;
		ranking = new Ranking();
	}
	
	//metodi di accesso
	/**
	 * Cerca un'arma nell'inventario della squadra
	 * @param myArma arma da ricercare
	 * @return	l'arma se presente, null altrimenti
	 */
	private Arma getArma(Arma myArma) {
		for(Arma a : inventario)
			if(a.equals(myArma)) {
				inventario.remove(a);
				return a.clone();
			}
		return null;	
	}
	
	public ArrayList<Arma> getArmi()
	{
		return inventario;
	}
	
	public ArrayList<Materiale> getMateriali()
	{
		return materiali;
	}
	
	public ArrayList<Robot> getRobots()
	{
		return team;
	}
	/**
	 * Cerca un materiale nell'inventario dei materiali della squadra
	 * @param myMat	materiale da ricercare
	 * @return il materiale se presente, null altrimenti
	 */
	private Materiale getMateriale(Materiale myMat) {
		for(Materiale m : materiali)
			if(m.equals(myMat)) {
				materiali.remove(myMat);
				return m.clone();
			}
		return null;
	}
	
	public Materiale getMateriale(int i) {
		if(i<0 || i>=materiali.size())
			return null;
		return materiali.get(i);
	}
	/**
	 * Restituisce il nome della squadra
	 * @return nome della squadra
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Restituisce il saldo della squadra
	 * @return saldo della squadra
	 */
	public int getSaldo() {
		return saldo;
	}
	/**
	 * Restituisce il ranking della squadra
	 * @return ranking della squadra
	 */
	public int getRanking() {
		return ranking.getValore();
	}
	
	public Ranking getRankingObj() {
		return ranking;
	}
	
	public int getRobotSize()
	{
		return team.size();
	}
	
	public int getMaterialeSize()
	{
		return materiali.size();
	}
	/**
	 * Cerca un robot fra tutti i robot presenti in squadra
	 * @param rNome nome del robot da cercare
	 * @return il Robot se presente, null altrimenti
	 */
	public Robot getRobot(String rNome){
		for(Robot r : team)
			if(r.getNome().equals(rNome))
				return r.clone();
		return null;
	}
	
	public Robot getRobot(int i) {
		if(i<0 || i>=team.size())
			return null;
		return team.get(i);
	}
	/**
	 * Cerca un arma tramite il suo nome nell'inventario della squadra
	 * @param aNome	nome dell'arma da cercare
	 * @return l'arma se presente, false altrimenti
	 */
	public Arma getArma(String aNome){
		for(Arma a : inventario)
			if(a.getNome().equals(aNome))
				return a.clone();
		return null;
	}
	/**
	 * Cerca un materiale tramite il suo nome nell'inventario della squadra
	 * @param myNome nome del materiale da ricercare
	 * @return il materiale se presente, false altrimenti
	 */
	public Materiale getMateriale(String myNome) {
		for(Materiale m : materiali) 
			if(m.getNome().equals(myNome))
				return m.clone();
		return null;
	}
	

	//metodi modificatori
	/**
	 * Aggiunge o rimuove una parte di saldo attuale
	 * @param mySaldo quantit� da aggiungere/rimuovere dal saldo attuale
	 */
	public void setSaldo(int mySaldo) throws RejectedPaymentException {
		if(-mySaldo > saldo)
			throw new RejectedPaymentException();
		else
			saldo += mySaldo;
	}
	/**
	 * Modifica il ranking di una squadra a seguito di una vittoria o sconfitta
	 * @param stato true se la partita � vinta, false altrimenti
	 */
	public void setRanking(boolean stato) {
		ranking.setGiocate(stato);
	}
	/**
	 * Modifica il ranking di una squadra a seguito di una vittoria o sconfitta
	 * @param val numero di punti ranking da rimuovere o sommare
	 */
	public void setRanking(int val) {
		ranking.setValore(val);
	}
	/**
	 * Permette di aggiungere un'arma all'inventario della squadra se quest'ultimo non supera
	 * la sua capienza massima
	 * @param myArma arma da aggiungere
	 */
	public void addArma(Arma myArma) {
		if(inventario.size()==maxInventario) return;
		inventario.add(myArma);
	}
	/**
	 * Permette di aggiungere un nuovo robot al team di robot, se la squadra non � al completo
	 * @param myRobot robot da aggiungere
	 */
	public void addRobot(Robot myRobot) {
		if(team.size()>=maxTeam) return;
		team.add(myRobot);
	}
	/**
	 * Pre-condizioni: myMat != null.
	 * Permette di aggiungere un nuovo materiale nell'inventario dei materiali
	 * @param myMat materiale da aggiungere
	 */
	public void addMateriali(Materiale myMat) {
		if(materiali.size()==maxMateriali) return;
		materiali.add(myMat.clone());
	}
	/**
	 * Pre-condizioni: quota > 0.
	 * Consente ad una squadra di pagare una quota di partecipazione per un torneo o una sfida
	 * @param quota somma da pagare
	 * @return true se la quota � stata pagata correttamente, false altrimenti
	 */
	public boolean pagaQuota(int quota){
		if(saldo<quota) return false;
		saldo-=quota;
		return true;
	}
	/**
	 * Pre-condizioni: myArma != null e myNome deve essere il nome di un robot presente in squadra
	 * Permette di equipaggiare un robot della squadra con un'arma
	 * @param myArma arma da equipaggiare
	 * @param myNome nome del robot a cui equipaggiare l'arma
	 */
	public void assegnaArma(Arma myArma, String myNome) {
		if(getArma(myArma)==null) return;
		for(int i=0; i<team.size(); i++) {
			if(!team.get(i).getNome().equals(myNome)) continue;
			if(team.get(i) instanceof Combattente) {
				Combattente c = (Combattente) team.get(i);
				if(myArma instanceof Attacco) {
					if(c.getAttacco()!=null) 
						inventario.add(c.getAttacco());
					c.setAttacco((Attacco)myArma);
				}else {
					if(c.getDifesa()!=null)
						inventario.add(c.getDifesa());
					c.setDifesa((Difesa)myArma);
				}
			}
		}
	}
	/**
	 * Pre-condizioni: myMat != null e myNome deve essere il nome di un robot presente nel team
	 * Permette di assegnare materiali ad un robot riparatore
	 * @param myMat materiale da assegnare
	 * @param myNome nome del robot a cui assegnarlo
	 */
	public void assegnaMateriale(Materiale myMat, String myNome) {
		if(getMateriale(myMat)==null) return;
		for(int i=0; i<team.size(); i++) {
			if(!team.get(i).getNome().equals(myNome)) continue;
			if(team.get(i) instanceof Riparatore) {
				Riparatore r = (Riparatore) team.get(i);
				r.addMateriale(myMat);
			}
		}
	}
	/**
	 * Restituisce la stringa descrittiva delle variabili d'istanza della squadra
	 */
	public String toString() {
		return getClass().getName() + "[team=" + team + ", inventario=" + inventario + ", materiali=" + materiali + ", nome=" + nome + ", saldo=" + saldo + ", ranking=" + ranking.toString() + "]";
	}
	/**
	 * Clona un oggetto di tipo squadra
	 */
	public Squadra clone() {
		try {
			Squadra o = (Squadra) super.clone();
			team.clone();
			inventario.clone();
			materiali.clone();
			o.ranking = ranking.clone();
			return o;
		}catch(CloneNotSupportedException e) {
			return null;
		}
	}
	
	private ArrayList<Robot> team;
	private ArrayList<Arma> inventario;
	private ArrayList<Materiale> materiali; 
	private String nome;
	private int saldo;
	private Ranking ranking;
	private static final int maxInventario = 50;
	private static final int maxTeam = 10;
	private static final int maxMateriali = 15;
}
