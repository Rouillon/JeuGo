/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour le damier 
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;

class Damier extends Panel implements MouseListener {

    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;
    //size du damier
    public static int SIZE_DAMIER = 16;
    //matric des pions
    int[][] matrice = new int[SIZE_DAMIER][SIZE_DAMIER];
    //la position de la souris sur l'écran
    int x = -1;
    int y = -1;
    public static int couleurPierre = 1;
    Jeu jeu;

    Damier() {
        setSize(20 * (SIZE_DAMIER + 2), 20 * (SIZE_DAMIER + 2));
        setLayout(null);
        setBackground(new Color(255, 178, 102));
        addMouseListener(this);
        jeu = new Jeu();
    }

    //dessiner le damier 
    public void paint(Graphics g) {
        for (int i = 40; i <= 20 * (SIZE_DAMIER + 1); i += 20) {
            g.drawLine(40, i, 20 * (SIZE_DAMIER + 1), i);
        }
        for (int j = 40; j <= 20 * (SIZE_DAMIER + 1); j += 20) {
            g.drawLine(j, 40, j, 20 * (SIZE_DAMIER + 1));
        }
        //Les cinq points noirs
        g.fillOval(97, 97, 6, 6);
        g.fillOval(97, 337, 6, 6);
        g.fillOval(337, 97, 6, 6);
        g.fillOval(337, 337, 6, 6);
        g.fillOval(217, 217, 6, 6);
    }

    //poser les pierres
    public void mousePressed(MouseEvent e) {
        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
            //position de la souris sur l'écran
            x = (int) e.getX();
            y = (int) e.getY();
            //position dans la matrice      
            int a = (x + 10) / 20 - 2;
            int b = (y + 10) / 20 - 2;
            //s'il est en endors du damier
            if (x / 20 < 2 || y / 20 < 2 || x / 20 > SIZE_DAMIER || y / 20 > SIZE_DAMIER) {
            } //sinon poser les pierres       
            else if (couleurPierre == COULEUR_NOIR) {
//                eviterSuicideNoir(matrice, b, a);
                //la fonction éviter suicide·
                if (Jeu.poserPierreNoir(matrice, b, a)) {
//                     System.out.println("suicide");
                } else {
                    //poser un pion noir
//                    jeu.detectionCapture(matrice);
                    setPions(matrice);
                }
                couleurPierre = couleurPierre * (-1);
                GO.fonctionPanel.text.setText("Tour : Blanc");
                printMatrice();
            } else if (couleurPierre == COULEUR_BLANC) {
                if (Jeu.poserPierreBlanc(matrice, b, a)) {
//                     System.out.println("suicide");                  
                } else {
//                    jeu.detectionCapture(matrice);
                    setPions(matrice);
                }
                couleurPierre = couleurPierre * (-1);
                GO.fonctionPanel.text.setText("Tour : Noir");
                printMatrice();
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

    //poser les pierres selon la matrice
    public void setPions(int[][] matrice) {
        System.out.println("setPions");
        this.removeAll();
        for (int i = 0; i < SIZE_DAMIER; i++) {
            for (int j = 0; j < SIZE_DAMIER; j++) {
                //pion noir
                if (matrice[i][j] == COULEUR_NOIR) {
                    PionNoir pionNoir = new PionNoir(this);
                    this.add(pionNoir);
                    pionNoir.setBounds(j * 20 + 30, i * 20 + 30, 20, 20);
                } //pion blanc
                else if (matrice[i][j] == COULEUR_BLANC) {
                    PionBlanc pionBlanc = new PionBlanc(this);
                    this.add(pionBlanc);
                    pionBlanc.setBounds(j * 20 + 30, i * 20 + 30, 20, 20);
                }
            }
        }
    }

    public void printMatrice() {
        for (int i = 0; i < SIZE_DAMIER; i++) {
            for (int j = 0; j < SIZE_DAMIER; j++) {
                System.out.print(matrice[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
