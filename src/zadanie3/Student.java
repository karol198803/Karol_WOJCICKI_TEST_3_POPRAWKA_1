package zadanie3;

public class Student extends Person {
    private String group;
    private double scholarship;

    public Student(String name, String surname, String pesel, String city, String group, double scholarship) {
        super(name, surname, pesel, city);
        this.group = group;
        this.scholarship = scholarship;
    }

    @Override
    public double getIncome() {
        return getScholarship();
    }

    public String getGroup() {
        return group;
    }

    public double getScholarship() {
        return scholarship;
    }

    @Override
    public char getGender() {
        return super.getGender();
    }
}
