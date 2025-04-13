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

        // Label + Spinner pentru nr. de puncte
        dotsLabel = new JLabel("Number of dots:");
        dotsInput = new JSpinner(new SpinnerNumberModel(5, 3, 100, 1));

        // Butoane
        createButton = new JButton("New Game");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton exportButton = new JButton("Export PNG");
        JButton exitButton = new JButton("Exit");

        // Actiuni pe butoane
        createButton.addActionListener(this::createDots);
        saveButton.addActionListener(e -> frame.canvas.saveGame("game.ser"));
        loadButton.addActionListener(e -> frame.canvas.loadGame("game.ser"));
        exportButton.addActionListener(e -> frame.canvas.exportToPNG("board.png"));
        exitButton.addActionListener(e -> System.exit(0));

        // Adaugare in panel (ordine logica)
        add(dotsLabel);
        add(dotsInput);
        add(createButton);
        add(saveButton);
        add(loadButton);
        add(exportButton);
        add(exitButton);
    }

    private void createDots(ActionEvent e) {
        int numDots = (Integer) dotsInput.getValue();
        frame.canvas.generateDots(numDots);
        frame.canvas.repaint();
    }
}
