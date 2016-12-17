/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

/**
 * la classe Pion nous aide à représenter les éléments : pierre noire ou pierre
 * blanche bien qu'au final ils sont représentés par des 1 ou -1 sur une matrice
 *
 * @author Rémi
 */
public class Pion2D {

    private int x;
    private int y;
    private int couleur;

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Pion2D(int x, int y, int couleur) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }

    public boolean equals(Pion2D p) {
        boolean retour = false;
        if ((p.getX() == this.x) && (p.getY() == this.y) && (p.getCouleur() == this.couleur)) {
            retour = true;
        }
        return retour;
    }

}
