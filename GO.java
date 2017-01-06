/*
 *TP3 de MEDEV 
 *Jeu de GO
 *Classe principale et main
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principale qui génère le damier et les éléments du jeu
 *
 * @author Guoxin
 */
public class GO extends Frame {

    // L'attribut fichier porte le nom du fichier de sauvegarde, on peut éventuellement demandé à l'utilisateur
    // de choisir le nom du fichier pour créer plusieurs sauvegardes
    private final static String FICHIER = "PartieGO.txt";
    // Attribut qui permet de compter le nombre de tours joués
    private static int tour;
    private static Damier damier;
    private static FonctionPanel fonctionPanel;
    // Attribut vrai lorsque la partie est terminée
    private static boolean finPartie;
    // Attribut vrai lorsque le joueur a fini d'éliminer les pierres mortes à la fin du jeu
    private static boolean finPierresMortes;
    // Attribut qui vaux le nombre de pierres de handicape restant à poser
    private static int handicape;
    // Attribut qui vaux le nombre de pierres de handicape choisies
    private static int handicapeInitial;
    // Cet attribut s'incrémente lorsque un joueur passe et reviens à 0 si le joueur suivant passe.
    // Lorsque 'passer' vaux 2 la partie est terminée
    private static int passer;
    // Attribut vrai lorsqu'une partie est en train d'être chargée
    private static boolean chargement;
    // Attribut valant le nombre du tour en cours de chargement
    private static int tourCharge;
    // NOmbre de tours que comporte une partie chargée
    private static int nbTours;

    // GETTERS ET SETTERS
    public static int getNbTours() {
        return nbTours;
    }

    public static void setNbTours(int nbTours) {
        GO.nbTours = nbTours;
    }

    public static int getTourCharge() {
        return tourCharge;
    }

    public static void setTourCharge(int tourCharge) {
        GO.tourCharge = tourCharge;
    }

    public static boolean isChargement() {
        return chargement;
    }

    public static void setChargement(boolean chargement) {
        GO.chargement = chargement;
    }

    public static String getFICHIER() {
        return FICHIER;
    }

    public static int getTour() {
        return tour;
    }

    public static void setTour(int tour) {
        GO.tour = tour;
    }

    public static Damier getDamier() {
        return damier;
    }

    public static void setDamier(Damier damier) {
        GO.damier = damier;
    }

    public static FonctionPanel getFonctionPanel() {
        return fonctionPanel;
    }

    public static void setFonctionPanel(FonctionPanel fonctionPanel) {
        GO.fonctionPanel = fonctionPanel;
    }

    public static boolean isFinPartie() {
        return finPartie;
    }

    public static void setFinPartie(boolean finPartie) {
        GO.finPartie = finPartie;
    }

    public static boolean isFinPierresMortes() {
        return finPierresMortes;
    }

    public static void setFinPierresMortes(boolean finPierresMortes) {
        GO.finPierresMortes = finPierresMortes;
    }

    public static int getHandicape() {
        return handicape;
    }

    public static void setHandicape(int handicape) {
        GO.handicape = handicape;
    }

    public static int getHandicapeInitial() {
        return handicapeInitial;
    }

    public static void setHandicapeInitial(int handicapeInitial) {
        GO.handicapeInitial = handicapeInitial;
    }

    public static int getPasser() {
        return passer;
    }

    public static void setPasser(int passer) {
        GO.passer = passer;
    }

    // CONSTRUCTEUR PAR DEFAUT
    GO() {
        tour = 1;
        finPartie = false;
        finPierresMortes = false;
        chargement = false;
        damier = new Damier();
        fonctionPanel = new FonctionPanel();
        passer = 0;
        damier.setNbrBlancCaptures(0);
        damier.setNbrNoirCaptures(0);
        tourCharge = 1;
        setVisible(true);
        setLayout(null);
        // Ajouter un titre
        Label label = new Label("JEU DE GO", Label.CENTER);
        add(label);
        label.setBounds(70, 55, 440, 26);
        label.setBackground(Color.pink);
        // Ajouter le damier
        add(damier);
        damier.setBounds(70, 90, 440, 440);
        add(fonctionPanel);
        fonctionPanel.setBounds(520, 90, 200, 440);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        pack();
        setSize(700, 550);
    }

