package zadanie1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Mother> mothers = readMothersFile("src/zadanie1/mamy.txt");
        List<Child> children = readChildrenFile("src/zadanie1/noworodki.txt", mothers);

        // a) Imię i wzrost najwyższego chłopca oraz imię i wzrost najwyższej dziewczynki.

        Child tallestBoy = findTallestChildByGender(children, "s");
        Child tallestGirl = findTallestChildByGender(children, "c");

        System.out.println("Najwyższy chłopiec: " + tallestBoy.getName() + ", wzrost: " + tallestBoy.getHeight() + " cm");
        System.out.println("Najwyższa dziewczynka: " + tallestGirl.getName() + ", wzrost: " + tallestGirl.getHeight() + " cm");

        // b) W którym dniu tygodnia urodziło się najwięcej dzieci?
        String mostPopularDay = findMostPopularDayOfBirth(children);
        System.out.println("Najwięcej dzieci urodziło się w dniu: " + mostPopularDay);

        // c) Imiona kobiet poniżej 25 lat, które urodziły dzieci o wadze powyżej 4000 g.
        List<Mother> youngMothersWithHeavyChildren = findMothersBelow25WithHeavyChildren(mothers);
        for (Mother mother : youngMothersWithHeavyChildren) {
            System.out.println("Młoda matka z ciężkim dzieckiem: " + mother.getName());
        }

        // d) Imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce.
        List<Child> daughtersWithMothersName = findDaughtersWithSameNameAsMother(children);
        for (Child daughter : daughtersWithMothersName) {
            System.out.println("Dziewczynka o imieniu " + daughter.getName() + " odziedziczyła imię po matce, data urodzenia: " + daughter.getBirthDate());
        }

        // e) Znajdź matki, które urodziły bliźnięta.
        List<Mother> mothersWithTwins = findMothersWithTwins(mothers);
        for (Mother mother : mothersWithTwins) {
            System.out.println("Matka z bliźniętami: " + mother.getName());
        }
    }

    private static List<Mother> readMothersFile(String fileName) {
        List<Mother> mothers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                mothers.add(new Mother(id, name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mothers;
    }

    private static List<Child> readChildrenFile(String fileName, List<Mother> mothers) {
        List<Child> children = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                String gender = parts[1];
                String name = parts[2];
                Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[3]);
                int weight = Integer.parseInt(parts[4]);
                int height = Integer.parseInt(parts[5]);
                int motherId = Integer.parseInt(parts[6]);
                Mother mother = findMotherById(mothers, motherId);
                children.add(new Child(id, gender, name, birthDate, weight, height, mother));
                mother.addChild(children.get(children.size() - 1));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return children;
    }

    private static Mother findMotherById(List<Mother> mothers, int id) {
        for (Mother mother : mothers) {
            if (mother.getId() == id) {
                return mother;
            }
        }
        return null;
    }

    private static Child findTallestChildByGender(List<Child> children, String gender) {
        Child tallestChild = null;
        int maxHeight = 0;

        for (Child child : children) {
            if (child.getGender().equals(gender) && child.getHeight() > maxHeight) {
                maxHeight = child.getHeight();
                tallestChild = child;
            }
        }

        return tallestChild; // Może być null, jeśli nie znaleziono
    }


    private static String findMostPopularDayOfBirth(List<Child> children) {
        Map<String, Integer> dayCountMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.ENGLISH);

        for (Child child : children) {
            String dayOfWeek = sdf.format(child.getBirthDate());
            dayCountMap.put(dayOfWeek, dayCountMap.getOrDefault(dayOfWeek, 0) + 1);
        }

        String mostPopularDay = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : dayCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopularDay = entry.getKey();
            }
        }

        return mostPopularDay;
    }


    private static List<Mother> findMothersBelow25WithHeavyChildren(List<Mother> mothers) {
        List<Mother> qualifyingMothers = new ArrayList<>();

        for (Mother mother : mothers) {
            if (mother.getAge() < 25) {
                for (Child child : mother.getChildren()) {
                    if (child.getWeight() > 4000) {
                        qualifyingMothers.add(mother);
                        break;
                    }
                }
            }
        }

        return qualifyingMothers;
    }

    private static List<Child> findDaughtersWithSameNameAsMother(List<Child> children) {
        List<Child> daughters = new ArrayList<>();

        for (Child child : children) {
            if ("c".equals(child.getGender()) && child.getMother() != null && child.getName().equals(child.getMother().getName())) {
                daughters.add(child);
            }
        }

        return daughters;
    }


    private static List<Mother> findMothersWithTwins(List<Mother> mothers) {
        List<Mother> mothersWithTwins = new ArrayList<>();

        for (Mother mother : mothers) {
            List<Child> children = mother.getChildren();
            for (int i = 0; i < children.size() - 1; i++) {
                for (int j = i + 1; j < children.size(); j++) {
                    if (isSameDay(children.get(i).getBirthDate(), children.get(j).getBirthDate())) {
                        mothersWithTwins.add(mother);
                        i = children.size();
                        break;
                    }
                }
            }
        }

        return mothersWithTwins;
    }


    private static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }
}




