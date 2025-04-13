package org.example;

import java.util.*;

public class Board {
    private final List<String> words = new ArrayList<>();

    public synchronized void addWord(Player player, String word) {
        words.add(word);
        System.out.println("Board: " + player.getName() + " placed word -> " + word);
    }

    public List<String> getWords() {
        return words;
    }
}
