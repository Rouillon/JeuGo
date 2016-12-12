/*
*TP3 de MEDEV 
*Jeu de GO
*qui a le but pour "test"
 */
package JeuGo;
import static JeuGo.Jeu.s;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 *
 * ici se trouve les fonctions pour le fonctionnement du jeu hors affichage
 */
public class Jeu {
    
    /**
    * les attributs ci dessous sont surtout des aides pour les différentes fonctions
    * String s qui est utile pour lire les réponses de l'utilisateur
    * listeTestes : liste des Pions testés, sert à la constitution de groupes
    * listeGroupes : prend en mémoire les groupes à un instant t
    */
    public static String s; //pour demander le joueur est-ce qu'il veut passer ce manche. ça doit etre "oui" ou "non"
    ArrayList<Pion> listeTestes;
    ArrayList<ArrayList<Pion>> listeGroupes;
    
    public Jeu() {
        
    }
    /**
     * sert à poser une pierre noire sur le plateau, une pierre noire est représentée par 1.
     * @param plateau le plateau de jeu
     * @param x position abscisse
     * @param y position ordonnées
     * @return true ou false en fonction de si l'utilisateur souhaite passer son tour ou pas
     */
    public static boolean poserPierreNoir(int[][] plateau, int x, int y) {
        boolean passerNoir = false;//pour marquer si le joueur veut passer
        boolean continuer = false;
        do {
            System.out.println("Est-ce que vous voulez passer cette manche?");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            try {
                s = br.readLine();
            } catch (IOException e) {
            }
            if (s.equals("oui")) {
                passerNoir = true;
                continuer = false;
            } else {
                {
                    if (plateau[x][y] == 0 && x < 16 && x >= 0 && y < 16 && y >= 0) {
                        if (eviterSuicideNoir(plateau, x, y)) {
                            plateau[y][x] = 1; // 1 est pour les pierres noirs
                        }
                        continuer=false; 
                    } else {
                        System.out.println("Vous pouvez pas le poser ici!");
                        continuer = true;
                    }
                }
            }
        } while (continuer);
        return passerNoir;
    }
    /**
     * de même que poserPierreNoir, sauf que la pierre blanche est représentée par -1
     * @param plateau
     * @param x
     * @param y
     * @return 
     */
    public static boolean poserPierreBlanc(int[][] plateau, int x, int y) {
        boolean passerBlanc = false;//pour marquer si le joueur veut passer
        boolean continuer = false;
        do {
            System.out.println("Est-ce que vous voulez passer cette manche?");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            try {
                s = br.readLine();
            } catch (IOException e) {
            }
            if (s.equals("oui")) {
                passerBlanc = true;
            } else {
                {
                    if (plateau[x][y] == 0 && x < 16 && x >= 0 && y < 16 && y >= 0) {
                        if (eviterSuicideBlanc(plateau, x, y)) {
                            plateau[y][x] = -1; //-1 est pout les pierres blancs                          
                        }
                        continuer=false;  
                    } else {
                        System.out.println("Vous pouvez pas le poser ici!");
                        continuer = true;
                    }
                }
            }
        } while (continuer);
        return passerBlanc;
    }
    /**
     * contrôle le suicide des joueurs
     * @param plateau de jeu
     * @param x position x de la pierre noire
     * @param y position y de la pierre noire
     * @return true si on peut la poser, false si on ne peut pas 
     */
    public static boolean eviterSuicideNoir(int[][] plateau, int x, int y) {
        boolean poserNoir = true;
       //Si autour de (x,y) est -1 (pierre blanc), on peut pas mettre ce pierre noir a case (x,y)
        if (x == 0 && y == 0) {
            if (plateau[x + 1][y] == -1 && plateau[x][y + 1] == -1) {
                poserNoir = false;
            }
        } else if (x == 15 && y == 0) {
            if (plateau[x - 1][y] == -1 && plateau[x][y + 1] == -1) {
                poserNoir = false;
            }
        } else if (x == 0 && y == 15) {
            if (plateau[x + 1][y] == -1 && plateau[x][y - 1] == -1) {
                poserNoir = false;
            }
        } else if (x == 15 && y == 15) {
            if (plateau[x - 1][y] == -1 && plateau[x][y - 1] == -1) {
                poserNoir = false;
            }
        } else if (x == 0 && y != 0 && y != 15) {
            if (plateau[x + 1][y] == -1 && plateau[x][y + 1] == -1 && plateau[x][y - 1] == -1) {
                poserNoir = false;
            }
        } else if (y == 0 && x != 0 && x != 15) {
            if (plateau[x + 1][y] == -1 && plateau[x - 1][y] == -1 && plateau[x][y + 1] == -1) {
                poserNoir = false;
            }
        } else if (x == 15 && y != 0 && y != 15) {
            if (plateau[x - 1][y] == -1 && plateau[x][y + 1] == -1 && plateau[x][y - 1] == -1) {
                poserNoir = false;
            }
        } else if (y == 15 && x != 0 && x != 15) {
            if (plateau[x + 1][y] == -1 && plateau[x - 1][y] == -1 && plateau[x][y - 1] == -1) {
                poserNoir = false;
            }
        } else if (x > 0 && x < 15 && y > 0 && y < 15) {
            if (plateau[x + 1][y] == -1 || plateau[x - 1][y] == -1 || plateau[x][y + 1] == -1 || plateau[x][y - 1] == -1) {
                poserNoir = false;
            }
        }
        return poserNoir;
    }
    /**
     * de même que pour le suicide noire mais pour les pierres blanches
     * @param plateau
     * @param x
     * @param y
     * @return 
     */
     public static boolean eviterSuicideBlanc(int[][] plateau, int x, int y) {
        boolean poserBlanc = true;
        //Si autour de (x,y) est 1 (pierre noir), on peut pas mettre ce pierre blanc a case (x,y)
        if (x == 0 && y == 0) {
            if (plateau[x + 1][y] == 1 && plateau[x][y + 1] == 1) {
                poserBlanc = false;
            }
        } else if (x == 15 && y == 0) {
            if (plateau[x - 1][y] == 1 && plateau[x][y + 1] == 1) {
                poserBlanc = false;
            }
        } else if (x == 0 && y == 15) {
            if (plateau[x + 1][y] == 1 && plateau[x][y - 1] == 1) {
                poserBlanc = false;
            }
        } else if (x == 15 && y == 15) {
            if (plateau[x - 1][y] == 1 && plateau[x][y - 1] == 1) {
                poserBlanc = false;
            }
        } else if (x == 0 && y != 0 && y != 15) {
            if (plateau[x + 1][y] == 1 && plateau[x][y + 1] == 1 && plateau[x][y - 1] == 1) {
                poserBlanc = false;
            }
        } else if (y == 0 && x != 0 && x != 15) {
            if (plateau[x + 1][y] == 1 && plateau[x - 1][y] == 1 && plateau[x][y + 1] == 1) {
                poserBlanc = false;
            }
        } else if (x == 15 && y != 0 && y != 15) {
            if (plateau[x - 1][y] == 1 && plateau[x][y + 1] == 1 && plateau[x][y - 1] == 1) {
               poserBlanc = false;
            }
        } else if (y == 15 && x != 0 && x != 15) {
            if (plateau[x + 1][y] == 1 && plateau[x - 1][y] == 1 && plateau[x][y - 1] == 1) {
                poserBlanc = false;
            }
        } else if (x > 0 && x < 15 && y > 0 && y < 15) {
            if (plateau[x + 1][y] == 1 || plateau[x - 1][y] == 1 || plateau[x][y + 1] == 1 || plateau[x][y - 1] == 1) {
                poserBlanc = false;
            }
        }
        return poserBlanc;
    }
//    public void recommencer() {
//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                this.plateau[i][j] = 0;
//            }
//        }
//    }
    /**
     * calcule le nombre de pierre noire sur le plateau en vue du comptage
     * @param plateau
     * @return le nombre de pierre noire sur le plateau
     */
    public static int nbPierreNoir(int[][] plateau) {
        int nbNoir = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (plateau[i][j] == 1) {
                    nbNoir++;
                }
            }
        }
        return nbNoir;
    }
    /**
     * pareil pour les pierres blanches
     * @param plateau
     * @return 
     */
    public static int nbPierreBlanc(int[][] plateau) {
        int nbBlanc = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (plateau[i][j] == -1) {
                    nbBlanc++;
                }
            }
        }
        return nbBlanc;
    }
