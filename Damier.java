/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour le damier 
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;

class Damier extends Panel implements MouseListener, ActionListener {

    final public static int COULEUR_NOIR = 1;
    final public static int COULEUR_BLANC = -1;
    //size du damier
    public static int SIZE_DAMIER = 19;
    //matric des pions
    int[][] matrice = new int[SIZE_DAMIER][SIZE_DAMIER];
    //la position de la souris sur l'écran
    int x = -1;
    int y = -1;
    int coleurPierre = 1;
    Button button = new Button("Recommencer");
    TextField text_1 = new TextField("Tour : Noir");

    Damier() {
        setSize(440, 440);
        setLayout(null);
        setBackground(new Color(255, 178, 102));
        addMouseListener(this);
        add(button);
        button.setBounds(40, 5, 140, 26);
        button.addActionListener(this);
        add(text_1);
        text_1.setBounds(260, 5, 260, 24);
        text_1.setEditable(false);
    }

    //dessiner le damier 
    public void paint(Graphics g) {
        for (int i = 40; i <= 400; i += 20) {
            g.drawLine(40, i, 400, i);
        }
        for (int j = 40; j <= 400; j += 20) {
            g.drawLine(j, 40, j, 400);
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
        Jeu jeu = new Jeu();
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
            else if (coleurPierre == COULEUR_NOIR) {
//                eviterSuicideNoir(matrice, b, a);
                //la fonction éviter suicide·
                if (!Jeu.poserPierreNoir(matrice, b, a)) {
//                     System.out.println("suicide");
                } else {
                    //poser un pion noir
                    PionNoir pinoNoir = new PionNoir(this);
                    this.add(pinoNoir);
                    pinoNoir.setBounds(a * 20 + 30, b * 20 + 30, 20, 20);
                    matrice[b][a] = COULEUR_NOIR;
                    jeu.detectionCapture(matrice);
                    setPion(matrice);
                    coleurPierre = coleurPierre * (-1);
                    text_1.setText("Tour : Blanc");
                    printMatrice();
                }

            } else if (coleurPierre == COULEUR_BLANC) {
                if (!Jeu.poserPierreBlanc(matrice, b, a)) {
//                     System.out.println("suicide");
                } else {
                    //poser un pion blanc
                    PionBlanc pionBlanc = new PionBlanc(this);
                    this.add(pionBlanc);
                    pionBlanc.setBounds(a * 20 - 10, b * 20 - 10, 20, 20);
                    matrice[b][a] = COULEUR_BLANC;
                    jeu.detectionCapture(matrice);
                    setPion(matrice);
                    coleurPierre = coleurPierre * (-1);
                    text_1.setText("Tour : Noir");
                    printMatrice();
                }
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
    public void setPion(int[][] matrice) {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                //pion noir
                if (matrice[i][j] == 1) {
                    PionNoir pionNoir = new PionNoir(this);
                    this.add(pionNoir);
                    pionNoir.setBounds(i * 20 - 10, j * 20 - 10, 20, 20);
                } //pion blanc
                else if (matrice[i][j] == -1) {
                    PionBlanc pionBlanc = new PionBlanc(this);
                    this.add(pionBlanc);
                    pionBlanc.setBounds(i * 20 - 10, j * 20 - 10, 20, 20);
                }
            }
        }
    }

    //recommencer
    public void actionPerformed(ActionEvent e) {
        this.removeAll();
        for (int i = 0; i < SIZE_DAMIER; i++) {
            for (int j = 0; j < SIZE_DAMIER; j++) {
                matrice[i][j] = 0;
            }
        }

        coleurPierre = 1;
        add(button);
        button.setBounds(10, 5, 60, 26);
        add(text_1);
        text_1.setBounds(90, 5, 90, 24);
        text_1.setText("Tour : Noir");
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
