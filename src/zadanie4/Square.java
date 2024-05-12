package zadanie4;

public class Square extends Figure {
    private double side;

    public Square(double side, boolean isFactoryCreated) {
        super(isFactoryCreated);
        this.side = side;
    }

    @Override
    public double circuit() {
        return 4 * side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public String toString() {
        return super.toString() + "Kwadrat o boku " + side + ".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Square)) return false;
        Square square = (Square) obj;
        return Double.compare(square.side, this.side) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(side);
    }
}
