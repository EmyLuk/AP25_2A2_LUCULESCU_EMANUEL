package org.example;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Alice", game));
        game.addPlayer(new Player("Bob", game));
        game.start();
    }
}
