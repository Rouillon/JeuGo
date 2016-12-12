/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeuGo;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author fabienrouillon
 */
public class JeuTest {

    public JeuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of poserPierreNoir method, of class Jeu.
     */
    @Ignore
    @Test
    public void testPoserPierreNoir() {
        System.out.println("poserPierreNoir");
        int[][] plateau = new int[16][16];
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                plateau[i][j] = 0;
            }
        }
        boolean expResult = false;
        boolean result = Jeu.poserPierreNoir(plateau, 3, 3);
        assertEquals(expResult, result);
    }

    /**
     * Test of poserPierreBlanc method, of class Jeu.
     */
    @Ignore
    @Test
    public void testPoserPierreBlanc() {
        System.out.println("poserPierreBlanc");
        int[][] plateau = null;
        int x = 0;
        int y = 0;
        boolean expResult = false;
        boolean result = Jeu.poserPierreBlanc(plateau, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eviterSuicideNoir method, of class Jeu.
     */
    @Test
    public void testEviterSuicideNoir() {
        System.out.println("eviterSuicideNoir");
        int[][] plateau = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                plateau[i][j] = 0;
            }
        }
        // test eviteSuicideNoir quand il peut mettre dans la case (10,10)
        boolean expResult = true;
        boolean result = Jeu.eviterSuicideNoir(plateau, 10, 10);
        // test eviteSuicideNoir quand il peut pas mettre dans la case (3,4)
        plateau[3][3] = plateau[3][5] = plateau[2][4] = plateau[4][4] = -1;
        boolean expResult1 = false;
        boolean result1 = Jeu.eviterSuicideNoir(plateau, 3, 4);
        //test eviteSuicideNoir quand il peut pas mettre dans la case (0,0)
        plateau[0][1] = plateau[1][0] = -1;
        boolean expResult2 = false;
        boolean result2 = Jeu.eviterSuicideNoir(plateau, 0, 0);
        //test eviteSuicideNoir quand il peut mettre dans la case (0,0)
        plateau[0][1] = 1;
        boolean expResult4 = true;
        boolean result4 = Jeu.eviterSuicideNoir(plateau, 0, 0);
        //test eviteSuicideNoir quand il peut pas mettre dans la case (0,14)
        plateau[0][13] = plateau[0][15] = plateau[1][14] = -1;
        boolean expResult3 = false;
        boolean result3 = Jeu.eviterSuicideNoir(plateau, 0, 14);
        assertEquals(expResult, result);
    }

    /**
     * Test of eviterSuicideBlanc method, of class Jeu.
     */
    @Test
    public void testEviterSuicideBlanc() {
        System.out.println("eviterSuicideBlanc");
        int[][] plateau = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                plateau[i][j] = 0;
            }
        }
        // test eviteSuicideBlanc quand il peut mettre dans la case (10,10)
        boolean expResult = true;
        boolean result = Jeu.eviterSuicideBlanc(plateau, 10, 10);
        // test eviteSuicideBlanc quand il peut pas mettre dans la case (3,4)
        plateau[3][3] = plateau[3][5] = plateau[2][4] = plateau[4][4] = 1;
        boolean expResult1 = false;
        boolean result1 = Jeu.eviterSuicideBlanc(plateau, 3, 4);
        //test eviteSuicideBlanc quand il peut pas mettre dans la case (0,0)
        plateau[0][1] = plateau[1][0] = 1;
        boolean expResult2 = false;
        boolean result2 = Jeu.eviterSuicideBlanc(plateau, 0, 0);
        //test eviteSuicideBlanc quand il peut mettre dans la case (0,0)
        plateau[0][1] = -1;
        boolean expResult4 = true;
        boolean result4 = Jeu.eviterSuicideBlanc(plateau, 0, 0);
        //test eviteSuicideBlanc quand il peut pas mettre dans la case (0,14)
        plateau[0][13] = plateau[0][15] = plateau[1][14] = -1;
        boolean expResult3 = false;
        boolean result3 = Jeu.eviterSuicideBlanc(plateau, 0, 14);
        assertEquals(expResult, result);
    }

    /**
     * Test of nbPierreNoir method, of class Jeu.
     */
    @Test
    public void testNbPierreNoir() {
        System.out.println("nbPierreNoir");
        int[][] plateau = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                plateau[i][j] = 0;
            }
        }
        plateau[3][4] = 1;
        plateau[7][8] = 1;
        plateau[0][0] = 1;
        plateau[2][0] = -1;
        plateau[2][9] = -1;
        int expResult = 3;
        int result = Jeu.nbPierreNoir(plateau);
        assertEquals(expResult, result);
    }

    /**
     * Test of nbPierreBlanc method, of class Jeu.
     */
    @Test
    public void testNbPierreBlanc() {
        System.out.println("nbPierreBlanc");
        int[][] plateau = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                plateau[i][j] = 0;
            }
        }
        plateau[3][4] = 1;
        plateau[7][8] = 1;
        plateau[0][0] = 1;
        plateau[2][0] = -1;
        plateau[2][9] = -1;
        int expResult = 2;
        int result = Jeu.nbPierreBlanc(plateau);
        assertEquals(expResult, result);
    }

    /**
     * Test of adjacents method, of class Jeu.
     */
    @Test
    public void testAdjacents() {
        System.out.println("adjacents");
        Pion p = new Pion(3, 3, -1);
        int[][] plateau = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                plateau[i][j] = 0;
            }
        }
        plateau[2][3] = -1;
        plateau[1][1] = -1;
        plateau[3][4] = 1;
        plateau[3][2] = -1;
        ArrayList<Pion> expResult = new ArrayList();
        expResult.add(new Pion(2, 3, -1));
        expResult.add(new Pion(3, 2, -1));
        ArrayList<Pion> result = Jeu.adjacents(plateau, p);
        //assertEquals(result.get(0).getX(), expResult.get(0).getX());
        //assertEquals(result.get(1).getX(), expResult.get(1).getX());
        //assertEquals(result.get(0).getY(), expResult.get(0).getY());
        //assertEquals(result.get(1).getY(), expResult.get(1).getY());
        assertEquals(result.get(0).getCouleur(), expResult.get(0).getCouleur());
        assertEquals(result.get(1).getCouleur(), expResult.get(1).getCouleur());

    }

    /**
     * Test of ajouterAdjacent method, of class Jeu.
     */
    @Ignore
    @Test
    public void testAjouterAdjacent() {
        System.out.println("ajouterAdjacent");
        Pion p = null;
        ArrayList<Pion> groupe = null;
        Jeu instance = new Jeu();
        ArrayList<Pion> expResult = null;
        ArrayList<Pion> result = instance.ajouterAdjacent(p, groupe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of detectionGroupes method, of class Jeu.
     */
    @Ignore
    @Test
    public void testDetectionGroupes() {
        System.out.println("detectionGroupes");
        Jeu instance = new Jeu();
        instance.detectionGroupes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of detectionCapture method, of class Jeu.
     */
    @Ignore
    @Test
    public void testDetectionCapture() {
        System.out.println("detectionCapture");
        int[][] plateau = null;
        Jeu instance = new Jeu();
        instance.detectionCapture(plateau);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
