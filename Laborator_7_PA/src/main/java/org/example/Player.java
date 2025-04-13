package org.example;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Player implements Runnable {
    private final String name;
    private final Game game;
    private int score = 0;
    private final List<Character> lettersDrawn = new ArrayList<>();

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    @Override
    public void run() {
        while (!game.getBag().isEmpty()) {
            List<Tile> extracted = game.getBag().extractTiles(1);

            if (extracted.isEmpty()) break;
            for (Tile tile : extracted) {
                lettersDrawn.add(tile.getLetter());
                score += tile.getPoints();
            }

            System.out.println(name + " drew: " + extracted.get(0).getLetter() +
                    " (Points: " + extracted.get(0).getPoints() + ")");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(name + " finished with total score: " + score);
        System.out.println("Letters drawn by " + name + ": " + lettersDrawn);
    }
}
