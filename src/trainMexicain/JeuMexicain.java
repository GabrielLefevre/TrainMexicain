package trainMexicain;
import java.util.*;
/**
 * 
* @author Gabriel.Lefevre 1B1
 * @version 1.0
 * Class représentant un jeu mexicain complet avec la pioche les joueurs et le train commun
 */
public class JeuMexicain {
	private Domino dominoDepart;
	private Joueur[] joueurs;
    private static int joueursMax = 4;
    private Deque<Domino> trainCommun = new ArrayDeque<Domino>();
    private Pile Pile;
    private Scanner sc;
    
    Random rd = new Random();
	int random = rd.nextInt(4);
	
	public JeuMexicain(Pile pile) {
		this.joueurs = new Joueur[joueursMax];
		this.Pile=pile;
		sc= new Scanner(System.in);		
	}
	/**
	 * méthode d'execution de la partie
	 */
	public void run() {
		int i=random;
		Pile.creerPioche();
		Pile.melangerPioche();
		creationJoueur();
		for (int k=0;k<4;k++) {
			joueurs[k].remplirMainJoueur();
		}
		dominoDepart(i);
		joueurDepart();
		afficherDominoDepart();
		while(Pile.pioche.size()!=0 || joueurs[i].mainJ.size()!=0 ) {
				if (i==4) {
					i=0;
				}//if
				joueurs[i].afficherMain(i);
				afficherTrain(i);
				afficherTrainCommun();
				peutJouer(i);
				i++;
			
		}//while
		System.out.println("C'est le joueur" + i + "qui a gagné !!");
	}//run()
	/**
	 * 
	 * @return domino
	 * permet de retourner le domino de départ
	 */
	public Domino getDominoDepart(){
		return dominoDepart;
	}
	/**
	 * 
	 * @return domino
	 * permet de retourner le premier domino du train(dans le programme nous placerons les dominos au DEBUT des trains(pour des questions de facilité) mais cela ne sera pas visible par le joueur
	 */
	public Domino getPremierDominoTrain() {
		return trainCommun.getFirst();
	}
	
	/**
	 * permet d'afficher dans le terminal le joueur de départ
	 */
	public void joueurDepart() {
		System.out.println("Le premier joueur a jouer est le n° " + random);
	}
	