<<<<<<< HEAD
    public static ArrayList<Pion> adjacents(int[][] plateau, Pion p){
=======
    /**
     * à partir d'un pion P, trouve les pions adjacents et les retourne dans une liste de pion
     * @param plateau
     * @param p pion à partir duquel on cherche les adjacents
     * @return l'arrayListe des pions adjacents
     */
    public ArrayList<Pion> adjacents(int[][] plateau, Pion p){
>>>>>>> origin/master
        int px = p.getX();
        int py = p.getY();
        int couleur = p.getCouleur();
        ArrayList<Pion> adjacents = new ArrayList();
        if (plateau[px-1][py]==couleur){
            adjacents.add(new Pion(px-1,py,couleur));
        }
        if (plateau[px+1][py]==couleur){
            adjacents.add(new Pion(px+1,py,couleur));
        }
        if (plateau[px][py-1]==couleur){
            adjacents.add(new Pion(px,py-1,couleur));
        }
        if (plateau[px][py+1]==couleur){
            adjacents.add(new Pion(px,py+1,couleur));
        }
        return adjacents;
    }
    
    /**
     * Constitue un groupe de pion à partir d'un pion de départ
     * @param plateau
     * @param p pion de départ de la recherche de groupe
     * @param groupe groupe qu'on va remplir avec les éléments adjacents
     * @return le groupe sous forme d'arraylist<Pion>
     */
    public ArrayList<Pion> ajouterAdjacent(int[][] plateau,Pion p, ArrayList<Pion> groupe){
        groupe.add(p);
        this.listeTestes.add(p);
        ArrayList<Pion> adjacents = adjacents(plateau, p);
        for(Pion adja : adjacents){
            if(!this.listeTestes.contains(adja)){
                this.ajouterAdjacent(plateau,adja, groupe);
            }
        }
        return groupe;
    }
    /**
     * constitue tous les groupes du plateau à un instant donné 
     * @param plateau 
     */
    public void detectionGroupes(int[][] plateau){
        this.listeTestes.clear();
        this.listeGroupes.clear();
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                //blanc 
                if(plateau[i][j]!=0){
                    Pion p = new Pion(i,j);
                    if(!this.listeTestes.contains(p)){
                        ArrayList<Pion> nouveau = new ArrayList<>();
                        this.listeGroupes.add(this.ajouterAdjacent(plateau,p,nouveau));
                    }
                }
            }
        }
    }
    /**
     * supprime les groupes encerclés
     * @param plateau 
     */
    public void detectionCapture(int[][] plateau){
        boolean capture=true;
        ArrayList<Pion> adj;
        for (ArrayList<Pion> groupe:this.listeGroupes){
            for (Pion p: groupe){
                adj = adjacents(plateau,p);
                if (adj.size()<4){ // TODO ou sur le bord
                    capture=false;
                }
            }
            if (capture) {
                    for (Pion p:groupe) {
                    plateau[p.getX()][p.getY()]=0;
                }
                }
        }
    }
}