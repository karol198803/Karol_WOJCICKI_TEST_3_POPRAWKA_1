package zadanie4;

public class Rectangle extends Figure {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB, boolean isFactoryCreated) {
        super(isFactoryCreated);
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double circuit() {
        return 2 * (sideA + sideB);
    }

    @Override
    public double area() {
        return sideA * sideB;
    }

    @Override
    public String toString() {
        return super.toString() + "Prostokąt o bokach " + sideA + "x" + sideB + ".";
    }
}
