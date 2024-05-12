package zadanie3;

public class Main {
    public static void main(String[] args) {
        Person[] people = {
                new Student("Jan", "Kowalski", "98010112345", "Warszawa", "A123", 1500),
                new Student("Anna", "Nowak", "95020254321", "Kraków", "B456", 1200),
                new Employee("Adam", "Nowicki", "91030398765", "Gdańsk", "Programista", 5000),
                new Employee("Ewa", "Lewandowska", "88040487654", "Poznań", "Księgowy", 4500)
        };

        // Znajdź osobę z największym dochodem (stypendium lub pensja)
        Person personWithTheHighestIncome = findPersonWithTheHighestIncome(people);
        System.out.println("Osoba z największym dochodem: " + personWithTheHighestIncome.getName() + " " + personWithTheHighestIncome.getSurname());

        // Policzyć ile jest kobiet w tablicy
        int numberOfWomens = countWomen(people);
        System.out.println("Liczba kobiet w tablicy: " + numberOfWomens);
    }

    private static Person findPersonWithTheHighestIncome(Person[] people) {
        Person personWithTheHighetIncome = people[0];

        for (Person person : people) {
            if (person.getIncome() > personWithTheHighetIncome.getIncome()) {
                personWithTheHighetIncome = person;
            }
        }

        return personWithTheHighetIncome;
    }

    private static int countWomen(Person[] people) {
        int numberOfWomen = 0;

        for (Person person : people) {
            if (person.getGender() == 'F') {
                numberOfWomen++;
            }
        }

        return numberOfWomen;
    }
}
