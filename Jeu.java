/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

/**
 *
 * @author fabienrouillon
 */
public class Jeu {
    
    int[][] plateau = new int[16][16];
    
    public Jeu(){
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                this.plateau[i][j]=0;
            }
        }
    }
    
}
