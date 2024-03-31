package iostream;

import java.util.Arrays;

public class bbb {
    public static void main(String[] args) {
        int[] ns = {28, 12, 89, 73, 65, 18, 96, 50, 8, 36};
        for (int i = 0; i < ns.length; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j] > ns[j + 1]) {
                    int temp = ns[j];
                    ns[j] = ns[j + 1];
                    ns[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(ns));

        int[] ns1 = {28, 12, 89, 73, 65, 18, 96, 50, 8, 36};
        Arrays.sort(ns1);
        System.out.println(Arrays.toString(ns1));

        for (int i = 0; i < ns.length; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j] < ns[j + 1]) {
                    int temp = ns[j];
                    ns[j] = ns[j + 1];
                    ns[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(ns));

        int[][] ns2 = {
                {1, 2, 3, 4},
                {5, 6},
                {7, 8, 9}
        };
        for (int[] arr : ns2) {
            for (int n : arr) {
                System.out.print(n);
                System.out.print(',');
            }
        }

        //或者这样
        System.out.println(Arrays.deepToString(ns2));

        int[][] scores = {
                { 82, 90, 91 },
                { 68, 72, 64 },
                { 95, 91, 89 },
                { 67, 52, 60 },
                { 79, 81, 85 },
        };
        double average = 0;
        for(int[] ns4 : scores){
            for(int a : ns4){
                average += a;
            }
        }
        System.out.println(average/(scores.length* scores[0].length));

        if (Math.abs(average - 77.733333) < 0.000001) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }

        for (String arg : args) {
            if ("-version".equals(arg)) {
                System.out.println("v 1.0");
                break;
            }
        }


    }
}
