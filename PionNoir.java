/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour le pion noir 
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;

/**
 * classe du pion noir
 * @author Guoxin
 */
class PionNoir extends Canvas {
    
    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;

    Damier damier = null;

    PionNoir(Damier d) {
        setSize(20, 20);
        damier = d;
    }
    
    //définir la pierre
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(0, 0, 20, 20);
    }

}
