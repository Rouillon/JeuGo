/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour le damier 
 */
package JeuGo;

import static JeuGo.Damier.couleurPierre;
import java.awt.*;
import java.awt.event.*;

class FonctionPanel extends Panel implements ActionListener {

    public static int passer = 0;
    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;

    //size du damier
    public static int SIZE_DAMIER = 16;

    int couleurPierre = 1;
    Button btn_recommencer = new Button("Recommencer");
    Button btn_passer = new Button("Passer");
    TextField text = new TextField("Tour : Noir");

    FonctionPanel() {
        setSize(140, 20 * (SIZE_DAMIER + 2));
        setLayout(null);
        setBackground(Color.WHITE);

        add(text);
        text.setBounds(20, 20, 120, 20);
        text.setEditable(false);
        add(btn_recommencer);
        btn_recommencer.setBounds(20, 60, 120, 20);
        btn_recommencer.addActionListener(this);

        add(btn_passer);
        btn_passer.setBounds(20, 100, 120, 20);
        btn_passer.addActionListener(this);
    }

    //gestion des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_recommencer) {
            passer += 0;
            GO.finPartie = false;
            GO.damier.removeAll();
            for (int i = 0; i < SIZE_DAMIER; i++) {
                for (int j = 0; j < SIZE_DAMIER; j++) {
                    GO.damier.matrice[i][j] = 0;
                }
            }
            couleurPierre = 1;
            text.setText("Tour : Noir");
        } else if (e.getSource() == btn_passer) {
            passer += 1;
            if (passer == 2) {
                System.out.println("Fin de la partie");
                GO.finPartie = true;
                GO.fonctionPanel.text.setText("Fin de la partie");
            }
            GO.damier.couleurPierre = GO.damier.couleurPierre * (-1);
            if (text.getText().equals("Tour : Noir")) {
                text.setText("Tour : Blanc");
            } else if (text.getText().equals("Tour : Blanc")) {
                text.setText("Tour : Noir");
            }
        }
    }

}

//                    do {
//                        FonctionPanel.passer = 0;
//                        //poser un pion noir
//                        Jeu.poserPierreNoir(matrice, b, a);
//                        GO.suicide = false;
//                        if (Jeu.detectionCaptureNoir(matrice)) {
//                            GO.suicide = true;
//                            GO.fonctionPanel.text.setText("Blanc: Suicide Interdit !");
//                        }
//                    } while (GO.suicide = true);
//                    Jeu.detectionCaptureBlanc(matrice);
//                    setPions(matrice);
//                    couleurPierre = couleurPierre * (-1);
//                    GO.fonctionPanel.text.setText("Tour : Blanc");
//                    printMatrice();
