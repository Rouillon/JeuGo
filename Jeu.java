/*
 *TP3 de MEDEV 
 *Jeu de GO
 *Classe où sont répertoriées toutes les méthodes utiles à la manipulation de la matrice du jeu
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
 *
 * @author fabienrouillon
 */
public class Jeu {

    /**
     * les attributs ci dessous sont des variables utiles aux différentes
     * méthodes de Jeu listeTestes : liste des Pions testés, sert à la
     * constitution des groupes de pions de même couleur listeGroupes : prend en
     * mémoire les groupes à un instant t
     */
    private static ArrayList<Pion2D> listeTestes;
    private static ArrayList<ArrayList<Pion2D>> listeGroupesBlanc;
    private static ArrayList<ArrayList<Pion2D>> listeGroupesNoir;
    
    // CONSTRUCTEUR PAR DEFAUT
    public static void Jeu(){
        listeTestes = new ArrayList<>();
        listeGroupesBlanc = new ArrayList<>();
        listeGroupesNoir = new ArrayList<>();
    }

    /**
     * Sert à poser une pierre noire sur le plateau, une pierre noire est
     * représentée par 1.
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @param x position abscisse
     * @param y position ordonnées
     */
    public static void poserPierreNoir(int[][] plateau, int x, int y) {
        plateau[x][y] = 1; // 1 est pour les pierres noirs
    }

    /**
     * De même que poserPierreNoir, sauf que la pierre blanche est représentée
     * par -1
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @param x position abscisse
     * @param y position ordonnées
     */
    public static void poserPierreBlanc(int[][] plateau, int x, int y) {
        plateau[x][y] = -1; //-1 est pout les pierres blancs                          
    }

