package fr.fall1ngstar.perso;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau class
 * Created by Thierry
 * 02/05/2017
 */
public class Panneau extends JPanel {
    public Panneau(){
        this.setBackground(Color.BLUE);
    }

    public void paintComponent(Graphics g){
        System.out.println("Je suis executé !");
        int x = this.getWidth()/4;
        int y = this.getHeight()/4;
        g.setColor(Color.BLUE);
        g.fillOval(x,y,getWidth()/2, getHeight()/2);
    }

}
