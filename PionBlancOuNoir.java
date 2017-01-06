/*
 *TP3 de MEDEV 
 *Jeu de GO
 *Classe pour les pions sous forme graphiques
 */
package JeuGo;

/**
 *
 * @author fabienrouillon
 */
import java.awt.*;
import java.awt.event.*;

/**
 * Super classe de PionBlanc et PionNoir
 *
 * @author Guoxin
 */
abstract class PionBlancOuNoir extends Canvas implements MouseListener {

    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;
    public int posx = -1;
    public int posy = -1;

    // CONSTRUCTEUR
    PionBlancOuNoir(int x, int y) {
        setSize(20, 20);
        addMouseListener(this);
        this.posx = x;
        this.posy = y;
    }

    //définir la pierre
    @Override
    public abstract void paint(Graphics g);

    @Override
    public void mousePressed(MouseEvent e) {
        if ((GO.isFinPartie()) && (!GO.isFinPierresMortes())) {
            if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                GO.getDamier().setMatrice(0, this.posx, this.posy);
                GO.getDamier().setPions(GO.getDamier().getMatrice());
            }
        }

    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

}
