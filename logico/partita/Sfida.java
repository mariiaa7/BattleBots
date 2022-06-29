package partita;
import java.util.ArrayList;
import java.util.Random;
import robot.Combattente;
import robot.Riparatore;
import robot.Robot;
/**
 * Una sfida � uno scontro fra robot di squadre avversarie, per poter partecipare ad una sfida � necessario
 * pagare una quota d'iscrizione. Ogni sfida � composta da pi� round, la sfida � vinta dalla prima squadra
 * che sconfigge tutti i robot avversari.
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */

public class Sfida implements Cloneable{
	/**
	 * Istanzia un nuovo oggetto sfida, assegnando alla sfida le due squadre che si sfideranno,
	 * i rispettivi team di robot e lo scenario di gioco in cui si sfideranno.
	 * @param nome1 nome della prima squadra in sfida
	 * @param nome2 nome della seconda squadra in sfida
	 */
	public Sfida(String nome1, String nome2, int myQuota) {
		numRound = 0;
		sq1 = nome1;
		sq2 = nome2;
		quota = myQuota;
		robot1 = new ArrayList<Robot>();
		robot2 = new ArrayList<Robot>();
		Random r = new Random();
		tipoScenario = r.nextInt(2);
		vincitrice = null;
	}
	

	public int getQuota() {
		return quota;
	}
	

	public int getVincita() {
		float i = (float)quota / 100;
		i = i*20;
		return quota * 2 + (int)i;
	}
	
	//metodi di accesso
	/**
	 * Restituisce il nome della prima squadra in sfida
	 * @return il nome della prima squadra in sfida
	 */
	public String getNome1() {
		return sq1;
	}
	
	/**
	 * Restituisce il nome della seconda squadra in sfida
	 * @return il nome della seconda squadra in sfida
	 */
	public String getNome2() {
		return sq2;
	}
	
	/**
	 * Restituisce lo scenario su cui le due squadre si sfideranno
	 * @return lo scenario della sfida
	 */
	public int getScenario() {
		return tipoScenario;
	}
	
	/**
	 * Restituisce il numero di round giocati per una sfida
	 * @return il numero di round giocati
	 */
	public int getRound() {
		return numRound;
	}
	
	/**
	 * Precondizioni: 0 < i <= 5
	 * @param i indice del robot che si vuole selezionare dalla prima squadra
	 * @return il robot in posizione i della prima squadra
	 */
	public Robot getRobot1(int i) {
		if(i<0 || i>=robot1.size())
			return null;
		return robot1.get(i);//.clone();      
	}
	
	/**
	 * Precondizioni: 0 < i <= 5
	 * @param i indice del robot che si vuole selezionare dalla seconda squadra
	 * @return il robot in posizione i della seconda squadra
	 */
	public Robot getRobot2(int i) {
		if(i<0 || i>=robot2.size())
			return null;
		return robot2.get(i).clone();
	}
	
	/**
	 * Restituisce la costante maxRobot che indica il numero massimo di robot
	 * che ogni squadra pu� portare in una sfida.
	 * @return la costante maxRobot
	 */
	public int getMaxRobot() {
		return maxRobot;
	}
	
	/**
	 * Restituisce la squadra vincitrice della sfida
	 * @return la squadra vincitrice
	 */
	public String getVincitrice() {
		return vincitrice;
	}
	
	/**
	 * Restituisce la squadra perdente della sfida
	 * @return la squadra perdente
	 */
	public String getPerdente() {
		return (vincitrice.equals(sq1)) ? sq2 : sq1;
	}
	
	//metodi modificatori
	/**
	 * Incrementa il numero dei round in modo progressivo
	 */
	public void setRound() {
		numRound++;
	}
	
	public ArrayList<Robot> getArrayRobot1()
	{
		return robot1;
	}
	
	public ArrayList<Robot> getArrayRobot2()
	{
		return robot2;
	}
	
	/**
	 * Pre-condizioni: robot != null.
	 * Aggiunge un robot della prima squadra alla sfida
	 * @param robot il robot che il giocatore intende usare in sfida
	 */
	public void addRobot1(Robot robot) {
		if(robot1.size()<maxRobot)
			robot1.add(robot.clone());
	}
	
