package zadanie3;

public abstract class Person {
    private String name;
    private String surname;
    private String pesel;
    private String city;

    public Person(String name, String surname, String pesel, String city) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.city = city;
    }


    // TODO: Brakuje metody abstrakcyjnej!!! Ktora zwraca pensje dla pozniejszych klas np. Student oraz Employee
    // Logika dla tej metody dla studenta powinna zwracac wartosc pola schoolarship
    // Logika dla tej metody dla pracownika powinna zwracac wartosc pola salary
    public abstract double getIncome();

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

    public String getCity() {
        return city;
    }

    public char getGender() {
        char digit = pesel.charAt(9);
        return (digit % 2 == 0) ? 'F' : 'M';
    }
}
