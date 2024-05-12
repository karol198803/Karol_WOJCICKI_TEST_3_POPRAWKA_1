package zadanie3;

public class Employee extends Person {
    private String position;
    private double salary;

    public Employee(String name, String surname, String pesel, String city, String position, double salary) {
        super(name, surname, pesel, city);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double getIncome() {
        return getSalary();
    }

    @Override
    public char getGender() {
        return super.getGender();
    }
}
