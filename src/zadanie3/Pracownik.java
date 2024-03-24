package zadanie3;

public class Pracownik extends Osoba{
    private String stanowisko;
    private double pensja;

    public Pracownik(String imie, String nazwisko, String pesel, String miasto, String stanowisko, double pensja) {
        super(imie, nazwisko, pesel, miasto);
        this.stanowisko = stanowisko;
        this.pensja = pensja;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public double getPensja() {
        return pensja;
    }

    @Override
    public char getPlec() {
        return super.getPlec();
    }
}
