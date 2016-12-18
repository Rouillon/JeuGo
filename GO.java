/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour faire appraitre le frame 
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
 * classe pour le frame
 *
 * @author Guoxin
 */
public class GO extends Frame {
    
    private static String fichier;
    private static int tour;
    
    private static Damier damier;
    private static FonctionPanel fonctionPanel;
    private static boolean finPartie;
    private static boolean finPierresMortes;
    private static int handicape;
    private static int handicapeInitial;
    private static int passer;
    
    public static String getFichier() {
        return fichier;
    }
    
    public static void setFichier(String fichier) {
        GO.fichier = fichier;
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
    
    GO() {
        tour = 1;
        fichier = "PartieGO.txt";
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fichier));
            bufferedWriter.write("");
        } catch (IOException ex) {
            Logger.getLogger(GO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finPartie = false;
        finPierresMortes = false;
        damier = new Damier();
        fonctionPanel = new FonctionPanel();
        passer = 0;
        damier.setNbrBlancCaptures(0);
        damier.setNbrNoirCaptures(0);
        
        setVisible(true);
        setLayout(null);
        //ajouter un title
        Label label = new Label("JEU DE GO", Label.CENTER);
        add(label);
        label.setBounds(70, 55, 440, 26);
        label.setBackground(Color.pink);
        //ajouter le damier
        add(damier);
        damier.setBounds(70, 90, 440, 440);
        add(fonctionPanel);
        fonctionPanel.setBounds(520, 90, 200, 440);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        pack();
        setSize(700, 550);
    }
    
    public static void main(String args[]) {
        damier = new Damier();
        System.out.println("NB: Lorsque la partie est finie, cliquez sur les pierres mortes s'il y en a \n"
                + "puis une fois cette étape terminée, cliquez sur le bouton 'Score' pour afficher le nom du gagnant");
        //Choix de la taille du jeu
        Scanner sc = new Scanner(System.in);
        String choixAction;
        do {
            System.out.println("\n\nTaper 'n' pour jouer une nouvelle partie, "
                    + "'c' pour charger une partie non terminée");
            choixAction = sc.nextLine();
        } while (!choixAction.equals("c") && !choixAction.equals("n"));
        if (choixAction.equals("n")) {
            do {
                System.out.println("\n\nChoisir la taille du goban: taper 9, 16 ou 19");
                choixAction = sc.nextLine();
            } while (!choixAction.equals("9") && !choixAction.equals("16") && !choixAction.equals("19"));
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
        }
        else {
            
        }
        sc.close();
        //Commencer le jeu
        GO go = new GO();
        Jeu.chargerMatrice("test.txt", 9);
        damier.setPions(damier.getMatrice());
        System.out.println(Jeu.nbTours("test.txt"));
    }
}
