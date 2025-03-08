
import java.util.*;

public class Compulsory {


    public enum Proiect_tip {
        teoretic,
        practic
    }

    public static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Student)) {
                return false;
            }
            Student other = (Student) obj;
            return name.equals(other.name);

        }
    }
    public static class Project {
        private int id;
        private Proiect_tip tip;

        public Project(int id, Proiect_tip tip) {
            this.id = id;
            this.tip = tip;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Proiect_tip getTip() {
            return tip;
        }

        public void setTip(Proiect_tip tip) {
            this.tip = tip;
        }

        @Override
        public String toString() {
            return id + " " + tip;
        }

    }




    public static void main(String[] args) {

        var p1 = new Project(1, Proiect_tip.teoretic);
        var student = new Student("Emi");
        var student2 = new Student("Emi");
        var student3 = new Student("Alex");
        System.out.println(p1);
        System.out.println(student);
        System.out.println("student1 equals student2: " + student.equals(student2));
        System.out.println("student1 equals student3: " + student.equals(student3));
    }

}
