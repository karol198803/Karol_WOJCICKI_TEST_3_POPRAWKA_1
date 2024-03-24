package zadanie4;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Figura> figury = Arrays.asList(Figura.stworzKwadrat(10), Figura.stworzKolo(20), Figura.stworzProstokat(10, 20));

        for (Figura f : figury) {
            System.out.println(f);
        }

        // b) Znajdź figurę z największym obwodem
        Figura figuraZNajwiekszymObwodem = znajdzFiguraZNajwiekszymObwodem(figury);
        System.out.println("Figura z największym obwodem: " + figuraZNajwiekszymObwodem);

        // b) Znajdź figurę z największym polem
        Figura figuraZNajwiekszymPolem = znajdzFiguraZNajwiekszymPolem(figury);
        System.out.println("Figura z największym polem: " + figuraZNajwiekszymPolem);

        // c) System.out.println(figuryPunktA.contains(new Kwadrat(10)); //powinno wypisc true
        System.out.println(figury.contains(Figura.stworzKwadrat(10))); // powinno wypisać true
    }

    private static Figura znajdzFiguraZNajwiekszymObwodem(List<Figura> figury) {
        return Collections.max(figury, Comparator.comparing(Figura::obwod));
    }

    private static Figura znajdzFiguraZNajwiekszymPolem(List<Figura> figury) {
        return Collections.max(figury, Comparator.comparing(Figura::pole));
    }
}

