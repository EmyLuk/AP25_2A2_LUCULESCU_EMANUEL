package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;

    DrawingPanel canvas;

    public MainFrame() {
        super("Dots Game");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);


        setVisible(true);
    }

}
