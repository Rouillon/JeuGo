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
    Button button = new Button("Recommencer");
    TextField text = new TextField("Tour : Noir");

    FonctionPanel() {
        setSize(140, 20 * (SIZE_DAMIER + 2));
        setLayout(null);
        setBackground(new Color(255, 178, 102));
        add(button);
        button.setBounds(20, 10, 120, 30);
        button.addActionListener(this);
        add(text);
        text.setBounds(20, 40, 120, 60);
        text.setEditable(false);
    }

    //recommencer
    public void actionPerformed(ActionEvent e) {
        GO.damier.removeAll();
        for (int i = 0; i < SIZE_DAMIER; i++) {
            for (int j = 0; j < SIZE_DAMIER; j++) {
                GO.damier.matrice[i][j] = 0;
            }
        }
        coleurPierre = 1;
        text.setText("Tour : Noir");
    }
}
