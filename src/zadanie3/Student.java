package zadanie3;

public class Student extends Osoba{
    private String grupa;
    private double stypendium;

    public Student(String imie, String nazwisko, String pesel, String miasto, String grupa, double stypendium) {
        super(imie, nazwisko, pesel, miasto);
        this.grupa = grupa;
        this.stypendium = stypendium;
    }

    public String getGrupa() {
        return grupa;
    }

    public double getStypendium() {
        return stypendium;
    }

    @Override
    public char getPlec() {
        return super.getPlec();
    }
}
