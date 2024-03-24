package zadanie2;

import java.util.Date;

public class Doctor {
    private int id;
    private String nazwisko;
    private String imie;
    private String specjalnosc;
    private Date dataUrodzenia;
    private String nip;
    private String pesel;

    public Doctor(int id, String nazwisko, String imie, String specjalnosc, Date dataUrodzenia, String nip, String pesel) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.specjalnosc = specjalnosc;
        this.dataUrodzenia = dataUrodzenia;
        this.nip = nip;
        this.pesel = pesel;
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

    public String getSpecjalnosc() {
        return specjalnosc;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getNip() {
        return nip;
    }

    public String getPesel() {
        return pesel;
    }
}
