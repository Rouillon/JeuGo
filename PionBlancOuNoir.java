/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

/**
 *
 * @author fabienrouillon
 */
import static JeuGo.Damier.SIZE_DAMIER;
import java.awt.*;
import java.awt.event.*;

/**
 * classe du pion blanc
 *
 * @author Guoxin
 */
abstract class PionBlancOuNoir extends Canvas implements MouseListener {

    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;
    public int posx = -1;
    public int posy = -1;

    PionBlancOuNoir(int x,int y) {
        setSize(20, 20);
        addMouseListener(this);
        this.posx=x;
        this.posy=y;
    }

    
    
    

    //définir la pierre
    public abstract void paint(Graphics g);

    public void mousePressed(MouseEvent e) {
        if ((GO.finPartie) && (!GO.finPierresMortes)) {
            if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                GO.damier.matrice[this.posx][this.posy]=0;
                GO.damier.setPions(GO.damier.matrice);
            }
        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
