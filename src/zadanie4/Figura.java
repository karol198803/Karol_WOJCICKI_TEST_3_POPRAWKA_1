package zadanie4;


public abstract class Figura {
    private static int liczbaFigur = 0;

    protected int numerFigury;

    public Figura() {
        this.numerFigury = liczbaFigur++;
    }

    public static Kwadrat stworzKwadrat(double bok) {
        return new Kwadrat(bok);
    }

    public static Kolo stworzKolo(double promien) {
        return new Kolo(promien);
    }

    public static Prostokat stworzProstokat(double bokA, double bokB) {
        return new Prostokat(bokA, bokB);
    }

    public abstract double obwod();

    public abstract double pole();

    @Override
    public String toString() {
        return "Figura nr " + numerFigury + ": ";
    }
}