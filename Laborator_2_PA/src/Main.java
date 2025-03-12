import java.util.Arrays;

/**
 * Reprezinta o persoana generica, ce are un nume si o data de nastere
 */
class Person {
    private String name;
    private String dataDeNastere;

    /**
     * Constructor pentru clasa Person
     * @param name Numele persoanei
     * @param dataDeNastere data de nastere
     */

    public Person(String name, String dataDeNastere) {
        this.name = name;
        this.dataDeNastere = dataDeNastere;
    }

    public String getName() {
        return name;
    }

    public String getDataDeNastere() {
        return dataDeNastere;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return name.equals(person.name) && dataDeNastere.equals(person.dataDeNastere);
    }
}

/**
 * Reprezinta un student, care de asemenea este persoana avand un nr de inreg.
 */

class Student extends Person {
    private int numarInregistrare;
    private Project proiectAlocat;

    /**
     * Constructor pentru clasa Student
     * @param name numele studentului
     * @param dateOfBirth data de nastere
     * @param registrationNumber nr de inregistrare
     */

    public Student(String name, String dateOfBirth, int registrationNumber) {
        super(name, dateOfBirth);
        this.numarInregistrare = registrationNumber;
        this.proiectAlocat=null;
    }


    public void setProiectAlocat(Project project){
        this.proiectAlocat=project;
    }
    public Project getProiectAlocat(){
        return proiectAlocat;
    }
    public int getNumarInregistrare() {
        return numarInregistrare;
    }


    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Student student = (Student) obj;
        return numarInregistrare == student.numarInregistrare;
    }
}

/**
 * Reprezinta un proiect propus de un profesor
 */

class Project {
    private String title;
    private proiectTip tip;
    private boolean esteAlocat;

    /**
     * Am folosit enum pentru tipul proiectului
     */

    public enum proiectTip{
        teoretic,
        practic
    }

    /**
     * Constructor pentru clasa Proiect
     * @param title titlul proiectului
     * @param tip tipul proiectului (teoretic sau practic)
     */
    public Project(String title, proiectTip tip) {
        this.title = title;
        this.tip = tip;
        this.esteAlocat = false;
    }

    public String getTitle() {
        return title;
    }
    public proiectTip getTip() {
        return tip;
    }

    /**
     * Verifica daca proiectul este alocat
     * @return returneaza true daca da, altfel false
     */
    public boolean esteAlocat() {
        return esteAlocat;
    }

    /**
     * Marchez proiectul ca fiind alocat
     */
    public void alocat(){
        esteAlocat = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return title.equals(project.title);
    }
}

/**
 * Reprezinta un profesor, care la randul lui este o persoana si propune proiecte.
 */

class Teacher extends Person {
    private Project[] projects;
    private int projectCount = 0;

    /**
     * Constructor pentru clasa Teacher
     * @param name numele profesorului
     * @param dataDeNastere data de nastere a profesorului
     * @param maxProjects nr maxim de proiect pe care le poate propune
     */
    public Teacher(String name, String dataDeNastere, int maxProjects) {
        super(name, dataDeNastere);
        this.projects = new Project[maxProjects];
        this.projectCount = 0;
    }

    /**
     * Adaug proiectul la lista de proiecte ale prof
     * @param project proiectul care este adaugat
     */

    public void addProject(Project project) {
        for (int i = 0; i < projectCount; i++) {
            if (projects[i].equals(project)) return;
        }
        if (projectCount < projects.length) {
            projects[projectCount++] = project;
        }
    }

    /**
     * Returneaza toate proiectele propuse de profesor
     * @return
     */
    public Project[] getProjects() {
        return Arrays.copyOf(projects, projectCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Teacher teacher = (Teacher) obj;
        return Arrays.equals(getProjects(), teacher.getProjects());
    }
}



/**
 * Problema de alocare a proiectelor catre studenti
 */

class Problem {
    private Student[] students;
    private Teacher[] teachers;
    private int studentCount = 0;
    private int teacherCount = 0;

    /**
     * Constructor pentru clasa Problem
     *
     * @param maxStudents nr maxim de studenti
     * @param maxTeachers nr maxim de prof
     */

    public Problem(int maxStudents, int maxTeachers) {
        this.students = new Student[maxStudents];
        this.teachers = new Teacher[maxTeachers];
    }

    /**
     * Adaug studentul in instanta problemei
     *
     * @param student
     */

