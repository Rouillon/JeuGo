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
 * @author fabienrouillon
 */
public class Jeu {

    public static String s; //pour demander le joueur est-ce qu'il veut passer ce manche. ça doit etre "oui" ou "non"
    private int captureN;
    private int captureB;
    int[][] plateau = new int[16][16];
    ArrayList<Pion> listeTestes;
    ArrayList<ArrayList<Pion>> listeGroupes;


    public Jeu() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.plateau[i][j] = 0;
            }
        }
    }

    public boolean poserPierreNoir(int[][] plateau, int x, int y) {

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
            } else {
                {
                    if (plateau[x][y] == 0 && x < 16 && x >= 0 && y < 16 && y >= 0) {
                        if (eviterSuicideNoir(plateau, x, y)) {
                            plateau[x][y] = 1; // 1 est pour les pierres noirs
                        }
                    } else {
                        System.out.println("Vous pouvez pas le poser ici!");
                        continuer = true;
                    }
                }
            }
        } while (continuer);
        return passerNoir;
    }

    public boolean poserPierreBlanc(int[][] plateau, int x, int y) {

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
                            plateau[x][y] = -1; //-1 est pout les pierres blancs
                        }
                    } else {
                        System.out.println("Vous pouvez pas le poser ici!");
                        continuer = true;
                    }
                }
            }
        } while (continuer);
        return passerBlanc;
    }

    public boolean eviterSuicideNoir(int[][] plateau, int x, int y) {
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

     public boolean eviterSuicideBlanc(int[][] plateau, int x, int y) {
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
    public int nbPierreNoir(int[][] plateau) {
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

    public int nbPierreBlanc(int[][] plateau) {
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

    public ArrayList<Pion> adjacents(Pion p){
        int px = p.getX();
        int py = p.getY();
        ArrayList<Pion> adjacents = new ArrayList();
        if ((this.plateau[px-1][py]==1)||(this.plateau[px-1][py]==-1)){
            adjacents.add(new Pion(px-1,py));
        }
        if ((this.plateau[px+1][py]==1)||(this.plateau[px+1][py]==-1)){
            adjacents.add(new Pion(px+1,py));
        }
        if ((this.plateau[px][py-1]==1)||(this.plateau[px][py-1]==-1)){
            adjacents.add(new Pion(px,py-1));
        }
        if ((this.plateau[px][py+1]==1)||(this.plateau[px][py+1]==-1)){
            adjacents.add(new Pion(px,py+1));
        }
        return adjacents;
    }
    public ArrayList<Pion> ajouterAdjacent(Pion p, ArrayList<Pion> groupe){
        groupe.add(p);
        this.listeTestes.add(p);
        ArrayList<Pion> adjacents = this.adjacents(p);
        for(Pion adja : adjacents){
            if(!this.listeTestes.contains(adja)){
                this.ajouterAdjacent(adja, groupe);
            }
        }
        return groupe;
    }
}
