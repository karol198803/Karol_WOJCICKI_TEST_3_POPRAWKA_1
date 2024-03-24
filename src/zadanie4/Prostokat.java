package zadanie4;

public class Prostokat extends Figura {
    private double bokA;
    private double bokB;

    public Prostokat(double bokA, double bokB) {
        super();
        this.bokA = bokA;
        this.bokB = bokB;
    }

    @Override
    public double obwod() {
        return 2 * (bokA + bokB);
    }

    @Override
    public double pole() {
        return bokA * bokB;
    }

    @Override
    public String toString() {
        return super.toString() + "Prostokąt o bokach " + bokA + "x" + bokB + ".";
    }
}
