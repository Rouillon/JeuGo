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
    public static boolean finPartie = false;

    GO() {
        damier = new Damier();
        fonctionPanel = new FonctionPanel();
        
        setVisible(true);
        setLayout(null);
        //ajouter un title
        Label label = new Label("JeuGo", Label.CENTER);
        add(label);
        label.setBounds(70, 55, 440, 26);
        label.setBackground(Color.yellow);
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
        sc.close();
        //Commencer le jeu
        GO go = new GO();
    }
}
