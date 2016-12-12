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
    
    

    Damier damier = new Damier();

    GO() {
        setVisible(true);
        setLayout(null);
        //ajouter un title
        Label label = new Label("JeuGo", Label.CENTER);
        add(label);
        label.setBounds(70, 55, 440, 26);
        label.setBackground(Color.yellow);
        //ajouter le damier
        add(damier);
        damier.setBounds(70, 90, 440, 440);
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
        //commencer le jeu
        GO go = new GO();
    }
}
