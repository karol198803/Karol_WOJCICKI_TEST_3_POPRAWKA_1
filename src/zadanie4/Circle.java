package zadanie4;

public class Circle extends Figure {
    private double radius;

    public Circle(double radius, boolean isFactoryCreated) {
        super(isFactoryCreated);
        this.radius = radius;
    }

    @Override
    public double circuit() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return super.toString() + "Koło o promieniu " + radius + ".";
    }
}