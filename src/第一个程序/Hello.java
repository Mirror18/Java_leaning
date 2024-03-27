package 第一个程序;

import java.util.Arrays;

public class Hello {
    public static void main(String[] args) {
        System.out.println("第一个程序.Hello,world");
        int n = 100;
        System.out.println("n=" + n);
        n = 200;
        System.out.println("n=" + n);
        int x = n;
        System.out.println("x=" + x);
        x += 1;
        System.out.println("x=" + x);
        System.out.println("n=" + n);
        //整形
        int i = 2147483647;
        int i2 = -2147483648;
        int i3 = 2_000_000_000; // 加下划线更容易识别
        int i4 = 0xff0000; // 十六进制表示的16711680
        int i5 = 0b1000000000; // 二进制表示的512

        long n1 = 9000000000000000000L; // long型的结尾需要加L
        long n2 = 900; // 没有加L，此处900为int，但int类型可以赋值给long
        // int i6 = 900L; // 错误：不能把long型赋值给int

        //浮点型
        float f1 = 3.14f;
        float f2 = 3.14e38f; // 科学计数法表示的3.14x10^38
        //float f3 = 1.0; // 错误：不带f结尾的是double类型，不能赋值给float

        double d = 1.79e308;
        double d2 = -1.79e308;
        double d3 = 4.9e-324; // 科学计数法表示的4.9x10^-324

        //布尔类型
        boolean b1 = true;
        boolean b2 = false;
        boolean isGreater = 5 > 3; // 计算结果为true
        int age = 12;
        boolean isAdult = age >= 18; // 计算结果为false

        //字符类型
        char a = 'A';
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);

        //引用类型
        String s = "第一个程序.Hello";
        // 引用类型 --//数组类型
        int[] ns = new int[5];
        int[] ns1 = new int[] { 68, 79, 91, 85, 62 };
        int[] ns2 = { 68, 79, 91, 85, 62 };
        String[] names = {"ABC", "XYZ", "zoo"};

        //常量
        final double PI = 3.14;
        double r = 5.0;
        double area = PI * r * r;
        //PI = 300;

        //var关键字
        StringBuilder sb = new StringBuilder();
        //等同于
        var sb1 = new StringBuilder();
        // 整数运算
        int i1 = (100+200)*(99-88);//3300
        int n3 = 7 * (5+(i1-9));
        System.out.println(i1);
        System.out.println(n3);

        int x1 = 214783640;
        int y = 15;
        int sum = x1 + y;
        System.out.println(sum);

        long x2 = 2147483640;
        long y1 = 15;
        long sum1 = x2 + y1;
        System.out.println(sum1); // 2147483655

        int n4 = 3300;
        n4++; // 3301, 相当于 n = n + 1;
        n4--; // 3300, 相当于 n = n - 1;
        int y2 = 100 + (++n4); // 不要这么写
        System.out.println(y2);

        short s1 = 1234;
        int i6 = 123456;
        int x3 = s1 + i; // s自动转型为int
        //short y3 = s1 + i6; // 编译错误!

        int i7 = 1234567;
        short s2 = (short) i7; // -10617
        System.out.println(s2);
        int i8 = 12345678;
        short s3 = (short) i8; // 24910
        System.out.println(s3);


        //字符类型
        char c1 = 'A';
        char c2 = '中';
        System.out.println(c1);
        System.out.println(c2);

        int n5 = 'A';
        int n6 = '中';
        System.out.println(n5);
        System.out.println(n6);

        char c3 = '\u0041';
        char c4 = '\u4e2d';
        System.out.println(c3);
        System.out.println(c4);

        //字符串类型
        //不可变形
        String s5 = "";
        String s6 = "A";
        String s7 = "ABC";
        String s8 = "中文 ABC";
        String s9 = "abc\"xyz";
        String s10 = "abc\\xyz";
        System.out.println(s5);
        System.out.println(s6);
        System.out.println(s7);
        System.out.println(s8);
        System.out.println(s9);
        System.out.println(s10);

        //字符串的链接
        String s11 = "第一个程序.Hello";
        String s12 = "world";
        String s13 = s11 + " " + s12 + "!";
        System.out.println(s13);

        //有其他类型先转换为字符串
        int age1 = 25;
        String s14 = "age is " + age1;
        System.out.println(s14);

        //多行字符串
        String s15 = """
                   SELECT * FROM
                     users
                   WHERE id > 100
                   ORDER BY name DESC
                   """;
        System.out.println(s15);

        //不可变性
        String s16 = "hello";
        String t = s;
        s = "world";
        System.out.println(t); // t是"hello"还是"world"?

        int a1 = 72;
        int b = 105;
        int c = 65281;
        // FIXME:
        String s17 = String.valueOf(a1) + ' ' + (char)b + " "+c;
        System.out.println(s17);


        String[] names1 = {"ABC", "XYZ", "zoo"};
        String s20 = names[1];
        names1[1] = "cat";
        System.out.println(s20); // s是"XYZ"还是"cat"?
        System.out.println(Arrays.toString(names1));
    }
}