	/**
	 * permet de creer les 4 joueurs qui s'apprettent a démarrer la partie
	 */
	public void creationJoueur() {
	for (int i = 0; i < joueursMax; i++) {
		String nom;
		switch(i){
			case 0:
				nom = "Albert";
			break;
			case 1:
				nom = "Mauricette";
			break;
			case 2:
				nom = "Ginette";
			break;
			case 3:
				nom = "Maurice";
			break;
			default:
				nom = "Inconnu";
		}
		joueurs[i] = new Joueur(nom, Pile);
	}
}  
	
/**
 * 
 * @param alea
 * parcours la main des joueurs avec un paramètre aléatoire jusqu'à trouver un double
 */
	public void dominoDepart(int alea) {
		int i=alea;
		int indice=-1;
		while (i<=4 && indice==-1) {
			indice=joueurs[i].rechercheDoubleMax();
			if ( indice ==-1) {
				joueurs[i].piocheMain();
				i++;
			}
			if (i==4) {
				i=0;
			}//if
		} // while
		 dominoDepart = joueurs[i].getDominoIndice(indice);
		 joueurs[i].supprimerDomino(indice);
	} // dominoDepart

	
	/**
	 * affiche dans le terminal le domino de départ
	 */
	public void afficherDominoDepart() {
		 System.out.println("Le domino de depart est : " + dominoDepart);
	}
	/**
	 * 
	 * @return boolean
	 * permet de verifier si un train a deja reçu un domino
	 */
	public boolean commencementTrain() {
		if (trainCommun.size()==0) {
			return false;
		}
		else
			return true;
	}
	/**
	 * 
	 * @param domino
	 * permet de placer un domino sur le train(en premiere position de la liste)
	 */
	public void ajouterDominoTrainCommun(Domino domino) {
		trainCommun.addFirst(domino);
	}
	/**
	 * affiche le dernier domino joué sur le train, affiche que le train et vide dans le cas contraire
	 */
	public void afficherTrainCommun() {
		if (trainCommun.size()==0) {
			System.out.println("le train mexicain est vide");
		}
		else {
			System.out.print("Le dernier domino du train mexicain est : ");
			System.out.println(trainCommun.getFirst());
		}
	}
	/**
	 * 
	 * @param j
	 * permet au joueur de choisir entre poser un domino ou piocher, dans le cas ou il ne peux pas poser de domino il sera contraint de piocher
	 */
	public void peutJouer(int j) {
		System.out.print("Voulez vous jouer ou piocher ??(j/p)");
		String s=sc.next();
		if (s.equalsIgnoreCase("j")) {
			System.out.println("donnez l'indice du domino que vous voulez jouer");
			int i=sc.nextInt();
			ajouterDominoTrain(joueurs[j].mainJ.get(i), j);
		} // if
		else if ( s.equalsIgnoreCase("p")) {
			joueurs[j].piocheMain();
		} // else if
		else 
			peutJouer(j);
	}
	/**
	 * 
	 * @param domino
	 * @param a
	 * demande au joueur dont l'indice est entrée en parametre sur quel train il désire jouer et vérifié que cela est possible, dans le cas contraire il sera renvoyer a la méthode lui demandant de jouer ou piocher
	 */
	public void ajouterDominoTrain(Domino domino, int a) { 
		System.out.println("Choisissez le train ou vous desirez jouer (t/c) t: votre train, c: train commun");
		String s=sc.next();
		if ( s.equalsIgnoreCase("t")) {
			if (joueurs[a].train.size() == 0) {
				if (domino.getPartD()==getDominoDepart().getPartD()) {
					domino.permuterDomino();
					joueurs[a].train.addFirst(domino);
					joueurs[a].mainJ.remove(domino);
				} // if
				else if (domino.getPartG()==getDominoDepart().getPartD()) {
					joueurs[a].train.addFirst(domino);
					joueurs[a].mainJ.remove(domino);
				} // else if
				else { 
					System.out.println("vous ne pouvez pas placer ce domino !");
					peutJouer(a);
				} // else
			} // if train.size == 0
			else if (joueurs[a].train.size() !=0) {
				if (domino.getPartD()==joueurs[a].train.getFirst().getPartD()) {
					domino.permuterDomino();
					joueurs[a].train.addFirst(domino);
					joueurs[a].mainJ.remove(domino);
				} // if
				else if (domino.getPartG()==joueurs[a].train.getFirst().getPartD()) {
					joueurs[a].train.addFirst(domino);
					joueurs[a].mainJ.remove(domino);
				} // else if
				else { 
					System.out.println("vous ne pouvez pas placer ce domino !");
					peutJouer(a);
				}
			}//else if
		} //if
		else if (s.equalsIgnoreCase("c")){
			if (commencementTrain()==false) {
				if (domino.getPartD()==getDominoDepart().getPartD()) {
					domino.permuterDomino();
					ajouterDominoTrainCommun(domino);
					joueurs[a].mainJ.remove(domino);
				} // if
				else if (domino.getPartG()==getDominoDepart().getPartD()) {
					ajouterDominoTrainCommun(domino);
					joueurs[a].mainJ.remove(domino);
				} // else if
				else { 
					System.out.println("vous ne pouvez pas placer ce domino !");
					peutJouer(a);
				}
			} // if commencementT=false
			else if (commencementTrain()==true) {
				if (domino.getPartD()==getPremierDominoTrain().getPartD()) {
					domino.permuterDomino();
					ajouterDominoTrainCommun(domino);
					joueurs[a].mainJ.remove(domino);
				} // if
				else if(domino.getPartG()==getPremierDominoTrain().getPartD()) {
					ajouterDominoTrainCommun(domino);
					joueurs[a].mainJ.remove(domino);
				}
				else { 
					System.out.println("vous ne pouvez pas placer ce domino !");
					peutJouer(a);
				}
			}//else if
		} // else if
		else
			ajouterDominoTrain(domino, a);
	}//ajouterDominoTrain()
	/**
	 * 
	 * @param i
	 * affiche le train du joueur entrée en parametre
	 */
	public void afficherTrain(int i) {
		if (joueurs[i].train.size()==0) {
			System.out.println("votre train est vide");
		}
		else {
			System.out.print("Le dernier domino de votre train : ");
			System.out.println(joueurs[i].train.getFirst());
		}
	}
	
}// class TrainMexicain
