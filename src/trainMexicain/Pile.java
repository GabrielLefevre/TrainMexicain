package trainMexicain;
import java.util.*;

/**
 * 
 * @author Gabriel.Lefevre 1B1
 *  @version 1.0
 *  Class représentant une pile de domino
 *
 */
public class Pile {
	protected static ArrayList<Domino> pioche = new ArrayList<Domino>();
	
	public Pile() {

	}
	/**
	 * méthode permettant de creer une pioche de 91dominos différents allant de 1/1 a 12/12
	 */
	public void creerPioche(){	
		for (int i=0;i<13;i++){
			for(int j=i ;j<13;j++){
				pioche.add(new Domino(i,j));
			}	
		}
	}
	/**
	 * Permet de melanger les dominos afin d'avoir un jeu aléatoire
	 */
	public void melangerPioche(){
		Collections.shuffle(pioche);
	}
	/**
	 * 
	 * @return domino
	 * permet de sortir le premier domino de la pioche et de le retourner
	 */
	public Domino piocher() {
		Domino pio = new Domino(pioche.get(0).getPartG(), pioche.get(0).getPartD());
		pioche.remove(0);
		return pio;
	}

} // class Pile