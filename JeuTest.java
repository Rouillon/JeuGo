/*
 *TP3 de MEDEV 
 *Jeu de GO
 *Classe de test de la classe Jeu
 */
package JeuGo;

import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author fabienrouillon
 */
public class JeuTest {

    // Taille du jeu réglable pour tester les 3 possibilités
    // (11 pour 9, 18 pour 16 et 21 pour 19)
    int tailleJeu = 11;
    // On créé un plateau test que l'on va utiliser pour tester nos méthodes
    int[][] plateauTest = new int[tailleJeu][tailleJeu];

    /**
     * CONSTRUCTEUR PAR DEFAUT
     */
    @Before
    public void JeuTest() {
        // On initialise un plateau de test
        for (int i = 0; i < tailleJeu; i++) {
            for (int j = 0; j < tailleJeu; j++) {
                plateauTest[i][j] = 0;
            }
        }
        for (int i = 0; i < tailleJeu; i++) {
            plateauTest[i][0] = 2;
            plateauTest[i][tailleJeu - 1] = 2;
            plateauTest[0][i] = 2;
            plateauTest[tailleJeu - 1][i] = 2;
        }
    }

    /**
     * Méthode qui permet d'initialiser un plateau avec les mêmes données que le
     * plateau de test
     *
     * @param plateau
     */
    public void setPlateauTest(int[][] plateau) {
        for (int i = 0; i < tailleJeu; i++) {
            System.arraycopy(plateauTest[i], 0, plateau[i], 0, tailleJeu);
        }
    }

