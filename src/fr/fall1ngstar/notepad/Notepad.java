package fr.fall1ngstar.notepad;

import javax.swing.*;

/**
 * Notepad class
 * Created by Thierry
 * 10/05/2017
 */
public class Notepad extends JFrame {

    public Notepad(){
        super();
        build();
    }

    private void build(){
        setTitle("NotepadJava");
        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new ContentPane());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
