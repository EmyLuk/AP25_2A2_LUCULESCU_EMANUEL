package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    public final MainFrame frame;
    private final List<Point> dots = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setBackground(Color.WHITE);
    }
    public void generateDots(int count) {
        dots.clear();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(getWidth() - 40) + 20;
            int y = rand.nextInt(getHeight() - 40) + 20;
            dots.add(new Point(x, y));
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Point dot : dots) {
            g.fillOval(dot.x - 5, dot.y - 5, 10, 10);
        }
    }
}
