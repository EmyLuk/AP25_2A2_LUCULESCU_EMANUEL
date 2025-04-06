package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    public final MainFrame frame;
    JButton loadButton, saveButton, exitButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new FlowLayout());

        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");

        exitButton.addActionListener(this::exitApp);

        add(loadButton);
        add(saveButton);
        add(exitButton);
    }

    private void exitApp(ActionEvent e) {
        System.exit(0);
    }
}
