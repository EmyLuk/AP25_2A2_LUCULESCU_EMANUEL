//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Laborator_1_PA {
    public static void main(String[] args) {
        Laborator_1_PA Laborator_1_PA = new Laborator_1_PA();
        Laborator_1_PA.compulsory();
    }
    void compulsory() {
        System.out.println("Hello World");
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);
        System.out.println("The random number is " + n);

        int result = n * 3;
        result = result + 0b10101;
        result = result + 0xFF;
        result = result * 6;

        System.out.println("The new number is " + result);

        while(result >= 10){
            int sum=0;
            while(result > 0){
                result = result %10;
                result = result / 10;
            }
            result = sum;
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}



