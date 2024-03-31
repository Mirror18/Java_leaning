package iostream;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("input your name: ");
        String name = scanner.nextLine();
        System.out.print("input your age:");
        int age = scanner.nextInt();
        System.out.printf("hi ,%s, you are %d\n" ,name, age);
    }
}
