/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;

/**
 * classe pour le frame
 * @author Guoxin
 */

public class GO extends Frame {

    ChessPad chesspad = new ChessPad();

    GO() {
        setVisible(true);
        setLayout(null);
        Label label = new Label("JeuGo", Label.CENTER);
        add(label);
        label.setBounds(70, 55, 440, 26);
        label.setBackground(Color.yellow);
        add(chesspad);
        chesspad.setBounds(70, 90, 440, 440);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        pack();
        setSize(600, 550);
    }

    public static void main(String args[]) {
        GO go = new GO();
    }
}
