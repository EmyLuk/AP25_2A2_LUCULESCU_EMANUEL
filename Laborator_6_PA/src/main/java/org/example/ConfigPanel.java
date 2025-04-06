package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    JLabel dotsLabel;
    JSpinner dotsInput;
    JButton createButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new FlowLayout());

        dotsLabel = new JLabel("Number of dots:");
        dotsInput = new JSpinner(new SpinnerNumberModel(5, 3, 100, 1));
        createButton = new JButton("New Game");

        createButton.addActionListener(this::createDots);

        add(dotsLabel);
        add(dotsInput);
        add(createButton);
    }

    private void createDots(ActionEvent e) {
        int numDots = (Integer) dotsInput.getValue();
        frame.canvas.generateDots(numDots);
        frame.canvas.repaint();
    }
}
