/*
*TP3 de MEDEV 
*Jeu de GO
*qui a le but pour "test"
 */
package JeuGo;

import static JeuGo.Damier.SIZE_DAMIER;
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
    public static void poserPierreNoir(int[][] plateau, int x, int y) {
            plateau[x][y] = 1; // 1 est pour les pierres noirs
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
    public static void poserPierreBlanc(int[][] plateau, int x, int y) {
            plateau[x][y] = -1; //-1 est pout les pierres blancs                          
    }

    /**
     * calcule le nombre de pierre noire sur le plateau en vue du comptage
     *
     * @param plateau
     * @return le nombre de pierre noire sur le plateau
     */
    public static int nbPierreNoir(int[][] plateau) {
        int nbNoir = 0;
        for (int i = 0; i < SIZE_DAMIER-1; i++) {
            for (int j = 0; j < SIZE_DAMIER-1; j++) {
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
        for (int i = 0; i < SIZE_DAMIER-1; i++) {
            for (int j = 0; j < SIZE_DAMIER-1; j++) {
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
        boolean test2;
        for (Pion adja : adjacents) {
            test2 = false;
            for (int i = 0; i < listeTestes.size(); i++) {
                if (listeTestes.get(i).equals(adja)) {
                    test2 = true;
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
        for (int i = 0; i < SIZE_DAMIER-1; i++) {
            for (int j = 0; j < SIZE_DAMIER-1; j++) {
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
        for (int i = 0; i < SIZE_DAMIER-1; i++) {
            for (int j = 0; j < SIZE_DAMIER-1; j++) {
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
    public static boolean detectionCaptureBlanc(int[][] plateau) {
        boolean capture = false;
        boolean auMoinsUneCapture = false;
        int count=0;
        Jeu.detectionGroupesBlanc(plateau);
        for (ArrayList<Pion> groupe : listeGroupesBlanc) {
            capture = false;
            count = 0;
            for (Pion p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0)) {
                    count += 1;
                }
            }
            if ((count == groupe.size()) && (count>0)) {
                capture = true;
            }
            if (capture) {
                auMoinsUneCapture = true;
                for (Pion p : groupe) {
                    plateau[p.getX()][p.getY()] = 0;
                }
            }
        }
        return auMoinsUneCapture;
    }

    /**
     * supprime les groupes de pions noirs encerclés
     *
     * @param plateau
     */
    public static boolean detectionCaptureNoir(int[][] plateau) {
        boolean capture = false;
        boolean auMoinsUneCapture = false;
        int count=0;
        Jeu.detectionGroupesNoir(plateau);
        for (ArrayList<Pion> groupe : listeGroupesNoir) {
            capture = false;
            count = 0;
            for (Pion p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0)) {
                    count += 1;
                }
            }
            if ((count == groupe.size()) && (count>0)) {
                capture = true;
            }
            if (capture) {
                auMoinsUneCapture = true;
                for (Pion p : groupe) {
                    plateau[p.getX()][p.getY()] = 0;
                }
            }
        }
        return auMoinsUneCapture;
    }

}
