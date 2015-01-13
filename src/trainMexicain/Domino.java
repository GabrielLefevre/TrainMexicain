package trainMexicain;
/**
 * 
* @author Gabriel.Lefevre 1B1
 * @version 1.0
 * Class représentant un domino
 */
public class Domino {
	private int partieD;
	private int partieG;
	
	public Domino(int partD,int partG){
		this.partieD=partD;
		this.partieG=partG;
	}
	/**
	 * 
	 * @param d
	 * permet de selectionner la partie droite d'un domino
	 */
	public void setPartD(int d){
		partieD=d;
	}
	/**
	 * 
	 * @param g
	 * permet de selectionner la partie gauche d'un domino
	 */
	public void setPartG(int g){
		partieG=g;
	}
/**
 * 
 * @return int
 * permet de retourner la valeur de la partie droite du domino
 */
	public int getPartD(){
		return partieD;
	}
	/**
	 * 
	 * @return int
	 * permet de retourner la valeur de la partie gauche du domino
	 */
	public int getPartG(){
		return partieG;
	}
	/**
	 * 
	 * @return boolean
	 * permet de verifier si un domino est double ou non
	 */
	public boolean estDouble(){
		if(partieD==partieG){
			return true;
		}
		else 
			return false;
	}
	/**
	 * 
	 * @param domino entré en parametre 
	 * @return boolean
	 * permet de verifier si un domino peux être placé a la suite d'un autre dans un train
	 */
	public boolean peutEtrePlaceApres(Domino domino){
		if(this.partieD == domino.partieG || this.partieD == domino.partieD){
			return true;
		}
		else 
			return false;
	}
	/**
	 * retourne l'affichage d'un domino
	 */
	public String toString(){
		return("["+partieG+"|"+partieD+"]");
	}
	/**
	 * permet de remplacer la partie droite par la partie gauche d'un domino
	 */
	public void permuterDomino(){
		int tmp;
		tmp=partieD;
		partieD=partieG;
		partieG=tmp;
	}
	

}