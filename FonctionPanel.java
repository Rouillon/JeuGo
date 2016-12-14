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
    public static int SIZE_DAMIER = 16;

    int couleurPierre = 1;
    Button btn_recommencer = new Button("Recommencer");
    Button btn_passer = new Button("Passer");
    TextField text = new TextField("Tour : Noir");
    TextField text2 = new TextField("");
    TextField text3 = new TextField("Pierres capturées:");
    TextField text4 = new TextField("Noir: 0");
    TextField text5 = new TextField("Blanc: 0");

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
