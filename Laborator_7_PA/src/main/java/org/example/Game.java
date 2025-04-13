package org.example;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Getter
@Setter
@ToString


public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void start() {
        List<Thread> threads = new ArrayList<>();
        for (Player p : players) {
            Thread t = new Thread(p);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join(); //pentru a astepta sa se termine la fiecare thread urile
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Game ended. Words on board:");
        for (String word : board.getWords()) {
            System.out.println(" - " + word);
        }
    }

}
