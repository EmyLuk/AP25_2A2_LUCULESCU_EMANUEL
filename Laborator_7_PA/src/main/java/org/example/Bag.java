    package org.example;

    import java.util.*;

    public class Bag {
        private final List<Tile> tiles = new ArrayList<>();
        private final Random random = new Random();

        public Bag() {
            for (char c = 'A'; c <= 'Z'; c++) {
                for (int i = 0; i < 10; i++) {
                    tiles.add(new Tile(c, random.nextInt(10) + 1));
                }
            }
            Collections.shuffle(tiles);
        }

        public synchronized List<Tile> extractTiles(int howMany) {
            List<Tile> result = new ArrayList<>();
            for (int i = 0; i < howMany && !tiles.isEmpty(); i++) {
                result.add(tiles.remove(tiles.size() - 1));
            }
            return result;
        }

        public synchronized boolean isEmpty() {
            return tiles.isEmpty();
        }
    }
