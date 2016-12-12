/*
*TP3 de MEDEV 
*Jeu de GO
*qui a le but pour "test"
 */
package JeuGo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * ici se trouve les fonctions pour le fonctionnement du jeu hors affichage
 */
public class Jeu {

    /**
     * les attributs ci dessous sont surtout des aides pour les différentes
     * fonctions String s qui est utile pour lire les réponses de l'utilisateur
     * listeTestes : liste des Pions testés, sert à la constitution de groupes
     * listeGroupes : prend en mémoire les groupes à un instant t
     */
    public static String s; //pour demander le joueur est-ce qu'il veut passer ce manche. ça doit etre "oui" ou "non"
    public static ArrayList<Pion> listeTestes;
    public static ArrayList<ArrayList<Pion>> listeGroupesBlanc;
    public static ArrayList<ArrayList<Pion>> listeGroupesNoir;
    
    public Jeu() {
        
    }

    /**
     * sert à poser une pierre noire sur le plateau, une pierre noire est
     * représentée par 1.
     *
     * @param plateau le plateau de jeu
     * @param x position abscisse
     * @param y position ordonnées
     * @return true ou false en fonction de si l'utilisateur souhaite passer son
     * tour ou pas
     */
    public static boolean poserPierreNoir(int[][] plateau, int x, int y) {
        boolean passerNoir = false;//pour marquer si le joueur veut passer
        boolean continuer = false;
        
        if (plateau[x][y] == 0 && x < 16 && x >= 0 && y < 16 && y >= 0) {
            //if (eviterSuicideNoir(plateau, x, y)) {
            plateau[x][y] = 1; // 1 est pour les pierres noirs
            //}
            continuer = false;
        } else {
            System.out.println("Vous ne pouvez pas le poser ici!");
        }
        
        return passerNoir;
    }

    /**
     * de même que poserPierreNoir, sauf que la pierre blanche est représentée
     * par -1
     *
     * @param plateau
     * @param x
     * @param y
     * @return
     */
    public static boolean poserPierreBlanc(int[][] plateau, int x, int y) {
        boolean passerBlanc = false;//pour marquer si le joueur veut passer
        boolean continuer = false;
        
        if (plateau[x][y] == 0 && x < 16 && x >= 0 && y < 16 && y >= 0) {
            //if (eviterSuicideBlanc(plateau, x, y)) {
            plateau[x][y] = -1; //-1 est pout les pierres blancs                          
            //}
            continuer = false;
        } else {
            System.out.println("Vous ne pouvez pas le poser ici!");
        }
        
        return passerBlanc;
    }

    /**
     * contrôle le suicide des joueurs
     *
     * @param plateau de jeu
     * @param x position x de la pierre noire
     * @param y position y de la pierre noire
     * @return True si on peut la poser, false si on ne peut pas
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
     *
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
     *
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
     *
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

    /**
     * à partir d'un pion P, trouve les pions adjacents et les retourne dans une
     * liste de pion
     *
     * @param plateau
     * @param p pion à partir duquel on cherche les adjacents
     * @return l'arrayListe des pions adjacents
     */
    public static LinkedList<Pion> adjacents(int[][] plateau, Pion p) {
        int px = p.getX();
        int py = p.getY();
        int couleur = p.getCouleur();
        LinkedList<Pion> adjacents = new LinkedList<>();
        if (plateau[px - 1][py] == couleur) {
            adjacents.add(new Pion(px - 1, py, couleur));
        }
        if (plateau[px + 1][py] == couleur) {
            adjacents.add(new Pion(px + 1, py, couleur));
        }
        if (plateau[px][py - 1] == couleur) {
            adjacents.add(new Pion(px, py - 1, couleur));
        }
        if (plateau[px][py + 1] == couleur) {
            adjacents.add(new Pion(px, py + 1, couleur));
        }
        return adjacents;
    }

