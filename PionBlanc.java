/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour pion blanc 
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;

/**
 * classe du pion blanc
 *
 * @author Guoxin
 */
class PionBlanc extends Canvas {

    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;

    Damier damier = null;

    PionBlanc(Damier d) {
        setSize(20, 20);
        damier = d;
    }

    //définir la pierre
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(0, 0, 20, 20);
    }

}
