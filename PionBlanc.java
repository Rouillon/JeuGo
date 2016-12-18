/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour pion blanc 
 */
package JeuGo;

import java.awt.*;

/**
 * classe du pion blanc
 *
 * @author Guoxin
 */
class PionBlanc extends PionBlancOuNoir {
    
    PionBlanc(int x, int y) {
        super(x,y);
    }
    
    //définir la pierre
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(0, 0, 20, 20);
    }
}



