package zadanie4;

public abstract class Figure {
    private static int figureCount = 1;
    protected int figureNumber;

    protected Figure(boolean isFactoryCreated) {
//        if (isFactoryCreated) {
            this.figureNumber = figureCount++;
//        } else {
//            this.figureNumber = 1;
//        }
    }

    public static Square createSquare(double side) {
        return new Square(side, true);
    }

    public static Circle createCircle(double radius) {
        return new Circle(radius, true);
    }

    public static Rectangle createRectangle(double sideA, double sideB) {
        return new Rectangle(sideA, sideB, true);
    }

    public abstract double circuit();
    public abstract double area();

    @Override
    public String toString() {
        return "Figura nr " + figureNumber + ": ";
    }
}