    /**
     * Constitue un groupe de pion à partir d'un pion de départ
     *
     * @param plateau
     * @param p pion de départ de la recherche de groupe
     * @param groupe groupe qu'on va remplir avec les éléments adjacents
     * @return le groupe sous forme d'ArrayList<Pion>
     */
    public static ArrayList<Pion> ajouterAdjacent(int[][] plateau, Pion p, ArrayList<Pion> groupe) {
        groupe.add(p);
        listeTestes.add(p);
        LinkedList<Pion> adjacents = adjacents(plateau, p);
        boolean test2 = false;
        for (Pion adja : adjacents) {
            for (int i = 0; i < listeTestes.size(); i++) {
                if (listeTestes.get(i).equals(adja)) {
                    test2=true;
                }
            }
            if (!test2) {
                ajouterAdjacent(plateau, adja, groupe);
            }
        }
        return groupe;
    }

    /**
     * constitue tous les groupes de pions blancs du plateau à un instant donné
     *
     * @param plateau
     */
    public static void detectionGroupesBlanc(int[][] plateau) {
        listeTestes = new ArrayList<>();
        listeGroupesBlanc = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                //blanc
                if (plateau[i][j] == -1) {
                    Pion p = new Pion(i, j, -1);
                    boolean test = false;
                    for (Pion pp : listeTestes) {
                        if (pp.equals(p)) {
                            test = true;
                        }
                    }
                    if (!test) {                        
                        ArrayList<Pion> nouveau = new ArrayList<>();
                        listeGroupesBlanc.add(Jeu.ajouterAdjacent(plateau, p, nouveau));
                    }
                    
                }
            }
        }
    }

    /**
     * constitue tous les groupes de pions noirs du plateau à un instant donné
     *
     * @param plateau
     */
    public static void detectionGroupesNoir(int[][] plateau) {
        listeTestes = new ArrayList<>();
        listeGroupesNoir = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                //noir
                if (plateau[i][j] == 1) {
                    Pion p = new Pion(i, j, 1);
                    boolean test = false;
                    for (Pion pp : listeTestes) {
                        if (pp.equals(p)) {
                            test = true;
                        }
                    }
                    if (!test) {                        
                        ArrayList<Pion> nouveau = new ArrayList<>();
                        listeGroupesNoir.add(Jeu.ajouterAdjacent(plateau, p, nouveau));
                    }
                    
                }
            }
        }
    }

    /**
     * supprime les groupes de pions blanc encerclés
     *
     * @param plateau
     */
    public static void detectionCaptureBlanc(int[][] plateau) {
        boolean capture;
        Jeu.detectionGroupesBlanc(plateau);
        for (ArrayList<Pion> groupe : listeGroupesBlanc) {
            capture = false;
            for (Pion p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] == 1) && (plateau[p.getX()][p.getY() - 1] == 1) && (plateau[p.getX() + 1][p.getY()] == 1) && (plateau[p.getX() - 1][p.getY()] == 1)) { // TODO gérer exception sur le bord
                    capture = true;
                }
            }
            if (capture) {
                for (Pion p : groupe) {
                    plateau[p.getX()][p.getY()] = 0;
                }
            }
        }
    }

    /**
     * supprime les groupes de pions noirs encerclés
     *
     * @param plateau
     */
    public static void detectionCaptureNoir(int[][] plateau) {
        boolean capture;
        Jeu.detectionGroupesNoir(plateau);
        for (ArrayList<Pion> groupe : listeGroupesNoir) {
            capture = false;
            for (Pion p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] == -1) && (plateau[p.getX()][p.getY() - 1] == -1) && (plateau[p.getX() + 1][p.getY()] == -1) && (plateau[p.getX() - 1][p.getY()] == -1)) { // TODO gérer exception sur le bord
                    capture = true;
                }
            }
            if (capture) {
                for (Pion p : groupe) {
                    plateau[p.getX()][p.getY()] = 0;
                }
            }
        }
    }
    
}
