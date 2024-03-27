package iostream;

import java.util.Arrays;

public class ostream {
    public static void main(String[] args) {
        String fruit = "orange";
        int opt = switch (fruit) {
            case "apple" -> 1;
            case "pear", "mango" -> 2;
            default -> {
                int code = fruit.hashCode();
                yield code;
            }
        };
        System.out.println("opt =" + opt);


        int sum = 0;
        int m = 20;
        int n = 100;
        // 使用while计算M+...+N:
        while (m < n) {
            sum += 1;
            m++;
        }
        System.out.println(sum);

        int sum1 = 0;
        int m1 = 20;
        int n1 = 100;
        // 使用do while计算M+...+N:
        do {

            sum1 += 1;
            m1++;
        } while (m1 < n1);
        System.out.println(sum1);


        int[] ns = {1, 4, 9, 16, 25};
        for (int i = 0; i < ns.length; i++) {
            System.out.print(ns[i] + " ");
        }
        System.out.println();
        //等价于
        for (int n3 : ns) {
            System.out.print(n3 + " ");
        }
        System.out.println();

        for (int i = ns.length - 1; i >= 0; i--) {
            System.out.print(ns[i] + " ");
        }
        System.out.println();


        int sum2 = 0;
        for (int n4 : ns) {
            sum2 += n4;
        }
        System.out.println(sum2);
        System.out.println();

        double pi = 0;
        boolean a = true;
        for (double i = 1; i < 1000; i += 2) {
            if (a) {
                pi = pi + 1 / i;
                a = false;
            } else {
                pi = pi - 1 / i;
                a = true;
            }
        }
        System.out.printf("%.2f", pi * 4);

        int[] ns1 = {1, 1, 2, 3, 5, 8};
        for (int n2 : ns) {
            System.out.print(n2 + ",");
        }
        System.out.println();
        //等同于
        System.out.println(Arrays.toString(ns1));


    }
}

