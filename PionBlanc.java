/*
 *TP3 de MEDEV 
 *Jeu de GO
 *Classe pour les pions blancs
 */
package JeuGo;

import java.awt.*;

/**
 * Classe du pion blanc
 *
 * @author Guoxin
 */
class PionBlanc extends PionBlancOuNoir {

    // CONSTUCTEUR
    PionBlanc(int x, int y) {
        super(x, y);
    }

    // Dessiner la pierre
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(0, 0, 20, 20);
    }
}