    /**
     * Calcule le nombre de pierres noires sur le plateau en vue du comptage
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @return le nombre de pierres noires sur le plateau
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
     * Calcule le nombre de pierres blanches sur le plateau en vue du comptage
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @return le nombre de pierres blanches sur le plateau
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
     * A partir d'un pion P, trouve les pions adjacents de même couleur et les
     * retourne dans une liste de pions
     *
     * @param plateau la matrice qui représente le plateau de jeu
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
     * Constitue un groupe de pion à partir d'un pion de départ faisant partie d'un groupe
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @param p pion de départ de la recherche de groupe
     * @param groupe groupe qu'on va remplir avec les éléments adjacents
     * @return le groupe sous forme d'ArrayListe de Pions
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
     * Constitue tous les groupes de pions blancs du plateau à un instant donné
     *
     * @param plateau la matrice qui représente le plateau de jeu
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
     * Constitue tous les groupes de pions noirs du plateau à un instant donné
     *
     * @param plateau la matrice qui représente le plateau de jeu
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
     * Supprime les groupes de pions blanc encerclés
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @return booléen vrai si au moins un Pion capturé
     */
    public static boolean captureBlanc(int[][] plateau) {
        boolean auMoinsUneCapture = false;
        Jeu.detectionGroupesBlanc(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesBlanc) {
            boolean capture = false;
            int count = 0;
            count = groupe.stream().filter((p) -> ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0))).map((_item) -> 1).reduce(count, Integer::sum);
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }
            if (capture) {
                auMoinsUneCapture = true;
                groupe.stream().map((p) -> {
                    plateau[p.getX()][p.getY()] = 0;
                    return p;
                }).forEach((_item) -> {
                    GO.getDamier().setNbrBlancCaptures(GO.getDamier().getNbrBlancCaptures() + 1);
                });
            }
        }
        return auMoinsUneCapture;
    }

    /**
     * Supprime les groupes de pions noirs encerclés
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @return booléen vrai si au moins un Pion capturé
     */
    public static boolean captureNoir(int[][] plateau) {
        boolean auMoinsUneCapture = false;
        Jeu.detectionGroupesNoir(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesNoir) {
            boolean capture = false;
            int count = 0;
            count = groupe.stream().filter((p) -> ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0))).map((_item) -> 1).reduce(count, Integer::sum);
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }
            if (capture) {
                auMoinsUneCapture = true;
                groupe.stream().map((p) -> {
                    plateau[p.getX()][p.getY()] = 0;
                    return p;
                }).forEach((_item) -> {
                    GO.getDamier().setNbrNoirCaptures(GO.getDamier().getNbrNoirCaptures() + 1);
                });
            }
        }
        return auMoinsUneCapture;
    }

    /**
     * Détecte la capture des pions blanc sans supprimer les pions capturés
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @return booléen vrai si au moins une capture
     */
    public static boolean detectionCaptureBlanc(int[][] plateau) {
        boolean capture = false;
        Jeu.detectionGroupesBlanc(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesBlanc) {
            int count = 0;
            count = groupe.stream().filter((p) -> ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0))).map((_item) -> 1).reduce(count, Integer::sum);
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }

        }
        return capture;
    }

    /**
     * Détecte la capture des pions noirs sans supprimer les pions capturés
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @return booléen vrai si au moins une capture
     */
    public static boolean detectionCaptureNoir(int[][] plateau) {
        boolean capture = false;
        Jeu.detectionGroupesNoir(plateau);
        for (ArrayList<Pion2D> groupe : listeGroupesNoir) {
            int count = 0;
            count = groupe.stream().filter((p) -> ((plateau[p.getX()][p.getY() + 1] != 0) && (plateau[p.getX()][p.getY() - 1] != 0) && (plateau[p.getX() + 1][p.getY()] != 0) && (plateau[p.getX() - 1][p.getY()] != 0))).map((_item) -> 1).reduce(count, Integer::sum);
            if ((count == groupe.size()) && (count > 0)) {
                capture = true;
            }
        }
        return capture;
    }

    /**
     * Remplit les zones vides du plateau par des pions de la même couleur que
     * les pions du Joueur qui possède le territoire sur lequel ce trouve la
     * zone passé en argument. Utilise les méthodes remplissageGauche,
     * remplissageInverseDroit et remplissageInverseGauche pour balayer
     * l'ensemble du plateau récursivement
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @param i abscisse du Pion pris en argument
     * @param j ordonnée du Pion pris en argument
     * @return
     */
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

    /**
     * Utilisé par la méthode remplissageDroit pour balayer l'ensemble du
     * plateau récursivement
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @param i abscisse du Pion pris en argument
     * @param j ordonnée du Pion pris en argument
     * @return
     */
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

    /**
     * Utilisé par la méthode remplissageDroit pour balayer l'ensemble du
     * plateau récursivement
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @param i abscisse du Pion pris en argument
     * @param j ordonnée du Pion pris en argument
     * @return
     */
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

    /**
     * Utilisé par la méthode remplissageDroit pour balayer l'ensemble du
     * plateau récursivement
     *
     * @param plateau la matrice qui représente le plateau de jeu
     * @param i abscisse du Pion pris en argument
     * @param j ordonnée du Pion pris en argument
     * @return
     */
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

    /**
     * Gère la sauvegarde de la partie en cours dans un fichier en ajoutant tour
     * par tour les données contenu dans le plateau
     *
     * @param matrice représente le tableau des pions
     * @param fichier contenant les données sauvegardées
     * @param tour le tour que l'on va enregistrer dans fichier
     */
    public static void sauvegardeMatrice(int[][] matrice, String fichier, int tour) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fichier, true))) {
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
            bufferedWriter.write("Couleur " + GO.getDamier().getCouleurPierre());
            bufferedWriter.newLine();
        } catch (IOException e) {
        }

    }

    /**
     * Gère le chargement du plateau de jeu correspondant à un tour donné d'une
     * partie sauvegardée
     *
     * @param source le fichier contenant la sauvegarde à charger
     * @param tour le tour que l'on souhaite charger
     */
    public static void chargerMatrice(String source, int tour) {

        try (BufferedReader fichier = new BufferedReader(new FileReader(source))) {
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
                    st.nextToken();
                    b = Integer.parseInt(st.nextToken());
                    GO.getDamier().setNbrBlancCaptures(b);
                    GO.getFonctionPanel().getText5().setText("Blanc: " + GO.getDamier().getNbrBlancCaptures());
                    ligne = fichier.readLine();
                    st = new StringTokenizer(ligne);
                    st.nextToken();
                    b = Integer.parseInt(st.nextToken());
                    GO.getDamier().setNbrNoirCaptures(b);
                    GO.getFonctionPanel().getText4().setText("Noir: " + GO.getDamier().getNbrNoirCaptures());
                    ligne = fichier.readLine();
                    st = new StringTokenizer(ligne);
                    st.nextToken();
                    b = Integer.parseInt(st.nextToken());
                    GO.getDamier().setCouleurPierre(b);
                    if (b == -1) {
                        GO.getFonctionPanel().getText().setText("Tour : Blanc");
                    } else {
                        GO.getFonctionPanel().getText().setText("Tour : Noir");
                    }

                }
                ligne = fichier.readLine();
            }

        } catch (Exception e) {
        }
    }

    /**
     * Retourne le nombre de tours qui ont été joué dans une partie
     *
     * @param source contient la partie sauvegardée
     * @return le nombre de tours joués dans la partie
     */
    public static int nbTours(String source) {
        int result = 0;
        try (BufferedReader fichier = new BufferedReader(new FileReader(source))) {
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
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Retourne la taille du plateau de jeu d'une partie sauvegardée
     *
     * @param source contient la partie sauvegardée
     * @return la taille du plateau
     */
    public static int taille(String source) {
        int result = 0;
        try (BufferedReader fichier = new BufferedReader(new FileReader(source))) {
            String ligne = fichier.readLine();
            StringTokenizer st = new StringTokenizer(ligne);
            String a = st.nextToken();
            result = Integer.parseInt(st.nextToken());
        } catch (Exception e) {
            System.out.println("\nAucune partie n'a été trouvée...");
        }
        return result;
    }

}
