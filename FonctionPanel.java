/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour le damier 
 */
package JeuGo;

import static JeuGo.Damier.SIZE_DAMIER;
import static JeuGo.Damier.couleurPierre;
import java.awt.*;
import java.awt.event.*;

class FonctionPanel extends Panel implements ActionListener {

    public static int passer;
    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;

    //size du damier
    //public static int SIZE_DAMIER = 16;
    public int couleurPierre = 1;
    public Button btn_recommencer = new Button("Recommencer");
    public Button btn_passer = new Button("Passer");
    public Button btn_score = new Button("Scores");
    public TextField text = new TextField("Tour : Noir");
    public TextField text2 = new TextField("");
    public TextField text3 = new TextField("Pierres capturées:");
    public TextField text4 = new TextField("Noir: 0");
    public TextField text5 = new TextField("Blanc: 0");
    public TextField text6 = new TextField("Score final");
    public TextField text7 = new TextField("Noir: ");
    public TextField text8 = new TextField("Blanc :");
    public TextField text9 = new TextField("... Gagne");

    FonctionPanel() {
        setSize(140, 20 * (SIZE_DAMIER + 2));
        setLayout(null);
        setBackground(Color.WHITE);

        add(text);
        text.setBounds(20, 20, 120, 20);
        text.setEditable(false);
        add(text2);
        text2.setBounds(20, 40, 120, 20);
        text2.setEditable(false);
        add(btn_recommencer);
        btn_recommencer.setBounds(20, 80, 120, 20);
        btn_recommencer.addActionListener(this);
        add(btn_passer);
        btn_passer.setBounds(20, 120, 120, 20);
        btn_passer.addActionListener(this);
        add(text3);
        text3.setBounds(20, 160, 120, 20);
        text3.setEditable(false);
        add(text4);
        text4.setBounds(20, 180, 120, 20);
        text4.setEditable(false);
        add(text5);
        text5.setBounds(20, 200, 120, 20);
        text5.setEditable(false);
    }

    //gestion des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_recommencer) {
            passer = 0;
            GO.finPartie = false;
            GO go = new GO();
            couleurPierre = 1;
            text.setText("Tour : Noir");
            //GO.damier.setPions(GO.damier.matrice);
        } else if (e.getSource() == btn_passer) {
            passer += 1;
            if (passer == 2) {
                System.out.println("Fin de la partie");
                GO.finPartie = true;
                text.setText("Fin de la partie");
                add(btn_score);
                btn_score.setBounds(20, 240, 120, 20);
                btn_score.addActionListener(this);
            }
            GO.damier.couleurPierre = GO.damier.couleurPierre * (-1);
            if (text.getText().equals("Tour : Noir")) {
                text.setText("Tour : Blanc");
            } else if (text.getText().equals("Tour : Blanc")) {
                text.setText("Tour : Noir");
            }
        } else if (e.getSource() == btn_score) {
            GO.finPierresMortes = true;
            for (int i = 1; i < SIZE_DAMIER - 1; i++) {
                for (int j = 1; j < SIZE_DAMIER - 1; j++) {
                    GO.damier.matrice[i][j] = Jeu.remplissageDroit(GO.damier.matrice, i, j);
                }
            }
            GO.damier.setPions(GO.damier.matrice);
            add(text6);
            text6.setBounds(20, 280, 120, 20);
            text6.setEditable(false);
            add(text7);
            text7.setBounds(20, 300, 120, 20);
            text7.setEditable(false);
            double scoreNoir = Jeu.nbPierreNoir(GO.damier.matrice);
            double komi = 7.5;
            if (GO.handicapeInitial > 1) {
                komi = (GO.handicapeInitial - 1) + 0.5;
            }
            double scoreBlanc = Jeu.nbPierreBlanc(GO.damier.matrice) + komi;
            text7.setText("Noir: " + scoreNoir);
            add(text8);
            text8.setBounds(20, 320, 120, 20);
            text8.setEditable(false);
            text8.setText("Blanc: " + scoreBlanc);
            add(text9);
            text9.setBounds(20, 360, 120, 20);
            text9.setEditable(false);
            if (scoreBlanc > scoreNoir) {
                text9.setText("Blanc Gagne !");
            } else {
                text9.setText("Noir Gagne !");
            }

        }
    }
}
