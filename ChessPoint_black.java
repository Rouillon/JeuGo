/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Guoxin
 */
class ChessPoint_black extends Canvas implements MouseListener {

    ChessPad chesspad = null;

    ChessPoint_black(ChessPad p) {
        setSize(20, 20);
        chesspad = p;
        addMouseListener(this);
    }
    
    //définir la pierre
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(0, 0, 20, 20);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
            chesspad.remove(this);
            chesspad.coleurPierre = 1;
            chesspad.text_2.setText(null);
            chesspad.text_1.setText("Tour : Noir");
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() >= 2) {
            chesspad.remove(this);
        }
    }
}
