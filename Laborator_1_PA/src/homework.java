import java.util.*;

public class homework {

    public static class Graph {
        private int n;
        private int k;
        private BitSet[] matrice_de_adiacenta;
        private Random rand;

        public Graph(int n, int k, BitSet[] matrice_de_adiacenta) {
            this.n = n;
            this.k = k;
            this.matrice_de_adiacenta = matrice_de_adiacenta;
        }

        public Graph(int n, int k,boolean randomGraph) {
            this.n = n;
            this.k = k;
            this.matrice_de_adiacenta = new BitSet[n];
            for (int i = 0; i < n; i++) {
                matrice_de_adiacenta[i] = new BitSet(n);
            }
            this.rand = new Random();
            if (randomGraph) {
                genereaza_graf_random();
            } else {
                generateGraph();
            }
        }

        private void generateGraph() {
            creaza_clique();
            if (n >= 2 * k) {
                creaza_set_stable();
            }
            addRandomEdges();
        }
        private void genereaza_graf_random() {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (rand.nextBoolean()) {
                        matrice_de_adiacenta[i].set(j);
                        matrice_de_adiacenta[j].set(i);
                    }
                }
            }
        }

        private void creaza_clique() {
            for (int i = 0; i < k; i++) {
                for (int j = i + 1; j < k; j++) {
                    matrice_de_adiacenta[i].set(j);
                    matrice_de_adiacenta[j].set(i);
                }
            }
        }

        private void creaza_set_stable() {
            int start_index = n - k;
            for (int i = start_index; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrice_de_adiacenta[i].clear(j);
                    matrice_de_adiacenta[j].clear(i);
                }
            }
        }

        private void addRandomEdges() {
            for (int i = 0; i < k; i++) {
                for (int j = k; j < n; j++) {
                    if (rand.nextBoolean()) {
                        matrice_de_adiacenta[i].set(j);
                        matrice_de_adiacenta[j].set(i);
                    }
                }
            }
            for (int i = k; i < n-k ; i++) {
                for (int j = 0; j < n ; j++) {
                    if (i != j && rand.nextBoolean()) {
                        matrice_de_adiacenta[i].set(j);
                        matrice_de_adiacenta[j].set(i);
                    }
                }
            }
        }


        public void afiseaza_matrice() {
            String simbol_muchie = "*";
            String simbol_no_muchie = ".";

            StringBuilder sb = new StringBuilder();
            sb.append("  ");
            for (int i = 1; i <= n; i++) {
                sb.append(i).append("  ");
            }
            sb.append("\n");

            for (int i = 0; i < n; i++) {
                sb.append((i + 1)).append("  ");
                for (int j = 0; j < n; j++) {
                    sb.append(matrice_de_adiacenta[i].get(j) ? simbol_muchie : simbol_no_muchie).append("  ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }

        public int numar_muchii() {
            int muchii = 0;
            for (int i = 0; i < n; i++) {
                muchii += matrice_de_adiacenta[i].cardinality();
            }
            return muchii / 2;
        }

        public int numar_maxim_grade() {
            int max_grade = 0;
            for (int i = 0; i < n; i++) {
                int grade = matrice_de_adiacenta[i].cardinality();
                if (grade > max_grade) {
                    max_grade = grade;
                }
            }
            return max_grade;
        }

        public int numar_minim_grade() {
            int min_grade = n;
            for (int i = 0; i < n; i++) {
                int grade = matrice_de_adiacenta[i].cardinality();
                if (grade < min_grade) {
                    min_grade = grade;
                }
            }
            return min_grade;
        }

        public boolean suma_grade() {
            int suma_grade = 0;
            for (int i = 0; i < n; i++) {
                suma_grade += matrice_de_adiacenta[i].cardinality();
            }
            return suma_grade == 2 * numar_muchii();
        }
        public boolean verifica_clique() {
            int[] candidat = new int[k];
            boolean result= gaseste_clique(0, 0, candidat);
            return result;

        }

        private boolean gaseste_clique(int start, int nivel, int[] candidat) {
            if (nivel == k) {
                return true; // Am gasit un clique de dimensiune exact k, ne oprim
            }
            for (int i = start; i < n; i++) {
                boolean isClique = true;
                for (int j = 0; j < nivel; j++) {
                    if (!matrice_de_adiacenta[i].get(candidat[j])) {
                        isClique = false;
                        break;
                    }
                }
                if (isClique) {
                    candidat[nivel] = i;
                    if (gaseste_clique(i + 1, nivel + 1, candidat)) {
                        return true; 
                        
                    }
                }
            }

            return false;
        }
        public boolean verifica_stable_set() {
            Graph graf_invers = inverseaza_graf();
            return graf_invers.verifica_clique();

        }

        private Graph inverseaza_graf() {
            BitSet[] graf_invers = new BitSet[n];
            for (int i = 0; i < n; i++) {
                graf_invers[i] = new BitSet(n);
            }
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++) {
                    if (!matrice_de_adiacenta[i].get(j)) {
                        graf_invers[i].set(j);
                        graf_invers[j].set(i);
                    }
                }
            return new Graph(n,k,graf_invers);

        }
    }
    public static void main(String[] args) {
        long start_time = System.nanoTime();
        if (args.length < 2) {
            System.exit(-1);
        }
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        boolean randomGraph = args.length > 2 && Boolean.parseBoolean(args[2]);

        System.out.println("n =  " + n);
        System.out.println("k = " + k);

        Graph graph = new Graph(n, k, randomGraph);
        if (n <= 30_000) {
            graph.afiseaza_matrice();
        } else {
            long end_time = System.nanoTime();
            long elapsed_time = (end_time - start_time) / 1_000_000;
            System.out.println("Elapsed time: " + elapsed_time + "ms");
            System.out.println("Numarul de muchii este: " + graph.numar_muchii());
            System.out.println("Numarul maxim de grade este: Delta(G)= " + graph.numar_maxim_grade());
            System.out.println("Numarul minim de grade este: δ(G)= " + graph.numar_minim_grade());
            System.exit(0);
        }

        System.out.println("Numarul de muchii este: " + graph.numar_muchii());
        System.out.println("Numarul maxim de grade este: Delta(G)= " + graph.numar_maxim_grade());
        System.out.println("Numarul minim de grade este: δ(G)= " + graph.numar_minim_grade());

        if (graph.suma_grade()) {
            System.out.println("Suma este egala cu 2 * m");
        } else {
            System.out.println("Suma nu este egala cu 2 * m!");
        }

        if (graph.verifica_clique()) {
            System.out.println("Graful contine o clique de dimensiune cel putin " + k + ".");
        } else {
            System.out.println("Graful nu contine o clique de dimensiune cel putin " + k + "!");
        }

        if (graph.verifica_stable_set()) {
            System.out.println("Graful contine un set stabil de dimensiune cel putin " + k + ".");
        } else {
            System.out.println("Graful nu contine un set stabil de dimensiune cel putin " + k + "!");
        }
        long end_time = System.nanoTime();
        long elapsed_time = (end_time - start_time) / 1_000_000;
        System.out.println("Elapsed time: " + elapsed_time + "ms");
    }
}
