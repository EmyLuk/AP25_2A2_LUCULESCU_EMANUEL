import java.util.*;

/**
 * Clasa Bonus implementeaza verificarea potrivirii perfecte bazata pe algoritmul DFS
 * și Teorema lui Hall.
 */
public class Bonus {
    private int studentCount;
    private int projectCount;
    private boolean[][] graph;
    private int[] projectsAssigned;
    private boolean[] visited;

    /**
     * Constructor pentru initializarea unui graf bipartit Student - Proiect
     * @param studentCount numarul de studenti
     * @param projectCount numarul de proiecte
     */
    public Bonus(int studentCount, int projectCount) {
        this.studentCount = studentCount;
        this.projectCount = projectCount;
        this.graph = new boolean[studentCount][projectCount];
        this.projectsAssigned = new int[projectCount];
        Arrays.fill(projectsAssigned, -1);
    }

    /**
     * Adaug o muchie între un student si un proiect
     * @param student indexul studentului
     * @param project indexul proiectului
     */
    public void adaugaMuchie(int student, int project) {
        graph[student][project] = true;
    }

    /**
     * Algoritmul DFS pentru verificarea potrivirii în graf bipartit
     * @param student indexul studentului curent
     * @return true daca studentul poate fi asociat unui proiect
     */
    private boolean dfs(int student) {
        for (int project = 0; project < projectCount; project++) {
            if (graph[student][project] && !visited[project]) {
                visited[project] = true;
                if (projectsAssigned[project] == -1 || dfs(projectsAssigned[project])) {
                    projectsAssigned[project] = student;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determina numarul maxim de potriviri posibile.
     * @return numarul de potriviri realizate
     */
    public int maxPotrivire() {
        int counter = 0;
        for (int student = 0; student < studentCount; student++) {
            visited = new boolean[projectCount];
            if (dfs(student)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Verifica daca se poate face o potrivire perfecta conform Teoremei lui Hall.
     * @return true daca fiecare student poate primi un proiect, altfel false
     */
    public boolean verificarePotrivire() {
        return maxPotrivire() == studentCount;
    }
}
