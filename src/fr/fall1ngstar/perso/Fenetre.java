package fr.fall1ngstar.perso;

import javax.swing.*;
import java.awt.*;

/**
 * Fenetre class
 * Created by Thierry
 * 02/05/2017
 */
public class Fenetre extends JFrame {

    int indice = 0xffffffff;
    JButton plus = new JButton("Plus");
    JButton moins = new JButton("Moins");
    JLabel centre = new JLabel(getText());

    public Fenetre(){
        this.setTitle("Première fenêtre");
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pan  = new JPanel();
        pan.setLayout(new BorderLayout());
        plus.addActionListener((e)->{
           indice++;
            centre.setText(getText());
        });
        centre.setHorizontalAlignment(SwingConstants.CENTER);
        pan.add(plus, BorderLayout.SOUTH);
        pan.add(moins, BorderLayout.NORTH);
        pan.add(centre, BorderLayout.CENTER);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    private String getText(){
        return String.valueOf(indice) + "   |    " + Integer.toHexString(indice) + "   |   " + Integer.toBinaryString(indice);
    }
}
