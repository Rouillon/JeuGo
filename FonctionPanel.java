/*
 *TP3 de MEDEV 
 *Jeu de GO
 *la classe pour le damier 
 */
package JeuGo;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class FonctionPanel extends Panel implements ActionListener {

    //public static int SIZE_DAMIER = 16;
    private Button btn_recommencer = new Button("Recommencer");
    private Button btn_passer = new Button("Passer");
    private Button btn_score = new Button("Scores");
    private Button btn_suivant = new Button("Suivant");
    private Button btn_precedent = new Button("Précédent");
    private Button btn_jouer = new Button("Jouer");
    private TextField text = new TextField("Tour : Noir");
    private TextField text2 = new TextField("");
    private TextField text3 = new TextField("Pierres capturées:");
    private TextField text4 = new TextField("Noir: 0");
    private TextField text5 = new TextField("Blanc: 0");
    private TextField text6 = new TextField("Score final");
    private TextField text7 = new TextField("Noir: ");
    private TextField text8 = new TextField("Blanc :");
    private TextField text9 = new TextField("... Gagne");

    public Button getBtn_jouer() {
        return btn_jouer;
    }

    public void setBtn_jouer(Button btn_jouer) {
        this.btn_jouer = btn_jouer;
    }

    public Button getBtn_suivant() {
        return btn_suivant;
    }

    public void setBtn_suivant(Button btn_suivant) {
        this.btn_suivant = btn_suivant;
    }

    public Button getBtn_precedent() {
        return btn_precedent;
    }

    public void setBtn_precedent(Button btn_precedent) {
        this.btn_precedent = btn_precedent;
    }
    
    public Button getBtn_recommencer() {
        return btn_recommencer;
    }

    public void setBtn_recommencer(Button btn_recommencer) {
        this.btn_recommencer = btn_recommencer;
    }

    public Button getBtn_passer() {
        return btn_passer;
    }

    public void setBtn_passer(Button btn_passer) {
        this.btn_passer = btn_passer;
    }

    public Button getBtn_score() {
        return btn_score;
    }

    public void setBtn_score(Button btn_score) {
        this.btn_score = btn_score;
    }

    public TextField getText() {
        return text;
    }

    public void setText(TextField text) {
        this.text = text;
    }

    public TextField getText2() {
        return text2;
    }

    public void setText2(TextField text2) {
        this.text2 = text2;
    }

    public TextField getText3() {
        return text3;
    }

    public void setText3(TextField text3) {
        this.text3 = text3;
    }

    public TextField getText4() {
        return text4;
    }

    public void setText4(TextField text4) {
        this.text4 = text4;
    }

    public TextField getText5() {
        return text5;
    }

    public void setText5(TextField text5) {
        this.text5 = text5;
    }

    public TextField getText6() {
        return text6;
    }

    public void setText6(TextField text6) {
        this.text6 = text6;
    }

    public TextField getText7() {
        return text7;
    }

    public void setText7(TextField text7) {
        this.text7 = text7;
    }

    public TextField getText8() {
        return text8;
    }

    public void setText8(TextField text8) {
        this.text8 = text8;
    }

    public TextField getText9() {
        return text9;
    }

    public void setText9(TextField text9) {
        this.text9 = text9;
    }

    
    
    FonctionPanel() {
        setSize(140, 20 * (GO.getDamier().getSIZE_DAMIER() + 2));
        setLayout(null);
        setBackground(Color.WHITE);

        add(text);
        text.setBounds(20, 20, 120, 20);
        text.setEditable(false);
        add(text2);
        text2.setBounds(20, 40, 120, 20);
        text2.setEditable(false);
        add(btn_recommencer);
        btn_recommencer.setBounds(20, 80, 120, 20);
        btn_recommencer.addActionListener(this);
        add(btn_passer);
        btn_passer.setBounds(20, 120, 120, 20);
        btn_passer.addActionListener(this);
        add(text3);
        text3.setBounds(20, 160, 120, 20);
        text3.setEditable(false);
        add(text4);
        text4.setBounds(20, 180, 120, 20);
        text4.setEditable(false);
        add(text5);
        text5.setBounds(20, 200, 120, 20);
        text5.setEditable(false);
    }

    //gestion des boutons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_recommencer) {
            GO.setPasser(0);
            GO.setFinPartie(false);
            GO go = new GO();
            text.setText("Tour : Noir");
            //GO.damier.setPions(GO.damier.matrice);
        } else if (e.getSource() == btn_passer) {
            GO.setPasser(GO.getPasser()+1);
            if (GO.getPasser() == 2) {
                System.out.println("Fin de la partie");
                GO.setFinPartie(true);
                text.setText("Fin de la partie");
                add(btn_score);
                btn_score.setBounds(20, 240, 120, 20);
                btn_score.addActionListener(this);
            }
            GO.getDamier().setCouleurPierre(GO.getDamier().getCouleurPierre() * (-1));
            if (text.getText().equals("Tour : Noir")) {
                text.setText("Tour : Blanc");
            } else if (text.getText().equals("Tour : Blanc")) {
                text.setText("Tour : Noir");
            }
        } else if (e.getSource() == btn_score) {
            GO.setFinPierresMortes(true);
            for (int i = 1; i < GO.getDamier().getSIZE_DAMIER() - 1; i++) {
                for (int j = 1; j < GO.getDamier().getSIZE_DAMIER() - 1; j++) {
                    GO.getDamier().setMatrice(Jeu.remplissageDroit(GO.getDamier().getMatrice(), i, j),i,j);
                }
            }
            GO.getDamier().setPions(GO.getDamier().getMatrice());
            add(text6);
            text6.setBounds(20, 280, 120, 20);
            text6.setEditable(false);
            add(text7);
            text7.setBounds(20, 300, 120, 20);
            text7.setEditable(false);
            int scoreNoir = Jeu.nbPierreNoir(GO.getDamier().getMatrice());
            double komi = 7.5;
            if (GO.getHandicapeInitial() > 1) {
                komi = (GO.getHandicapeInitial() - 1) + 0.5;
            }
            double scoreBlanc = Jeu.nbPierreBlanc(GO.getDamier().getMatrice()) + komi;
            text7.setText("Noir: " + scoreNoir);
            add(text8);
            text8.setBounds(20, 320, 120, 20);
            text8.setEditable(false);
            text8.setText("Blanc: " + scoreBlanc);
            add(text9);
            text9.setBounds(20, 360, 120, 20);
            text9.setEditable(false);
            if (scoreBlanc > scoreNoir) {
                text9.setText("Blanc Gagne !");
            } else {
                text9.setText("Noir Gagne !");
            }

        } else if (e.getSource() == btn_suivant) {
            if (GO.getTourCharge()<GO.getNbTours()) {
                GO.setTourCharge(GO.getTourCharge()+1);
                Jeu.chargerMatrice("test.txt", GO.getTourCharge());
                GO.getDamier().setPions(GO.getDamier().getMatrice());
            }
        } else if (e.getSource() == btn_precedent) {
            if (GO.getTourCharge()>1) {
                GO.setTourCharge(GO.getTourCharge()-1);
                Jeu.chargerMatrice("test.txt", GO.getTourCharge());
                GO.getDamier().setPions(GO.getDamier().getMatrice());
            }
        } else if (e.getSource() == btn_jouer) {
            remove(btn_suivant);
            remove(btn_precedent);
            remove(btn_jouer);
            GO.setChargement(false);
        }
    }
}
