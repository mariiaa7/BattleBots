package robot;
import equipaggiamento.Attacco;
import equipaggiamento.Difesa;

/**
 * Robot in grado di infliggere danni attraverso attacchi e armi. Ogni robot � contraddistinto da un tipo,
 * che gli consente di essere avvantaggiato in alcuni scenari e svantaggiato in altri. E' inoltre possibile
 * equipaggiare un robot con armi di attacco e difesa, per migliorarne le prestazioni in combattimento.
 * @author Francesco Miranda, Michele Mattiello, Carlo Sorrentino, Maria Immacolata Colella
 *
 */
public class Combattente extends Robot{
	/**
	 * Istanzia un nuovo robot combattente
	 * @param myNome nome del robot
	 * @param myTipo tipo del robot
	 * @param myDanno danno base del robot
	 * @param myAtk	arma di attacco equipaggiata al robot
	 * @param myDif	arma di difesa equipaggiata al robot 
	 */
	public Combattente(String myNome, int p, int myTipo, int myDanno, Attacco myAtk, Difesa myDif)
	{
		super(myNome, p);
		tipo = myTipo;
		danno = myDanno;
		atk = myAtk;
		dif = myDif;
	}
	
	//metodi accesso
	/**
	 * Restituisce la statistica di danno base che un robot farebbe con un attacco
	 * @return danno del robot
	 */
	public int getDanno() {
		return danno;
	}
	
	/**
	 * Restituisce le caratteristiche dell'arma di attacco equipaggiata al robot
	 * @return arma di attacco equipaggiata
	 */
	public Attacco getAttacco() {
		return atk;  //da scrivere
	}
	
	/**
	 * Restituisce il tipo che caratteriza il robot
	 * @return tipo del robot
	 */
	public int getTipo() {
		return tipo;
	}
	
	/**
	 * Restituisce l'arma di difesa equipaggiata al robot
	 * @return arma di difesa equipaggiata
	 */
	public Difesa getDifesa() {
		return dif;  //da scrivere
	}
	
	//metodi modificatori
	/**
	 * Permette di cambiare l'arma di attacco equipaggiata a un robot
	 * @param myAtk nuova arma di attacco da equipaggiare
	 */
	public void setAttacco(Attacco myAtk) {
		atk = myAtk.clone();
	}
	
	/**
	 * Permette di cambiare l'arma di difesa equipaggiata a un robot
	 * @param myDif	nuova arma di difesa da equipaggiare
	 */
	public void setDifesa(Difesa myDif) {
		dif = myDif.clone();
	}
	
	/**
	 * Calcola il danno che un robot combattente infligge ad un altro, considerando sia la statistica di attacco base
	 * che l'arma di attacco equipaggiata.
	 * @return danno inflitto
	 */
	public int infligge()
	{
		if(super.getEnergia()<=0)  //controllo sulla salute nella sfida
			return 0;
		super.setEnergia(super.getEnergia()-10);
		
		if(atk==null || atk.getDurabilita()<=0) 
			return danno;
		atk.setDurabilita();
		
		return danno + atk.getValore();
	}
	
	/**
	 * Calcola il danno subito da un robot dopo aver subito un attacco, considerando la statistica di 
	 * attacco del robot che effettua l'attacco e l'arma di difesa equipaggiata al robot che subisce l'attacco.
	 */
	public void subisce(int val)
	{
		if(dif!=null) {
			if(dif.getDurabilita()>0) {
				if(val<dif.getValore())
					val=0;
				else 
					val-=dif.getValore();
				dif.setDurabilita();
			}
		}
		super.setSalute(super.getSalute()-val);
	}
	
	/**
	 * Restituisce la stringa descrittiva dello stato delle variabili d'istanza
	 */
	public String toString() {
		return super.toString() + "[Danno=" + danno + ", tipo=" + tipo + ", Arma Attacco=" + atk.toString() + ", Arma difesa=" + dif.toString() + "]";
	}
	
	/**
	 * Clona un robot combattente
	 */
	public Combattente clone()
	{
		Combattente o = (Combattente) super.clone();
		o.atk = atk.clone();
		o.dif = dif.clone();
		return o;
	}
	
	private int danno, tipo;
	private Attacco atk;
	private Difesa dif;
}
