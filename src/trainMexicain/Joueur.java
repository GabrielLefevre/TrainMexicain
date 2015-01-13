package trainMexicain;
import java.util.*;
/**
 * 
* @author Gabriel.Lefevre 1B1
 * @version 1.0
 * Class représentant un joueur
 */
public class Joueur {
    
    private String nom;
    protected ArrayList<Domino> mainJ = new ArrayList<Domino>(); 
    protected Deque<Domino> train = new ArrayDeque<Domino>();
    private Pile Pile;
    
    public Joueur(String nom, Pile Pile){
    	this.nom = nom;
    	this.Pile=Pile;
    	
    	
    }
   /**
    * 
    * @return string
    * permet de retourner le nom d'un joueur
    */
    public String getNom() {
        return nom;
    }
    /**
     * 
     * @param i indice du joueur
     * @return domino
     * permet de retourner un domino d'indice i de la main d'un joueur
     */
    public Domino getDominoIndice(int i) {
    	if (i<mainJ.size()) {
    		return mainJ.get(i);
    	}
    	else 
    		return null;
    }
    /**
     * 
     * @return Deque
     * permet de retourner le train d'un joueur
     */
    public Deque<Domino> getTrain() {
    	return train;
    }

 /**
  * permet de faire piocher 10 dominos au joueur en début de partie afin de composer sa main
  */
    public void remplirMainJoueur() {
    	for (int i=0;i<10;i++) {
    		mainJ.add(i,Pile.piocher());
    	}
    }
    /**
     * 
     * @param j
     * affiche la main su joueur dont l'indice est entrée en paramètre
     */
	public void afficherMain(int j) {
		System.out.println("Main du joueur" + j +" : ");
		for (int i=0; i<mainJ.size(); i++) {
			System.out.print(i);
			System.out.print( mainJ.get(i) + " ");
		}
		System.out.println(" \n");
	}
	/**
	 * permet de faire passer un domino de la pioche a la main d'un joueur
	 */
	public void piocheMain() {
		Domino pioche=Pile.piocher();
		mainJ.add(pioche);
	}
	/**
	 * 
	 * @return int
	 * retourne l'indice du domino plus grand double de la main du joueur 
	 */
	public int rechercheDoubleMax() {
		int valeurMax =-1;
		int indice =-1;
		int longueur = mainJ.size();
			for (int i=0; i<longueur;i++) {
				if (mainJ.get(i).estDouble() && (mainJ.get(i).getPartG()>valeurMax)) {
					valeurMax = mainJ.get(i).getPartG();
					indice =i;
				} // if
			} // for
				return indice;
	} // rechercheDoubleMax
	/**
	 * 
	 * @param i
	 * permet de supprimer un domino de la main d'un joueur avec son indice
	 */
	public void supprimerDomino(int i){
		mainJ.remove(i);
	}
	
	
} // class Joueur





