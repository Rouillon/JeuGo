/*
*TP3 de MEDEV 
*Jeu de GO
 */
package JeuGo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * Ici se trouve les fonctions permettant de gérer le fonctionnement du jeu hors
 * affichage
 */
public class Jeu {

    /**
     * les attributs ci dessous sont des variables utiles aux différentes
     * méthodes de Jeu listeTestes : liste des Pions testés, sert à la
     * constitution de groupes listeGroupes : prend en mémoire les groupes à un
     * instant t
     */
    private static ArrayList<Pion2D> listeTestes;
    private static ArrayList<ArrayList<Pion2D>> listeGroupesBlanc;
    private static ArrayList<ArrayList<Pion2D>> listeGroupesNoir;

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
        for (int i = 0; i < GO.getDamier().getSIZE_DAMIER() - 1; i++) {
            for (int j = 0; j < GO.getDamier().getSIZE_DAMIER() - 1; j++) {
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
        for (int i = 0; i < GO.getDamier().getSIZE_DAMIER() - 1; i++) {
            for (int j = 0; j < GO.getDamier().getSIZE_DAMIER() - 1; j++) {
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
    public static LinkedList<Pion2D> adjacents(int[][] plateau, Pion2D p) {
        int px = p.getX();
        int py = p.getY();
        int couleur = p.getCouleur();
        LinkedList<Pion2D> adjacents = new LinkedList<>();
        if (plateau[px - 1][py] == couleur) {
            adjacents.add(new Pion2D(px - 1, py, couleur));
        }
        if (plateau[px + 1][py] == couleur) {
            adjacents.add(new Pion2D(px + 1, py, couleur));
        }
        if (plateau[px][py - 1] == couleur) {
            adjacents.add(new Pion2D(px, py - 1, couleur));
        }
        if (plateau[px][py + 1] == couleur) {
            adjacents.add(new Pion2D(px, py + 1, couleur));
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
    public static ArrayList<Pion2D> ajouterAdjacent(int[][] plateau, Pion2D p, ArrayList<Pion2D> groupe) {
        groupe.add(p);
        listeTestes.add(p);
        LinkedList<Pion2D> adjacents = adjacents(plateau, p);
        boolean test2;
        for (Pion2D adja : adjacents) {
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
        for (int i = 0; i < GO.getDamier().getSIZE_DAMIER() - 1; i++) {
            for (int j = 0; j < GO.getDamier().getSIZE_DAMIER() - 1; j++) {
                //blanc
                if (plateau[i][j] == -1) {
                    Pion2D p = new Pion2D(i, j, -1);
                    boolean test = false;
                    for (Pion2D pp : listeTestes) {
                        if (pp.equals(p)) {
                            test = true;
                        }
                    }
                    if (!test) {
                        ArrayList<Pion2D> nouveau = new ArrayList<>();
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
        for (int i = 0; i < GO.getDamier().getSIZE_DAMIER() - 1; i++) {
            for (int j = 0; j < GO.getDamier().getSIZE_DAMIER() - 1; j++) {
                //noir
                if (plateau[i][j] == 1) {
                    Pion2D p = new Pion2D(i, j, 1);
                    boolean test = false;
                    for (Pion2D pp : listeTestes) {
                        if (pp.equals(p)) {
                            test = true;
                        }
                    }
                    if (!test) {
                        ArrayList<Pion2D> nouveau = new ArrayList<>();
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
    public static boolean CaptureBlanc(int[][] plateau) {
        boolean capture = false;
        boolean auMoinsUneCapture = false;
        int count = 0;
        Jeu.detectionGroupesBlanc(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesBlanc) {
            capture = false;
            count = 0;
            for (Pion2D p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0)) {
                    count += 1;
                }
            }
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }
            if (capture) {
                auMoinsUneCapture = true;
                for (Pion2D p : groupe) {
                    plateau[p.getX()][p.getY()] = 0;
                    GO.getDamier().setNbrBlancCaptures(GO.getDamier().getNbrBlancCaptures() + 1);
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
    public static boolean CaptureNoir(int[][] plateau) {
        boolean capture = false;
        boolean auMoinsUneCapture = false;
        int count = 0;
        Jeu.detectionGroupesNoir(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesNoir) {
            capture = false;
            count = 0;
            for (Pion2D p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0)) {
                    count += 1;
                }
            }
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }
            if (capture) {
                auMoinsUneCapture = true;
                for (Pion2D p : groupe) {
                    plateau[p.getX()][p.getY()] = 0;
                    GO.getDamier().setNbrNoirCaptures(GO.getDamier().getNbrNoirCaptures() + 1);
                }
            }
        }
        return auMoinsUneCapture;
    }

    /**
     * détecte la capture des pions blanc sans supprimer les pions capturés
     *
     * @param plateau
     */
    public static boolean detectionCaptureBlanc(int[][] plateau) {
        boolean capture = false;
        int count = 0;
        Jeu.detectionGroupesBlanc(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesBlanc) {
            count = 0;
            for (Pion2D p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0)) {
                    count += 1;
                }
            }
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }

        }
        return capture;
    }

    /**
     * détecte la capture des pions noirs sans supprimer les pions capturés
     *
     * @param plateau
     */
    public static boolean detectionCaptureNoir(int[][] plateau) {
        boolean capture = false;
        int count = 0;
        Jeu.detectionGroupesNoir(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesNoir) {
            count = 0;
            for (Pion2D p : groupe) {
                if ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0)) {
                    count += 1;
                }
            }
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }
        }
        return capture;
    }

    public static int remplissageDroit(int[][] plateau, int i, int j) {
        if (plateau[i][j] == -1) {
            return -1;
        } else if (plateau[i][j] == 1) {
            return 1;
        } else if (j == (GO.getDamier().getSIZE_DAMIER() - 2)) {

            if (i == (GO.getDamier().getSIZE_DAMIER() - 2)) {
                return remplissageInverseGauche(plateau, i, j - 1);
            } else {
                return remplissageGauche(plateau, i + 1, j);
            }

        } else {
            return remplissageDroit(plateau, i, j + 1);
        }
    }

    public static int remplissageGauche(int[][] plateau, int i, int j) {
        if (plateau[i][j] == -1) {
            return -1;
        } else if (plateau[i][j] == 1) {
            return 1;
        } else if (j == 1) {

            if (i == (GO.getDamier().getSIZE_DAMIER() - 2)) {
                return remplissageInverseDroit(plateau, i, j + 1);
            } else {
                return remplissageDroit(plateau, i + 1, j);
            }

        } else {
            return remplissageGauche(plateau, i, j - 1);
        }
    }

    public static int remplissageInverseGauche(int[][] plateau, int i, int j) {
        if (plateau[i][j] == -1) {
            return -1;
        } else if (plateau[i][j] == 1) {
            return 1;
        } else if (j == 1) {

            if (i == 1) {
                return 0;
            } else {
                return remplissageInverseDroit(plateau, i - 1, j);
            }

        } else {
            return remplissageInverseGauche(plateau, i, j - 1);
        }
    }

    public static int remplissageInverseDroit(int[][] plateau, int i, int j) {
        if (plateau[i][j] == -1) {
            return -1;
        } else if (plateau[i][j] == 1) {
            return 1;
        } else if (j == (GO.getDamier().getSIZE_DAMIER() - 2)) {

            if (i == 1) {
                return 0;
            } else {
                return remplissageInverseGauche(plateau, i - 1, j);
            }

        } else {
            return remplissageInverseDroit(plateau, i, j + 1);
        }
    }

    public static void sauvegardeMatrice(int[][] matrice, String fichier, int tour) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fichier, true));
            bufferedWriter.write("Tour " + tour);
            bufferedWriter.newLine();
            for (int i = 0; i < GO.getDamier().getSIZE_DAMIER(); i++) {
                for (int j = 0; j < GO.getDamier().getSIZE_DAMIER(); j++) {
                    bufferedWriter.write(" " + matrice[i][j] + " ");
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.write("Blanc " + GO.getDamier().getNbrBlancCaptures());
            bufferedWriter.newLine();
            bufferedWriter.write("Noir " + GO.getDamier().getNbrNoirCaptures());
            bufferedWriter.newLine();
            bufferedWriter.write("Couleur "+ GO.getDamier().getCouleurPierre());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
        }

    }

    public static void chargerMatrice(String source, int tour) {

        try {
            BufferedReader fichier = new BufferedReader(new FileReader(source));
            String ligne = fichier.readLine();
            StringTokenizer st;
            String a;

            while (ligne != null) {
                st = new StringTokenizer(ligne);
                a = st.nextToken();
                int b = Integer.parseInt(st.nextToken());
                if ((a.equals("Tour")) && (b == tour)) {
                    for (int i = 0; i < GO.getDamier().getSIZE_DAMIER(); i++) {
                        ligne = fichier.readLine();
                        st = new StringTokenizer(ligne);
                        for (int j = 0; j < GO.getDamier().getSIZE_DAMIER(); j++) {
                            GO.getDamier().setMatrice(Integer.parseInt(st.nextToken()), i, j);
                        }
                    }
                    ligne = fichier.readLine();
                    st = new StringTokenizer(ligne);
                    a = st.nextToken();
                    b = Integer.parseInt(st.nextToken());
                    GO.getDamier().setNbrBlancCaptures(b);
                    GO.getFonctionPanel().getText5().setText("Blanc: " + GO.getDamier().getNbrBlancCaptures());
                    ligne = fichier.readLine();
                    st = new StringTokenizer(ligne);
                    a = st.nextToken();
                    b = Integer.parseInt(st.nextToken());
                    GO.getDamier().setNbrNoirCaptures(b);
                    GO.getFonctionPanel().getText4().setText("Noir: " + GO.getDamier().getNbrNoirCaptures());
                    ligne = fichier.readLine();
                    st = new StringTokenizer(ligne);
                    a = st.nextToken();
                    b = Integer.parseInt(st.nextToken());
                    GO.getDamier().setCouleurPierre(b);
                    if (b==-1) {
                        GO.getFonctionPanel().getText().setText("Tour : Blanc");
                    }
                    else {
                        GO.getFonctionPanel().getText().setText("Tour : Noir");
                    }
                    
                }
                ligne = fichier.readLine();
            }
            fichier.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int nbTours(String source) {
        int result = 0;
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(source));
            String ligne = fichier.readLine();
            StringTokenizer st;
            String a;

            while (ligne != null) {
                st = new StringTokenizer(ligne);
                a = st.nextToken();
                int b = Integer.parseInt(st.nextToken());
                if (a.equals("Tour")) {
                    result = b;
                }
                ligne = fichier.readLine();
            }
            fichier.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static int taille(String source) {
        int result=0;
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(source));
            String ligne = fichier.readLine();
            StringTokenizer st= new StringTokenizer(ligne);;
            String a= st.nextToken();
            result = Integer.parseInt(st.nextToken());
            fichier.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