    // MAIN :
    public static void main(String args[]) {
        damier = new Damier();
        System.out.println("NB: Lorsque la partie est finie, cliquez sur les pierres mortes s'il y en a \n"
                + "puis une fois cette étape terminée, cliquez sur le bouton 'Score' pour afficher le nom du gagnant");
        // Choix entre nouvelle partie et rejouer la partie précédente
        Scanner sc = new Scanner(System.in);
        String choixAction;
        do {
            System.out.println("\n\nTaper 'n' pour jouer une nouvelle partie, "
                    + "'r' pour rejouer une partie non terminée");
            choixAction = sc.nextLine();
        } while (!"r".equals(choixAction) && !"n".equals(choixAction));
        //Choix de la taille du jeu
        if ("n".equals(choixAction)) {
            do {
                System.out.println("\n\nChoisir la taille du goban: taper 9, 16 ou 19");
                choixAction = sc.nextLine();
            } while (!"9".equals(choixAction) && !"16".equals(choixAction) && !"19".equals(choixAction));
            switch (choixAction) {
                case "9":
                    damier.setSIZE_DAMIER(11);
                    break;
                case "16":
                    damier.setSIZE_DAMIER(18);
                    break;
                case "19":
                    damier.setSIZE_DAMIER(21);
                    break;
                default:
                    break;
            }
            // Choix du handicape
            System.out.println("\nCombien voulez-vous de pierres de handicape?");
            System.out.println("Taper 0 (ou 1) si vous ne voulez pas de handicape");
            System.out.println("Handicap maximal: 4 pour un goban 9x9 / 8 pour un goban 16x16 / 9 pour un goban 19x19");
            System.out.println("Le komi est de 7,5 pour 0 pierre de handicape et de n-0,5 pour n pierres de handicape");
            handicape = sc.nextInt();
            handicapeInitial = handicape;
            if ((damier.getSIZE_DAMIER() == 21) && (handicape > 9)) {
                handicape = 9;
            }
            if ((damier.getSIZE_DAMIER() == 18) && (handicape > 8)) {
                handicape = 8;
            }
            if ((damier.getSIZE_DAMIER() == 11) && (handicape > 4)) {
                handicape = 4;
            }
            if (handicape < 0) {
                handicape = 0;
            }
            sc.close();
            //Commencer le jeu
            GO go = new GO();
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(FICHIER));
                bufferedWriter.write("");
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(GO.class.getName()).log(Level.SEVERE, null, ex);
            } 
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(FICHIER, true));
                bufferedWriter.write("Taille " + damier.getSIZE_DAMIER());
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(GO.class.getName()).log(Level.SEVERE, null, ex);
            } 
            // Partie rejouée:
        } else {
            System.out.println("Vous pouvez maintenant rejouer cette partie à partir du coup que vous souhaitez");
            System.out.println("Cliquez sur 'Suivant' ou 'Précédent' pour retourner à un moment de la partie puis cliquez sur 'Jouer'");
            sc.close();
            damier.setSIZE_DAMIER(Jeu.taille(FICHIER));
            GO go = new GO();
            chargement = true;
            nbTours = Jeu.nbTours(FICHIER);
            Jeu.chargerMatrice(FICHIER, tourCharge);
            damier.setPions(damier.getMatrice());
            fonctionPanel.add(GO.fonctionPanel.getBtn_suivant());
            GO.fonctionPanel.getBtn_suivant().setBounds(20, 240, 120, 20);
            GO.fonctionPanel.getBtn_suivant().addActionListener(GO.fonctionPanel);

            fonctionPanel.add(GO.fonctionPanel.getBtn_precedent());
            GO.fonctionPanel.getBtn_precedent().setBounds(20, 280, 120, 20);
            GO.fonctionPanel.getBtn_precedent().addActionListener(GO.fonctionPanel);

            fonctionPanel.add(GO.fonctionPanel.getBtn_jouer());
            GO.fonctionPanel.getBtn_jouer().setBounds(20, 320, 120, 20);
            GO.fonctionPanel.getBtn_jouer().addActionListener(GO.fonctionPanel);
        }

    }
}
