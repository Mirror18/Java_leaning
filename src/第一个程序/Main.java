package 第一个程序;
//public class 第一个程序.Main {
//    public static void main(String[] args) {
//        int n = 100;
//        int sum = 0;
//        for (int i = 1; i<=n;i++){
//            sum += i;
//        }
//        System.out.println(sum);
//    }
//}

//递归

//public class 第一个程序.Main {
//    public static void main(String[] args) {
//        int sum = sumNum(100);
//        System.out.println(sum);
//        System.out.println(sum == 5050 ? "测试通过" : "测试失败");
//
//        double x = 1.0 / 10;
//        double y = 1 - 9.0 / 10;
//        // 观察x和y是否相等:
//        System.out.println(x);
//        System.out.println(y);
//    }
//
//    private static int sumNum(int n) {
//        int sum = 0;
//        if (n >= 0) {
//            sum = sumNum(n - 1) + n;
//        }
//        return sum;
//    }
//}
public class Main {
    public static void main(String[] args) {
        int n = 5;
        double d = 1.2 + 24.0 / n; // 6.0
        System.out.println(d);
        d = 1.2 + 24 / 5; // 5.2
        System.out.println(d);
        System.out.println(0.0 / 0);
        System.out.println(1.0 / 0);
        System.out.println(-1.0 / 0);


        System.out.println((int) 12.3);
        System.out.println((int) 12.7);
        System.out.println((int) -12.7);
        System.out.println((int) (12.7 + 0.5));
        System.out.println((int) 1.2e20);


        double a = 1.0;
        double b = 3.0;
        double c = -4.0;
        double r1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
        double r2 = (-b - Math.sqrt(b * b - 4 * a * c) )/ (2 * a);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r1 == 1 && r2 == -4 ? "测试通过" : "测试失败");

        int age = 7;
        boolean isPrimaryStudent  = age >= 6 && age <= 12;
        System.out.println(isPrimaryStudent ? "yes" : "No");
    }
}