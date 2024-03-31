package mult;

public class Main {
    public static void main(String[] args) {
        // ��һ������ͨ���롢������������ܹ���Ժ���������С�����˰:
        Income[] incomes = new Income[]{
                new Income(3000),
                new Salary(7500),
                new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(totalTax(incomes));
    }

    public static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income : incomes) {
            total = total + income.getTax();
        }
        return total;
    }
}

class Income {
    protected double income;

    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return income * 0.1; // ˰��10%
    }
}

class Salary extends Income {
    public Salary(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

class StateCouncilSpecialAllowance extends Income {
    public StateCouncilSpecialAllowance(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        return 0;
    }
}

class Person {
    protected String name;
    public final String names ;

    public Person(String names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "Person:name=" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            return this.name.equals(p.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public String hello() {
        return "Hello," + name;
    }

}


class Student extends Person {

    public Student(String names) {
        super(names);
    }

    @Override
    public String hello() {
        return super.hello() + "!";
    }
}