/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour faire appraitre le frame 
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

/**
 * classe pour le frame
 *
 * @author Guoxin
 */
public class GO extends Frame {

    public static Damier damier;
    public static FonctionPanel fonctionPanel;
    public static boolean finPartie;
    public static int handicape;

    GO() {
        finPartie = false;
        damier = new Damier();
        fonctionPanel = new FonctionPanel();
        FonctionPanel.passer = 0;
        Damier.nbrBlancCaptures = 0;
        Damier.nbrNoirCaptures = 0;

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
        //Choix de la taille du jeu
        Scanner sc = new Scanner(System.in);
        String choixAction;
        do {
            System.out.println("Choisir la taille du goban: taper 9, 16 ou 19");
            choixAction = sc.nextLine();
        } while (!choixAction.equals("9") && !choixAction.equals("16") && !choixAction.equals("19"));
        switch (choixAction) {
            case "9":
                Damier.SIZE_DAMIER = 10;
                break;
            case "16":
                Damier.SIZE_DAMIER = 17;
                break;
            case "19":
                Damier.SIZE_DAMIER = 20;
                break;
            default:
                break;
        }

        System.out.println("\nCombien voulez-vous de pierres de handicape?");
        System.out.println("Taper 0 (ou 1) si vous ne voulez pas de handicape");
        System.out.println("Handicap maximal: 4 pour un goban 9x9 / 8 pour un goban 16x16 / 9 pour un goban 19x19");
        handicape = sc.nextInt();
        if ((Damier.SIZE_DAMIER == 20) && (handicape > 9)) {
            handicape = 9;
        }
        if ((Damier.SIZE_DAMIER == 17) && (handicape > 8)) {
            handicape = 8;
        }
        if ((Damier.SIZE_DAMIER == 10) && (handicape > 4)) {
            handicape = 4;
        }
        if (handicape < 0) {
            handicape = 0;
        }
        sc.close();
        //Commencer le jeu
        GO go = new GO();
    }
}
