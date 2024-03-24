package zadanie2;

import java.util.Date;

public class Patient {
    private int id;
    private String nazwisko;
    private String imie;
    private String pesel;
    private Date dataUrodzenia;

    public Patient(int id, String nazwisko, String imie, String pesel, Date dataUrodzenia) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
    }

    public int getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getPesel() {
        return pesel;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

}
