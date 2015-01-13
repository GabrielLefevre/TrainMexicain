package trainMexicain;
/**
 * 
* @author Gabriel.Lefevre 1B1
 * @version 1.0
 * Class permettant de lancer le programme
 */

public class main {

	public static void main(String[] args) {
		
		Pile pioche = new Pile();
		JeuMexicain game = new JeuMexicain(pioche);
		game.run();
	}
}
