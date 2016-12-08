package JeuGo;

import java.awt.*;
import java.awt.event.*;

//la classe pour le damier 
class ChessPad extends Panel implements MouseListener, ActionListener {

    int x = -1;
    int y = -1;
    int coleurPierre = 1;
    Button button = new Button("Recommencer");
    TextField text_1 = new TextField("Tour : Noir");
    TextField text_2 = new TextField();

    ChessPad() {
        setSize(440, 440);
        setLayout(null);
        setBackground(Color.yellow);
        addMouseListener(this);
        add(button);
        button.setBounds(10, 5, 60, 26);
        button.addActionListener(this);
        add(text_1);
        //
        text_1.setBounds(90, 5, 90, 24);
        add(text_2);
        text_2.setBounds(290, 5, 90, 24);
        text_1.setEditable(false);
        text_2.setEditable(false);

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
        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
            //la position sur l'écran
            x = (int) e.getX();
            y = (int) e.getY();
            //définir deux pierres
            ChessPoint_black chesspoint_black = new ChessPoint_black(this);
            ChessPoint_white chesspoint_white = new ChessPoint_white(this);
            int a = (x + 10) / 20, b = (y + 10) / 20;
            //s'il est endors du damier
            if (x / 20 < 2 || y / 20 < 2 || x / 20 > 19 || y / 20 > 19) {
            } 
            //sinon poser les pierres
            else if (coleurPierre == 1) {
                this.add(chesspoint_black);
                chesspoint_black.setBounds(a * 20 - 10, b * 20 - 10, 20, 20);
                coleurPierre = coleurPierre * (-1);
                text_1.setText("Tour : Blanc");
                text_2.setText(null);
            } else if (coleurPierre == -1) {
                this.add(chesspoint_white);
                chesspoint_white.setBounds(a * 20 - 10, b * 20 - 10, 20, 20);
                coleurPierre = coleurPierre * (-1);
                text_1.setText("Tour : Noir");
                text_2.setText(null);
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
    
    //recommencer
    public void actionPerformed(ActionEvent e) {
        this.removeAll();
        coleurPierre = 1;
        add(button);
        button.setBounds(10, 5, 60, 26);
        add(text_1);
        text_1.setBounds(90, 5, 90, 24);
        add(text_2);
        text_2.setBounds(290, 5, 90, 24);
        text_1.setText("Tour : Noir");
        text_2.setText(null);
    }
}
