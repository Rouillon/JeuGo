/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour le damier 
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;

class FonctionPanel extends Panel implements ActionListener {

    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;

    //size du damier
    public static int SIZE_DAMIER = 16;

    int coleurPierre = 1;
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

    //recommencer
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_recommencer) {
            GO.damier.removeAll();
            for (int i = 0; i < SIZE_DAMIER; i++) {
                for (int j = 0; j < SIZE_DAMIER; j++) {
                    GO.damier.matrice[i][j] = 0;
                }
            }
            coleurPierre = 1;
            text.setText("Tour : Noir");
        }else if (e.getSource() == btn_passer) {
            GO.damier.coleurPierre =GO.damier.coleurPierre * (-1);
            if(text.getText().equals("Tour : Noir"))
                text.setText("Tour : Blanc");
            else if(text.getText().equals("Tour : Blanc"))
                text.setText("Tour : Noir");                     
        }
    }

}
