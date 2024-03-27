package oop;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        City bj = new City();
        bj.name = "Beijing";
        bj.latitude = 39.903;
        bj.longitude = 116.401;
        System.out.println(bj.name);
        System.out.println("location:" + bj.latitude + "," + bj.longitude);

        Person p = new Person();
        int n = 15;
        p.setAge(n);
        System.out.println(p.getAge());
        n = 20;
        System.out.println(p.getAge());


        String[] fullname = new String[]{
                "homer",
                "Simpson"
        };
        var q = new Group();
        q.setNames(fullname);
        System.out.println(q.getNames());
        fullname[0] = "Bart";
        System.out.println(q.getNames());

        var g = new Group();
        String bob = "bob";

        g.setName(bob);
        System.out.println(g.getName());
        bob = "Alice";
        System.out.println(g.getName());

        String s = "Test string";
        int n1 = s.indexOf('t');
        int n2 = s.indexOf("st");
        int n3 = s.indexOf("st", 4);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);

        p.setName("aa","bb");
        System.out.println(p.getName());

        System.out.println(p instanceof Person);
        System.out.println(p instanceof Student);
        var s1 = new  Student();
        System.out.println(s1 instanceof Person);
        System.out.println(s1 instanceof Student);
        Student s2 = null;
        System.out.println(s2 instanceof Student);
        Person p1 = new Student();
        if (p1 instanceof Student){
            Student p2 = (Student) p1;
        }
        Object obj = "hello";
        if (obj instanceof String s4){
            System.out.println(s4.toUpperCase());
        }


    }
}

class Student extends Person{
    protected int score;
    public Student(){}
    public Student(String name, int age, int score){
        super();
        this.score = score;
    }
}
class City {
    public String name;
    public double latitude;
    public double longitude;

}

class Person {
    private String name;
    protected int age;
    protected int birth;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String... fullName) {
        this.name = String.join(",",fullName);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("invalid name");
        }
        this.name = name.strip();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }

    public void setNameAndAge(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

class Group {
    private String[] names;
    private String name;

    public void setNames(String... name) {
        this.names = name;
    }

    public String getNames() {
        return this.names[0] + " " + this.names[1];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}