	/**
	 * Pre-condizioni: robot != null.
	 * Aggiunge un robot della seconda squadra alla sfida
	 * @param robot il robot che il giocatore intende usare in sfida
	 */
	public void addRobot2(Robot robot) {
		if(robot2.size()<maxRobot)
			robot2.add(robot.clone());
	}
	
	/**
	 * Permette ad un robot combattente di attaccare un robot della squadra avversaria
	 * calcolando il danno che esso subir� e diminuendo la sua salute.
	 * @param attaccante il robot che attaccher�
	 * @param indiceAttaccato l'indice del robot avversario che si vuole attaccare
	 * @param squadra true se si vuole attaccare un robot della seconda squadra, false altrimenti
	 */
	public void attacca(Combattente attaccante, int indiceAttaccato, boolean squadra) {
		int danno = 0;
		if(tipoScenario==attaccante.getTipo())
			danno = 10;
		if(squadra) {
			robot2.get(indiceAttaccato).subisce(attaccante.infligge() + danno);
			if(robot2.get(indiceAttaccato).getSalute()<=0)
				robot2.remove(indiceAttaccato);
			if(robot1.get(robot1.indexOf(attaccante)).getEnergia() <= 0)
				robot1.remove(robot1.indexOf(attaccante));
		}
		else {
			robot1.get(indiceAttaccato).subisce(attaccante.infligge() + danno);
			if(robot1.get(indiceAttaccato).getSalute()<=0)
				robot1.remove(indiceAttaccato);
			if(robot2.get(robot2.indexOf(attaccante)).getEnergia() <= 0)
				robot2.remove(robot2.indexOf(attaccante));
		}
	}
	
	/**
	 * Permette ad un robot riparatore di curare un altro robot della sua squadra,
	 * calcolando la cur� che esso ricever� in base al materiale da riparazione utilizzato
	 * e aggiungendo vita al robot curato.
	 * @param riparante il robot che ripara
	 * @param indiceRiparato l'indice del robot della propria squadra che deve essere riparato
	 * @param squadra true per curare un robot della seconda squadra, false per un robot della prima squadra
	 */
	public void cura(Riparatore riparante, int indiceRiparato, boolean squadra) {
		if(squadra) {
			int cura = riparante.ripara(100-robot2.get(indiceRiparato).getSalute());
			robot2.get(indiceRiparato).setSalute(robot2.get(indiceRiparato).getSalute() + cura);
		}
		else {
			int cura = riparante.ripara(100-robot1.get(indiceRiparato).getSalute());
			robot1.get(indiceRiparato).setSalute(robot1.get(indiceRiparato).getSalute() + cura);
		}
			
	}
	
	/**
	 * Alla fine di ogni round viene controllato se una delle due squadre
	 * ha tutti e 5 i robot inutilizzabili ed eventualmente sceglie la squadra vincitrice.
	 * @return il nome della squadra 1 se � la squadra vincitrice, il nome della squadra 2 se � la squadra vincitrice, null se la sfida non � ancora finita
	 */
	public String controllo() {
		int n=0;
		for(Robot r : robot1) 
			if(r instanceof Combattente) 
				n++;
		if(n == 0)
			vincitrice = sq2;
		else if(robot2.size() == 0)
			vincitrice = sq1;
		return vincitrice;
	}
	
	/**
	 * Clona un oggetto di tipo clone
	 */
	public Sfida clone() {
		try {
			Sfida s = (Sfida) super.clone();
			for(int i=0; i<robot1.size(); i++)
				s.robot1.add(robot1.get(i));
			for(int i=0; i<robot2.size(); i++)
				s.robot2.add(robot2.get(i));
			return s;
		}catch(CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * Simula una sfida estraendo il vincitore in modo randomico
	 * @return squadra 1 o squadra 2 in base al numero estratto
	 */
	public String randomVincitrice() {
		Random r = new Random();
		if(r.nextInt(2) == 1) {
			vincitrice = sq2;
			return sq2;
		}
		vincitrice = sq1;
		return sq1;
	}
	
	public void abbandono() {
		vincitrice = sq2;
	}
	
	private int numRound, tipoScenario, quota;
	private String sq1, sq2, vincitrice;
	private ArrayList<Robot> robot1, robot2;
	private static final int maxRobot = 5;
}
