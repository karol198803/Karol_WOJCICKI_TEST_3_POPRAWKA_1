package zadanie4;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Figure> figureList = Arrays.asList(Figure.createSquare(10), Figure.createCircle(20), Figure.createRectangle(10, 20));

        for (Figure f : figureList) {
            System.out.println(f);
        }

        // b) Znajdź figurę z największym obwodem
        Figure figureWithTheHighestCircuit = findFigureWithTheHighestCircuit(figureList);
        System.out.println("Figura z największym obwodem: " + figureWithTheHighestCircuit);

        // b) Znajdź figurę z największym polem
        Figure figureWithTheHighestSquare = findFigureWithTheHighestSquare(figureList);
        System.out.println("Figura z największym polem: " + figureWithTheHighestSquare);

        // c) System.out.println(figuryPunktA.contains(new Kwadrat(10)); //powinno wypisc true
        System.out.println(3);
        System.out.println(figureList.contains(Figure.createSquare(10))); // powinno wypisać true



        Rectangle r1 = new Rectangle(10.0, 20.0,false);
        // TODO: Figura stworzona przez konstruktor np. Rectangle lub Square etc. powina miec numer 0
        // TODO: Figury stworzone tylko przez metode fabryczna powinny miec nadawany sekwencyjnie numer
        System.out.println(r1);
//        List<Figure> figureList1 = Arrays.asList(
//                Figure.createSquare(10),  // Should output Figura nr 1: Kwadrat o boku 10.
//                Figure.createCircle(10),  // Should output Figura nr 2: Koło o promieniu 10.
//                Figure.createRectangle(10, 20)  // Should output Figura nr 3: Prostokat o bokach 10x20.
//        );
//
//        figureList1.forEach(System.out::println);
    }

    private static Figure findFigureWithTheHighestCircuit(List<Figure> figureList) {
        return Collections.max(figureList, Comparator.comparing(Figure::circuit));
    }

    private static Figure findFigureWithTheHighestSquare(List<Figure> figureList) {
        return Collections.max(figureList, Comparator.comparing(Figure::area));
    }
}

