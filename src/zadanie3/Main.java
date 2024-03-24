package zadanie3;

public class Main {
    public static void main(String[] args) {
        Osoba[] osoby = {
                new Student("Jan", "Kowalski", "98010112345", "Warszawa", "A123", 1500),
                new Student("Anna", "Nowak", "95020254321", "Kraków", "B456", 1200),
                new Pracownik("Adam", "Nowicki", "91030398765", "Gdańsk", "Programista", 5000),
                new Pracownik("Ewa", "Lewandowska", "88040487654", "Poznań", "Księgowy", 4500)
        };

        // Znajdź osobę z największym dochodem (stypendium lub pensja)
        Osoba osobaZNajwiekszymDochodem = znajdzOsobeZNajwiekszymDochodem(osoby);
        System.out.println("Osoba z największym dochodem: " + osobaZNajwiekszymDochodem.getImie() + " " + osobaZNajwiekszymDochodem.getNazwisko());

        // Policzyć ile jest kobiet w tablicy
        int liczbaKobiet = policzKobiety(osoby);
        System.out.println("Liczba kobiet w tablicy: " + liczbaKobiet);
    }

    private static Osoba znajdzOsobeZNajwiekszymDochodem(Osoba[] osoby) {
        Osoba osobaZNajwiekszymDochodem = null;
        double najwiekszyDochod = Double.MIN_VALUE;

        for (Osoba osoba : osoby) {
            double dochod = 0;

            if (osoba instanceof Student) {
                dochod = ((Student) osoba).getStypendium();
            } else if (osoba instanceof Pracownik) {
                dochod = ((Pracownik) osoba).getPensja();
            }

            if (dochod > najwiekszyDochod) {
                najwiekszyDochod = dochod;
                osobaZNajwiekszymDochodem = osoba;
            }
        }

        return osobaZNajwiekszymDochodem;
    }

    private static int policzKobiety(Osoba[] osoby) {
        int liczbaKobiet = 0;

        for (Osoba osoba : osoby) {
            if (osoba.getPlec() == 'K') {
                liczbaKobiet++;
            }
        }

        return liczbaKobiet;
    }
}
