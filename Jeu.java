/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

import java.util.ArrayList;

/**
 *
 * @author fabienrouillon
 */
public class Jeu {
    
    private int captureN;
    private int captureB;
    private int[][] plateau = new int[16][16];
    
    public Jeu(){
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                this.plateau[i][j]=0;
            }
        }
    }
    
    public ArrayList<int[]> adjacent(int[] point){
        
    }
    public ArrayList<ArrayList> groupes(){
        ArrayList<ArrayList> groupe = new ArrayList();
        ArrayList<int[]> marques = new ArrayList();
        int[] point = new int[2];
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if(this.plateau[i][j]!=0){
                    point[0] = i;
                    point[1] = j;
                    marques.add(point);
                }
                
                
            }
        }
    }
    
}