    public void addStudent(Student student) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].equals(student)) return; /// sa nu adaug de doua ori acelasi student
        }
        if (studentCount < students.length) {
            students[studentCount++] = student;
        }
    }

    /**
     * Adaug profesorul la fel
     *
     * @param teacher
     */

    public void addTeacher(Teacher teacher) {
        for (int i = 0; i < teacherCount; i++) {
            if (teachers[i].equals(teacher)) return; ///sa nu adaug de doua ori acelasi profesor
        }
        if (teacherCount < teachers.length) {
            teachers[teacherCount++] = teacher;
        }
    }

    /**
     * Cu ajutorul alg Greedy se aloca proiectele studentilor
     */
    public void proiecteAlocate() {
        for (int i = 0; i < studentCount; i++) { /// parcurgem fiecare student
            if (students[i] == null) {
                continue;
            }
            for (int j = 0; j < teacherCount; j++) {/// parcurgem fiecare profesor
                if (students[j] == null) {
                    continue;
                }
                Project[] projects = teachers[j].getProjects();
                for (int k = 0; k < projects.length; k++) { ///  parcurgem fiecare proiect al profesorului
                    if (!projects[k].esteAlocat()) {
                        students[i].setProiectAlocat(projects[k]);
                        projects[k].alocat();
                        break;
                    }
                }
                if (students[i].getProiectAlocat() != null) {
                    break; ///daca studentul are un proiect deja alocat, iesim
                }
            }
        }
    }

    /**
     * Verifica daca se poate realiza o potrivire perfecta intre studenti si proiecte, conform teoremei lui Hall.
     * @return true daca potrivirea perfecta este posibila, false altfel.
     */

    public boolean verificarePotrivire() {
        int totalProjects = 0;
        for (int j = 0; j < teacherCount; j++) {
            totalProjects += teachers[j].getProjects().length;
        }
        Bonus bonus = new Bonus(studentCount, totalProjects);
        int projectIndex = 0;
        for (int j = 0; j < teacherCount; j++) {
            Project[] projects = teachers[j].getProjects();
            for (int k = 0; k < projects.length; k++) {
                if (projectIndex >= totalProjects) break;

                for (int i = 0; i < studentCount; i++) {
                    bonus.adaugaMuchie(i, projectIndex);
                }
                projectIndex++;
            }
        }
        return bonus.verificarePotrivire();
    }

}

/**
 * Clasa principala main pentru rulare si testare
 */
public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem(10, 5);

        var s1 = new Student("Emanuel", "2004-02-07", 101);
        var s2 = new Student("Andrei", "2002-04-22", 102);
        var s3 = new Student("Gabriel", "2005-06-10", 103);
        var s4 = new Student("Anca", "1979-01-07", 104);
        var s5 = new Student("Paul", "1978-12-21", 105);
        var s6 = new Student("Mihai", "1979-02-22", 106);

        problem.addStudent(s1);
        problem.addStudent(s2);
        problem.addStudent(s3);
        problem.addStudent(s4);
        problem.addStudent(s5);
        problem.addStudent(s6);

        var t1 = new Teacher("Dr. Alex", "1975-04-20", 2);
        var t2 = new Teacher("Dr. Irimia", "1980-09-30", 3);
        var t3 = new Teacher("Dr. Ion", "1980-09-30", 2);
        problem.addTeacher(t1);
        problem.addTeacher(t2);
        problem.addTeacher(t3);

        var p1 = new Project("AI Research", Project.proiectTip.practic);
        var p2 = new Project("Quantum Computing", Project.proiectTip.teoretic);
        var p3 = new Project("Java", Project.proiectTip.practic);
        var p4 = new Project("Sisteme de operare", Project.proiectTip.teoretic);
        var p5 = new Project("OOP", Project.proiectTip.practic);
        var p6 = new Project("Web", Project.proiectTip.practic);
        var p7 = new Project("Matematica", Project.proiectTip.practic);

        t1.addProject(p1);
        t2.addProject(p2);
        t3.addProject(p3);
        t2.addProject(p4);
        t2.addProject(p5);
        t3.addProject(p6);
        t1.addProject(p7);
        long startTime = System.nanoTime();
        boolean feasible = problem.verificarePotrivire();
        long endTime = System.nanoTime();
        System.out.println("Se poate face o potrivire perfecta? " + (feasible ? "True" : "False"));
        System.out.println("Timp de executie: " + (endTime - startTime) / 1_000_000 + " ms");
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memorie utilizata: " + (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");
//        problem.proiecteAlocate();
//
//        System.out.println("\nAlocarea proiectelor:");
//        for (Student student : new Student[]{s1, s2, s3, s4, s5, s6}) {
//            if (student.getProiectAlocat() != null) {
//                System.out.println(student.getName() + " -> " +
//                        student.getProiectAlocat().getTitle() + ", " +
//                        student.getProiectAlocat().getTip());
//            } else {
//                System.out.println(student.getName() + " -> Fara proiect");
//            }
//        }

    }
}
