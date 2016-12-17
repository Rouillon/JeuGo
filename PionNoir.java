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
 *
 * @author Guoxin
 */
class PionNoir extends PionBlancOuNoir {

    PionNoir(int x, int y) {
        super(x,y);
    }

    //définir la pierre
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(0, 0, 20, 20);
    }

}
