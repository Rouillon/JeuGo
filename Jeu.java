/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

import static JeuGo.Jeu.s;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author fabienrouillon
 */
public class Jeu {

    public static String s;
    int[][] plateau = new int[16][16];

    public Jeu() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.plateau[i][j] = 0;
            }
        }
    }

    public boolean poserPierreNoir(int[][] plateau, int x, int y) {

        boolean passerNoir = false;//pour marquer si le joueur veut passer
        System.out.println("Est-ce que vous voulez passer cette manche?");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            s = br.readLine();
        } catch (IOException e) {
        }

        if (s.equals("oui")) {
            passerNoir = true;
        } else {
            {
                if (plateau[x][y] == 0 && x < 16 && x > 0 && y < 16 && y > 0) {
                    plateau[x][y] = 1;
                } else {
                    System.out.println("Vous pouvez pas le poser ici!");
                }
            }
            passerNoir = false;
        }
        return passerNoir;
    }

    public boolean poserPierreBlanc(int[][] plateau, int x, int y) {

        boolean passerBlanc = false;//pour marquer si le joueur veut passer
        System.out.println("Est-ce que vous voulez passer cette manche?");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            s = br.readLine();
        } catch (IOException e) {
        }

        if (s.equals("oui")) {
            passerBlanc = true;
        } else {
            {
                if (plateau[x][y] == 0 && x < 16 && x > 0 && y < 16 && y > 0) {
                    plateau[x][y] = -1;
                } else {
                    System.out.println("Vous pouvez pas le poser ici!");
                }
            }
            passerBlanc = false;
        }
        return passerBlanc;
    }

//    public void recommencer() {
//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                this.plateau[i][j] = 0;
//            }
//        }
//    }

    public int nbPierreNoir(int[][] plateau) {
        int nbNoir = 0;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (plateau[i][j] == 1) {
                    nbNoir++;
                }
            }
        }
        return nbNoir;
    }

    public int nbPierreBlanc(int[][] plateau) {
        int nbBlanc = 0;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (plateau[i][j] == -1) {
                    nbBlanc++;
                }
            }
        }
        return nbBlanc;
    }
    
    public void eviterSuicide(int[][] plateau,int x, int y){
        if (x == 0 && y == 0)
            if ( plateau[x+1][y] == 1 && plateau[x][y+1] == 1){
                System.out.println("Vous pouvez pas le poser ici!");
            }
    }
}
