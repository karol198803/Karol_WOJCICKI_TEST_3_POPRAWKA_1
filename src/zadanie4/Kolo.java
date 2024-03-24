package zadanie4;

public class Kolo extends Figura {
    private double promien;

    public Kolo(double promien) {
        super();
        this.promien = promien;
    }

    @Override
    public double obwod() {
        return 2 * Math.PI * promien;
    }

    @Override
    public double pole() {
        return Math.PI * promien * promien;
    }

    @Override
    public String toString() {
        return super.toString() + "Koło o promieniu " + promien + ".";
    }
}