    /**
     * Méthode qui permet de tester si un plateau donné a les mêmes valeurs que
     * le plateau de test
     *
     * @param plateau
     * @return booléen vrai si le plateau testé à les même valeurs que le
     * plateau de test
     */
    public boolean equalsPlateauTest(int[][] plateau) {
        boolean equals = true;
        for (int i = 0; i < tailleJeu; i++) {
            for (int j = 0; j < tailleJeu; j++) {
                if (plateau[i][j] != plateauTest[i][j]) {
                    equals = false;
                    break;
                }

            }
        }
        return equals;
    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test de poserPierreNoir.
     */
    @Test
    public void testPoserPierreNoir() {
        System.out.println("poserPierreNoir");
        int[][] plateau = new int[tailleJeu][tailleJeu];
        setPlateauTest(plateau);
        plateau[3][4] = 1;
        plateau[1][7] = 1;
        plateau[8][8] = 1;
        Jeu.poserPierreNoir(plateauTest, 3, 4);
        Jeu.poserPierreNoir(plateauTest, 1, 7);
        Jeu.poserPierreNoir(plateauTest, 8, 8);
        boolean test = equalsPlateauTest(plateau);
        assertEquals(test, true);
    }

    /**
     * Test de poserPierreBlanc.
     */
    @Test
    public void testPoserPierreBlanc() {
        System.out.println("poserPierreBlanc");
        int[][] plateau = new int[tailleJeu][tailleJeu];
        setPlateauTest(plateau);
        plateau[3][4] = -1;
        plateau[1][7] = -1;
        plateau[8][8] = -1;
        Jeu.poserPierreBlanc(plateauTest, 3, 4);
        Jeu.poserPierreBlanc(plateauTest, 1, 7);
        Jeu.poserPierreBlanc(plateauTest, 8, 8);
        boolean test = equalsPlateauTest(plateau);
        assertEquals(test, true);
    }

    /**
     * Test de nbPierreNoir.
     */
    @Test
    public void testNbPierreNoir() {
        System.out.println("nbPierreNoir");
        plateauTest[3][4] = 1;
        plateauTest[7][6] = 1;
        plateauTest[1][1] = 1;
        plateauTest[6][3] = 1;
        plateauTest[8][8] = 1;
        plateauTest[2][1] = -1;
        plateauTest[7][8] = -1;
        plateauTest[7][4] = -1;
        GO go = new GO();
        GO.getDamier().setSIZE_DAMIER(tailleJeu);
        int expResult = 5;
        int result = Jeu.nbPierreNoir(plateauTest);
        assertEquals(expResult, result);
    }

    /**
     * Test de nbPierreBlanc.
     */
    @Test
    public void testNbPierreBlanc() {
        System.out.println("nbPierreBlanc");
        plateauTest[3][4] = 1;
        plateauTest[7][6] = 1;
        plateauTest[1][1] = 1;
        plateauTest[6][3] = 1;
        plateauTest[8][8] = 1;
        plateauTest[2][1] = -1;
        plateauTest[7][8] = -1;
        plateauTest[7][4] = -1;
        GO go = new GO();
        GO.getDamier().setSIZE_DAMIER(tailleJeu);
        int expResult = 3;
        int result = Jeu.nbPierreBlanc(plateauTest);
        assertEquals(expResult, result);
    }

    /**
     * Test de adjacents.
     */
    @Test
    public void testAdjacents() {
        System.out.println("adjacents");
        Pion2D p = new Pion2D(3, 3, -1);
        plateauTest[3][3] = -1;
        plateauTest[2][3] = -1;
        plateauTest[3][4] = 1;
        plateauTest[3][2] = -1;
        ArrayList<Pion2D> expResult = new ArrayList();
        expResult.add(new Pion2D(2, 3, -1));
        expResult.add(new Pion2D(3, 2, -1));
        LinkedList<Pion2D> result = Jeu.adjacents(plateauTest, p);
        boolean test = true;
        int i = 0;
        for (Pion2D pion : result) {
            if (!(pion.equals(expResult.get(i)))) {
                test = false;
                break;
            }
            i += 1;
        }
        assertEquals(test, true);
    }

    /**
     * Test de ajouterAdjacent.
     */
    @Test
    public void testAjouterAdjacent() {
        System.out.println("ajouterAdjacent");
        Jeu.Jeu();
        Pion2D p1 = new Pion2D(3, 3, -1);
        Pion2D p2 = new Pion2D(3, 4, -1);
        Pion2D p3 = new Pion2D(3, 5, -1);
        Pion2D p4 = new Pion2D(4, 5, -1);
        plateauTest[3][3] = -1;
        plateauTest[3][4] = -1;
        plateauTest[3][5] = -1;
        plateauTest[4][5] = -1;
        plateauTest[3][2] = 1;
        plateauTest[3][6] = 1;
        plateauTest[5][5] = 1;
        ArrayList<Pion2D> expResult = new ArrayList();
        ArrayList<Pion2D> groupe = new ArrayList();
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        expResult.add(p4);
        ArrayList<Pion2D> result = Jeu.ajouterAdjacent(plateauTest, p1, groupe);
        boolean test = true;
        int i = 0;
        for (Pion2D pion : result) {
            if (!(pion.equals(expResult.get(i)))) {
                test = false;
                break;
            }
            i += 1;
        }
        assertEquals(test, true);
    }

    /**
     * Test de CaptureBlanc.
     */
    @Test
    public void testCaptureBlanc() {
        System.out.println("captureBlanc");
        plateauTest[3][3] = -1;
        plateauTest[3][4] = 1;
        plateauTest[4][3] = 1;
        plateauTest[3][2] = 1;
        plateauTest[2][3] = 1;
        int[][] plateauBis = new int[tailleJeu][tailleJeu];
        setPlateauTest(plateauBis);
        plateauBis[3][3] = 0;
        GO go = new GO();
        GO.getDamier().setSIZE_DAMIER(tailleJeu);
        Jeu.captureBlanc(plateauTest);
        boolean test = equalsPlateauTest(plateauBis);
        assertEquals(test, true);
    }

    /**
     * Test de CaptureNoir.
     */
    @Test
    public void testCaptureNoir() {
        System.out.println("captureNoir");
        plateauTest[3][3] = 1;
        plateauTest[3][4] = -1;
        plateauTest[4][3] = -1;
        plateauTest[3][2] = -1;
        plateauTest[2][3] = -1;
        int[][] plateauBis = new int[tailleJeu][tailleJeu];
        setPlateauTest(plateauBis);
        plateauBis[3][3] = 0;
        GO go = new GO();
        GO.getDamier().setSIZE_DAMIER(tailleJeu);
        Jeu.captureNoir(plateauTest);
        boolean test = equalsPlateauTest(plateauBis);
        assertEquals(test, true);
    }

    /**
     * Test de detectionCaptureBlanc.
     */
    @Test
    public void testDetectionCaptureBlanc() {
        System.out.println("detectionCaptureBlanc");
        plateauTest[3][3] = -1;
        plateauTest[3][4] = 1;
        plateauTest[4][3] = 1;
        plateauTest[3][2] = 1;
        plateauTest[2][3] = 1;
        int[][] plateauBis = new int[tailleJeu][tailleJeu];
        setPlateauTest(plateauBis);
        GO go = new GO();
        GO.getDamier().setSIZE_DAMIER(tailleJeu);
        boolean test =((Jeu.detectionCaptureBlanc(plateauTest))&&equalsPlateauTest(plateauBis));
        assertEquals(test, true);
    }

    /**
     * Test de detectionCaptureNoir.
     */
    @Test
    public void testDetectionCaptureNoir() {
        System.out.println("detectionCaptureNoir");
        plateauTest[3][3] = 1;
        plateauTest[3][4] = -1;
        plateauTest[4][3] = -1;
        plateauTest[3][2] = -1;
        plateauTest[2][3] = -1;
        int[][] plateauBis = new int[tailleJeu][tailleJeu];
        setPlateauTest(plateauBis);
        GO go = new GO();
        GO.getDamier().setSIZE_DAMIER(tailleJeu);
        boolean test =((Jeu.detectionCaptureNoir(plateauTest))&&equalsPlateauTest(plateauBis));
        assertEquals(test, true);
    }

    /**
     * Test de chargerMatrice.
     */
    @Test
    public void testChargerMatrice() {
        System.out.println("chargerMatrice");
        GO.setDamier(new Damier());
        GO.getDamier().setSIZE_DAMIER(11);
        GO go = new GO();
        Jeu.chargerMatrice("test.txt",3);
        plateauTest[3][5] = 1;
        plateauTest[4][4] = 1;
        plateauTest[4][6] = 1;
        boolean result=equalsPlateauTest(GO.getDamier().getMatrice());
        assertEquals(result,true);
    }

    /**
     * Test de nbTours.
     */
    @Test
    public void testNbTours() {
        System.out.println("nbTours");
        int result=13;
        assertEquals(result,Jeu.nbTours("test.txt"));
    }

    /**
     * Test de taille.
     */
    @Test
    public void testTaille() {
        System.out.println("taille");
        int result=11;
        assertEquals(result,Jeu.taille("test.txt"));
    }

}
