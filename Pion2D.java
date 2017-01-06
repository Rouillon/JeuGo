/*
 *TP3 de MEDEV 
 *Jeu de GO
 *Classe qui contient la définiton d'un Pion2D (pion version matriciel)
 */
package JeuGo;

/**
 * La classe Pion nous aide à représenter les éléments : pierre noire ou pierre
 * blanche bien qu'au final ils sont représentés par des 1 ou -1 sur une matrice
 *
 * @author Rémi
 */
public class Pion2D {

    // abscisse
    private int x;
    // ordonnée
    private int y;
    // couleur : -1 pour blanc , 1 pour noir
    private int couleur;

    // GETTER ET SETTERS
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

    // CONSTRUCTEUR
    public Pion2D(int x, int y, int couleur) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }

    // REDEFINITION DE EQUALS (changement de nom pour éviter problème d'Override)
    public boolean Egale(Pion2D p) {
        boolean retour = false;
        if ((p.getX() == this.x) && (p.getY() == this.y) && (p.getCouleur() == this.couleur)) {
            retour = true;
        }
        return retour;
    }

}
