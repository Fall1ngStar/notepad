package fr.fall1ngstar.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

/**
 * ContentPane class
 * Created by Thierry
 * 10/05/2017
 */
public class ContentPane extends JPanel {

    JTextArea textArea;
    JButton load, save;
    JToolBar toolBar;
    JScrollPane scrollPane;
    JFileChooser fileChooser;
    File openedFile;
    JLabel statusBar;

    public ContentPane() {
        super();
        build();
        setInteractions();
    }

    private void build() {
        textArea = new JTextArea();
        load = new JButton("Load");
        save = new JButton("Save");
        toolBar = new JToolBar();
        scrollPane = new JScrollPane(textArea);
        fileChooser = new JFileChooser();
        statusBar = new JLabel(getLineCount());

        toolBar.add(load);
        toolBar.add(save);
        toolBar.setFloatable(false);

        textArea.setMargin(new Insets(10,10,10,10));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
    }

    private void setInteractions() {
        load.addActionListener((e) -> {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                loadFile(file);
            } else {
            }
        });

        save.addActionListener((e) -> {
            if (openedFile == null) {
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    if (f.exists() && openedFile != null && !(f.getName().equals(openedFile.getName()) && f.getAbsolutePath().equals(openedFile.getAbsolutePath()))) {
                        int valid = JOptionPane.showConfirmDialog(this, "File already exists !  Do you want to replace it ?");
                        if (valid == JOptionPane.OK_OPTION) {
                            saveFile(f);
                        }
                    } else {
                        try {
                            f.createNewFile();
                            saveFile(f);
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                    }

                }
            } else {
                saveFile(openedFile);
            }
        });

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                statusBar.setText(getLineCount());
            }
        });
    }

    private void loadFile(File f) {
        try {
            openedFile = f;
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[16];
            StringBuilder builder = new StringBuilder();
            while (fis.read(buffer) >= 0) {
                for (byte b : buffer) {
                    builder.append((char) b);
                }
                buffer = new byte[16];
            }
            textArea.setText(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void saveFile(File f) {
        try {
            PrintWriter out = new PrintWriter(f);
            out.print(textArea.getText().replace("\n", "\r\n"));
            out.close();
            openedFile = f;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLineCount() {
        return "Nombre de lignes : " + getNbOccuChar(textArea.getText());
    }

    private int getNbOccuChar(String str) {
        int i = 1;
        for (char c : str.toCharArray()) {
            if (c == '\n') i++;
        }
        return str.equals("") ? 0 : i;
    }
}
