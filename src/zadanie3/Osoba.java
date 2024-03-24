package zadanie3;

public abstract class Osoba {
    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;

    public Osoba(String imie, String nazwisko, String pesel, String miasto) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.miasto = miasto;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public String getMiasto() {
        return miasto;
    }

    public char getPlec() {
        char cyfra = pesel.charAt(9);
        return (cyfra % 2 == 0) ? 'K' : 'M';
    }
}
