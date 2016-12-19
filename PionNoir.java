/*
 *TP3 de MEDEV 
 *Jeu de GO
 *Classe pour les pions noirs 
 */
package JeuGo;

import java.awt.*;

/**
 * Classe du pion noir
 *
 * @author Guoxin
 */
class PionNoir extends PionBlancOuNoir {

    // CONSTRUCTEUR
    PionNoir(int x, int y) {
        super(x, y);
    }

    // Dessiner la pierre
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(0, 0, 20, 20);
    }

}
