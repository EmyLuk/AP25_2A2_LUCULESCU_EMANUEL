package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel implements Serializable {
    private transient final MainFrame frame;
    private final List<Point> dots = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();
    private transient Point selectedDot = null;
    private boolean isPlayerOneTurn = true;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setBackground(Color.WHITE);
        initListeners();
    }

    private void initListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point clicked = getClickedDot(e.getPoint());
                if (clicked != null) {
                    if (selectedDot == null) {
                        selectedDot = clicked;
                    } else if (!selectedDot.equals(clicked)) {
                        Color color = isPlayerOneTurn ? Color.BLUE : Color.RED;
                        lines.add(new Line(selectedDot, clicked, color));
                        isPlayerOneTurn = !isPlayerOneTurn;
                        selectedDot = null;
                        repaint();
                        compareScores();
                    }
                }
            }
        });
    }

    private Point getClickedDot(Point p) {
        for (Point dot : dots) {
            if (p.distance(dot) <= 10) {
                return dot;
            }
        }
        return null;
    }

    public void generateDots(int count) {
        dots.clear();
        lines.clear();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(getWidth() - 40) + 20;
            int y = rand.nextInt(getHeight() - 40) + 20;
            dots.add(new Point(x, y));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
        }

        g.setColor(Color.BLACK);
        for (Point dot : dots) {
            g.fillOval(dot.x - 5, dot.y - 5, 10, 10);
        }
    }

    /** Export tabla de joc ca imagine PNG */
    public void exportToPNG(String filename) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        printAll(g2d);
        g2d.dispose();
        try {
            File file = new File(filename);
            javax.imageio.ImageIO.write(image, "png", file);
            System.out.println("PNG exported to: " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting PNG: " + e.getMessage());
        }
    }

    /** Salvez starea curenta a jocului */
    public void saveGame(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(dots);
            out.writeObject(lines);
            System.out.println("Game saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e);
        }
    }

    /** Incarca starea jocului salvat anterior */
    public void loadGame(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            dots.clear();
            dots.addAll((List<Point>) in.readObject());
            lines.clear();
            lines.addAll((List<Line>) in.readObject());
            repaint();
            System.out.println("Game loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e);
        }
    }

    /** Verifica si compara scorurile jucatorilor */
    private void compareScores() {
        double player1 = lines.stream()
                .filter(l -> l.color.equals(Color.BLUE))
                .mapToDouble(Line::length)
                .sum();

        double player2 = lines.stream()
                .filter(l -> l.color.equals(Color.RED))
                .mapToDouble(Line::length)
                .sum();

        System.out.printf(" Player 1 Score: %.2f | Player 2 Score: %.2f%n", player1, player2);

        if (isConnected()) {
            if (player1 < player2) {
                System.out.println("Player 1 connected all dots with a better score!");
            } else if (player2 < player1) {
                System.out.println("Player 2 connected all dots with a better score!");
            } else {
                System.out.println(" It's a tie!");
            }
        }
    }

    /** Verifica daca toate punctele sunt conectate */
    private boolean isConnected() {
        if (dots.isEmpty()) return false;

        Map<Point, List<Point>> adj = new HashMap<>();
        for (Point p : dots) adj.put(p, new ArrayList<>());

        for (Line l : lines) {
            adj.get(l.p1).add(l.p2);
            adj.get(l.p2).add(l.p1);
        }

        Set<Point> visited = new HashSet<>();
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(dots.getFirst());

        while (!stack.isEmpty()) {
            Point current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                stack.addAll(adj.get(current));
            }
        }

        return visited.size() == dots.size();
    }

    /** Clasa linie intre doua puncte - serializabila */
    static class Line implements Serializable {

        Point p1, p2;
        Color color;

        public Line(Point p1, Point p2, Color color) {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
        }

        public double length() {
            return p1.distance(p2);
        }
    }
}
