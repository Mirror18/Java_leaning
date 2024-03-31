package abstract_class;

public class Main {
    public static void main(String[] args) {
        //var ca = new career();
    }
}
class Person {
    public String name;
    public void run(){

    }

}
class Student extends Person{
    @Override
    public void run(){}

}
abstract class career {
    public abstract void run();
}
class Teacher extends career{
    @Override
    public void run() {
    }
}

interface  employee {
    public static final int MALE = 1;
    int FEMALE =2;
    String getName();
    default void run(){
        System.out.println(getName()+"run");
    }
}

class NiuMa extends Person implements employee{
    public String getName(){
        return "mirror";
    }
}