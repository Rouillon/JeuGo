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
    public static int SIZE_DAMIER; //taille +1
    //matrice des pions
    public int[][] matrice = new int[SIZE_DAMIER][SIZE_DAMIER];
    public int[][] derniereMatrice = new int[SIZE_DAMIER][SIZE_DAMIER];
    public int[][] avantDerniereMatrice = new int[SIZE_DAMIER][SIZE_DAMIER];
    //la position de la souris sur l'écran
    public int x = -1;
    public int y = -1;

    public static int couleurPierre = 1;
    public Jeu jeu;

    public static int nbrBlancCaptures = 0;
    public static int nbrNoirCaptures = 0;

    Damier() {
        setSize(20 * (SIZE_DAMIER + 2), 20 * (SIZE_DAMIER + 2));
        setLayout(null);
        setBackground(new Color(255, 178, 102));
        addMouseListener(this);
        for (int i = 0; i < SIZE_DAMIER; i++) {
            matrice[i][0] = 2;
            matrice[i][SIZE_DAMIER - 1] = 2;
            matrice[0][i] = 2;
            matrice[SIZE_DAMIER - 1][i] = 2;
        }
    }

    //dessiner le damier 
    public void paint(Graphics g) {
        for (int i = 60; i <= 20 * (SIZE_DAMIER); i += 20) {
            g.drawLine(60, i, 20 * (SIZE_DAMIER), i);
        }
        for (int j = 60; j <= 20 * (SIZE_DAMIER); j += 20) {
            g.drawLine(j, 60, j, 20 * (SIZE_DAMIER));
        }
    }

    //poser les pierres
    public void mousePressed(MouseEvent e) {
        if (!GO.finPartie) {
            if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                //position de la souris sur l'écran
                x = (int) e.getX();
                y = (int) e.getY();
                //position dans la matrice      
                int a = (x + 10) / 20 - 2;
                int b = (y + 10) / 20 - 2;
                //s'il est en dedors du damier
                if (x / 20 < 2 || y / 20 < 2 || x / 20 > SIZE_DAMIER || y / 20 > SIZE_DAMIER || a == 0 || b == 0 || a == SIZE_DAMIER - 1 || b == SIZE_DAMIER - 1) {
                } //sinon poser les pierres       
                else if (couleurPierre == COULEUR_NOIR) {
                    if (GO.handicape > 1) {
                        GO.handicape -= 1;
                        FonctionPanel.passer = 0;
                        Jeu.poserPierreNoir(matrice, b, a);
                        setPions(matrice);
                        //printMatrice();
                    } else {
                        GO.fonctionPanel.text2.setText("");
                        FonctionPanel.passer = 0;
                        //Poser un pion noir
                        Jeu.poserPierreNoir(matrice, b, a);
                        //Pour vérifier si la règle du ko a lieu
                        for (int i = 0; i < SIZE_DAMIER; i++) {
                            for (int j = 0; j < SIZE_DAMIER; j++) {
                                avantDerniereMatrice[i][j] = derniereMatrice[i][j];
                                derniereMatrice[i][j] = matrice[i][j];
                            }
                        }
                        boolean ko = true;
                        for (int i = 0; i < SIZE_DAMIER; i++) {
                            for (int j = 0; j < SIZE_DAMIER; j++) {
                                if (avantDerniereMatrice[i][j] != matrice[i][j]) {
                                    ko = false;
                                    break;
                                }
                            }
                        }
                        if ((Jeu.detectionCaptureNoir(matrice)) && !(Jeu.detectionCaptureBlanc(matrice))) {
                            matrice[b][a] = 0;
                            GO.fonctionPanel.text2.setText("Suicide Interdit !");
                            //printMatrice();
                        } else if (ko) {
                            matrice[b][a] = 0;
                            GO.fonctionPanel.text2.setText("Règle du ko !");
                            //printMatrice();
                        } else {
                            Jeu.CaptureBlanc(matrice);
                            setPions(matrice);
                            couleurPierre = couleurPierre * (-1);
                            GO.fonctionPanel.text.setText("Tour : Blanc");
                            GO.fonctionPanel.text5.setText("Blanc: " + nbrBlancCaptures);
                            GO.fonctionPanel.text4.setText("Noir: " + nbrNoirCaptures);
                            //printMatrice();
                        }
                    }
                } else if (couleurPierre == COULEUR_BLANC) {
                    GO.fonctionPanel.text2.setText("");
                    FonctionPanel.passer = 0;
                    //Poser un pion blanc
                    Jeu.poserPierreBlanc(matrice, b, a);
                    //Pour vérifier si la règle du ko a lieu
                    for (int i = 0; i < SIZE_DAMIER; i++) {
                        for (int j = 0; j < SIZE_DAMIER; j++) {
                            avantDerniereMatrice[i][j] = derniereMatrice[i][j];
                            derniereMatrice[i][j] = matrice[i][j];
                        }
                    }
                    boolean ko = true;
                    for (int i = 0; i < SIZE_DAMIER; i++) {
                        for (int j = 0; j < SIZE_DAMIER; j++) {
                            if (avantDerniereMatrice[i][j] != matrice[i][j]) {
                                ko = false;
                                break;
                            }
                        }
                    }
                    if ((Jeu.detectionCaptureBlanc(matrice)) && !(Jeu.detectionCaptureNoir(matrice))) {
                        matrice[b][a] = 0;
                        GO.fonctionPanel.text2.setText("Suicide Interdit !");
                        //printMatrice();
                    } else if (ko) {
                        matrice[b][a] = 0;
                        GO.fonctionPanel.text2.setText("Règle du ko !");
                        //printMatrice();
                    } else {
                        Jeu.CaptureNoir(matrice);
                        setPions(matrice);
                        couleurPierre = couleurPierre * (-1);
                        GO.fonctionPanel.text.setText("Tour : Noir");
                        GO.fonctionPanel.text5.setText("Blanc: " + nbrBlancCaptures);
                        GO.fonctionPanel.text4.setText("Noir: " + nbrNoirCaptures);
                        //printMatrice();
                    }

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
    public void setPions(int[][] matrice) {
        this.removeAll();
        for (int i = 0; i < SIZE_DAMIER; i++) {
            for (int j = 0; j < SIZE_DAMIER; j++) {
                //pion noir
                if (matrice[i][j] == COULEUR_NOIR) {
                    PionNoir pionNoir = new PionNoir(i,j);
                    this.add(pionNoir);
                    pionNoir.setBounds(j * 20 + 30, i * 20 + 30, 20, 20);
                } //pion blanc
                else if (matrice[i][j] == COULEUR_BLANC) {
                    PionBlanc pionBlanc = new PionBlanc(i,j);
                    this.add(pionBlanc);
                    pionBlanc.setBounds(j * 20 + 30, i * 20 + 30, 20, 20);
                }
            }
        }
    }

    // Pour afficher la matrice sous forme texte dans la console
    /*public void printMatrice() {
        for (int i = 0; i < SIZE_DAMIER; i++) {
            for (int j = 0; j < SIZE_DAMIER; j++) {
                System.out.print(matrice[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }*/
}
