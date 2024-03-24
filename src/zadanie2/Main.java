package zadanie2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Doctor> doctors = readLekarzeFromFile("src/zadanie2/lekarze.txt");
        List<Patient> patients = readPacjenciFromFile("src/zadanie2/pacjenci.txt");
        List<Visits> visitsList = readWizytyFromFile("src/zadanie2/wizyty.txt");

//        // Znajdź lekarza, który miał najwięcej wizyt
        Doctor doctorZNajwiecejWizyt = znajdzLekarzaZNajwiecejWizyt(doctors, visitsList);
        System.out.println("Lekarz z największą liczbą wizyt: " + doctorZNajwiecejWizyt.getImie() + " " + doctorZNajwiecejWizyt.getNazwisko());

//        // Znajdź pacjenta, który miał najwięcej wizyt
        int patientId = findPatientWithMostVisits(visitsList);
        System.out.println("Patient with the most visits: " + patientId);
        // Która specjalizacja cieszy się największym powodzeniem?
        String najpopularniejszaSpecjalnosc = znajdzNajpopularniejszaSpecjalnosc(doctors, visitsList);
        System.out.println("Najpopularniejsza specjalizacja: " + najpopularniejszaSpecjalnosc);

        // Którego roku było najwięcej wizyt?
        int yearWithTheMostVisits = findYearWithTheMostVisits(visitsList);
        System.out.println("Rok z największą liczbą wizyt: " + yearWithTheMostVisits);

        // Wypisz top 5 najstarszych lekarzy
        List<Doctor> theOldestDoctors = top5OldestDoc(doctors);
        System.out.println("Top 5 najstarszych lekarzy:");
        for (Doctor doctor : theOldestDoctors) {
            System.out.println(doctor.getImie() + " " + doctor.getNazwisko() + ", data urodzenia: " + doctor.getDataUrodzenia());
        }

        // Wypisz top 5 lekarzy, którzy mieli najwięcej wizyt
        List<Doctor> top5Doc = top5DocWithTheMostVisitst(doctors, visitsList);
        System.out.println("Top 5 lekarzy z największą liczbą wizyt:");
        for (Doctor doctor : top5Doc) {
            System.out.println(doctor.getImie() + " " + doctor.getNazwisko());
        }
        // Zwróć pacjentów, którzy byli u minimum 5 różnych lekarzy
        List<Patient> patientsWith5Doc = findPatientWith5Doc(patients, visitsList);
        System.out.println("Pacjenci, którzy byli u co najmniej 5 różnych lekarzy:");
        for (Patient patient : patientsWith5Doc) {
            System.out.println(patient);
        }

        // Zwróć lekarzy, którzy przyjęli tylko jednego pacjenta
        List<Integer> docWith1Patient = findDocWith1Patient(visitsList);
        System.out.println("Lekarze z tylko jednym pacjentem:");
        for (Integer idLekarza : docWith1Patient) {
            System.out.println("ID lekarza: " + idLekarza);
        }

    }

    private static List<Doctor> readLekarzeFromFile(String fileName) {
        List<Doctor> lekarze = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                if (tokens.length == 7) {
                    int id = Integer.parseInt(tokens[0]);
                    String nazwisko = tokens[1];
                    String imie = tokens[2];
                    String specjalnosc = tokens[3];
                    Date dataUrodzenia = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[4]);
                    String nip = tokens[5];
                    String pesel = tokens[6];

                    Doctor doctor = new Doctor(id, nazwisko, imie, specjalnosc, dataUrodzenia, nip, pesel);
                    lekarze.add(doctor);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return lekarze;
    }

    private static List<Patient> readPacjenciFromFile(String fileName) {
        List<Patient> pacjenci = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                if (tokens.length == 4) {
                    int id = Integer.parseInt(tokens[0]);
                    String nazwisko = tokens[1];
                    String imie = tokens[2];
                    String pesel = tokens[3];
                    Date dataUrodzenia = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[4]);

                    Patient patient = new Patient(id, nazwisko, imie, pesel, dataUrodzenia);
                    pacjenci.add(patient);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return pacjenci;
    }

    private static List<Visits> readWizytyFromFile(String fileName) {
        List<Visits> wizyty = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                if (tokens.length == 3) {
                    int idLekarza = Integer.parseInt(tokens[0]);
                    int idPacjenta = Integer.parseInt(tokens[1]);
                    Date dataWizyty = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[2]);

                    Visits visits = new Visits(idLekarza, idPacjenta, dataWizyty);
                    wizyty.add(visits);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return wizyty;
    }

    private static String znajdzNajpopularniejszaSpecjalnosc(List<Doctor> lekarze, List<Visits> wizyty) {
        Map<String, Integer> liczbaWizytNaSpecjalnosc = new HashMap<>();

        for (Visits visits : wizyty) {
            int idLekarza = visits.getIdLekarza();
            Doctor doctor = lekarze.stream().filter(l -> l.getId() == idLekarza).findFirst().orElse(null);

            if (doctor != null) {
                String specjalnosc = doctor.getSpecjalnosc();
                liczbaWizytNaSpecjalnosc.put(specjalnosc, liczbaWizytNaSpecjalnosc.getOrDefault(specjalnosc, 0) + 1);
            }
        }

        int maxLiczbaWizyt = 0;
        String najpopularniejszaSpecjalnosc = null;

        for (Map.Entry<String, Integer> entry : liczbaWizytNaSpecjalnosc.entrySet()) {
            String specjalnosc = entry.getKey();
            int liczbaWizyt = entry.getValue();

            if (liczbaWizyt > maxLiczbaWizyt) {
                maxLiczbaWizyt = liczbaWizyt;
                najpopularniejszaSpecjalnosc = specjalnosc;
            }
        }

        return najpopularniejszaSpecjalnosc;
    }

    private static int findYearWithTheMostVisits(List<Visits> wizyty) {
        Map<Integer, Integer> liczbaWizytNaRok = new HashMap<>();

        Calendar calendar = Calendar.getInstance();
        for (Visits visits : wizyty) {
            calendar.setTime(visits.getDataWizyty());
            int rok = calendar.get(Calendar.YEAR);
            liczbaWizytNaRok.put(rok, liczbaWizytNaRok.getOrDefault(rok, 0) + 1);
        }

        int maxLiczbaWizyt = 0;
        int rokZNajwiecejWizyt = -1;

        for (Map.Entry<Integer, Integer> entry : liczbaWizytNaRok.entrySet()) {
            int rok = entry.getKey();
            int liczbaWizyt = entry.getValue();

            if (liczbaWizyt > maxLiczbaWizyt) {
                maxLiczbaWizyt = liczbaWizyt;
                rokZNajwiecejWizyt = rok;
            }
        }

        return rokZNajwiecejWizyt;
    }

    private static Doctor znajdzLekarzaZNajwiecejWizyt(List<Doctor> lekarze, List<Visits> wizyty) {
        Map<Integer, Integer> liczbaWizytNaLekarza = new HashMap<>();

        for (Visits visits : wizyty) {
            int idLekarza = visits.getIdLekarza();
            if (!liczbaWizytNaLekarza.containsKey(idLekarza)) {
                liczbaWizytNaLekarza.put(idLekarza, 0);
            }
            liczbaWizytNaLekarza.put(idLekarza, liczbaWizytNaLekarza.get(idLekarza) + 1);
        }

        int maxLiczbaWizyt = 0;
        int idLekarzaZNajwiecejWizyt = -1;

        for (Map.Entry<Integer, Integer> entry : liczbaWizytNaLekarza.entrySet()) {
            if (entry.getValue() > maxLiczbaWizyt) {
                maxLiczbaWizyt = entry.getValue();
                idLekarzaZNajwiecejWizyt = entry.getKey();
            }
        }

        for (Doctor doctor : lekarze) {
            if (doctor.getId() == idLekarzaZNajwiecejWizyt) {
                return doctor;
            }
        }

        return null;
    }


    public static int findPatientWithMostVisits(List<Visits> visits) {
        Map<Integer, Integer> visitCountMap = new HashMap<>();


        for (Visits visit : visits) {
            int currentCount = visitCountMap.getOrDefault(visit.getIdPacjenta(), 0);
            visitCountMap.put(visit.getIdPacjenta(), currentCount + 1);
        }


        int maxVisits = 0;
        int patientIdWithMostVisits = -1;
        for (Map.Entry<Integer, Integer> entry : visitCountMap.entrySet()) {
            if (entry.getValue() > maxVisits) {
                maxVisits = entry.getValue();
                patientIdWithMostVisits = entry.getKey();
            }
        }

        return patientIdWithMostVisits;
    }


    private static List<Doctor> top5DocWithTheMostVisitst(List<Doctor> lekarze, List<Visits> wizyty) {
        Map<Integer, Integer> lekarzLiczbaWizyt = new HashMap<>();
        for (Visits visits : wizyty) {
            lekarzLiczbaWizyt.put(visits.getIdLekarza(), lekarzLiczbaWizyt.getOrDefault(visits.getIdLekarza(), 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> lista = new ArrayList<>(lekarzLiczbaWizyt.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<Doctor> top5 = new ArrayList<>();
        for (int i = 0; i < Math.min(5, lista.size()); i++) {
            Map.Entry<Integer, Integer> wpis = lista.get(i);
            for (Doctor doctor : lekarze) {
                if (doctor.getId() == wpis.getKey()) {
                    top5.add(doctor);
                    break;
                }
            }
        }
        return top5;
    }


    private static List<Doctor> top5OldestDoc(List<Doctor> lekarze) {

        List<Doctor> kopiaLekarzy = new ArrayList<>(lekarze);

        Collections.sort(kopiaLekarzy, (Doctor l1, Doctor l2) -> l1.getDataUrodzenia().compareTo(l2.getDataUrodzenia()));


        return kopiaLekarzy.subList(0, Math.min(5, kopiaLekarzy.size()));
    }

    public static List<Integer> findDocWith1Patient(List<Visits> wizyty) {
        Map<Integer, Integer> mapLekarze = new HashMap<>();
        for (Visits visits : wizyty) {
            int idLekarza = visits.getIdLekarza();
            mapLekarze.put(idLekarza, mapLekarze.getOrDefault(idLekarza, 0) + 1);
        }

       // System.out.println("Mapa lekarzy po zliczeniu wizyt: " + mapLekarze);

        List<Integer> lekarzeZJednymPacjentem = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mapLekarze.entrySet()) {
            if (entry.getValue() == 1) {
                lekarzeZJednymPacjentem.add(entry.getKey());
            }
        }

      //  System.out.println("Lista lekarzy z jednym pacjentem: " + lekarzeZJednymPacjentem);

        return lekarzeZJednymPacjentem;
    }
    private static List<Patient> findPatientWith5Doc(List<Patient> pacjenci, List<Visits> wizyty) {
        Map<Integer, Set<Integer>> pacjentDoLekarzy = new HashMap<>();
        for (Visits visits : wizyty) {
            pacjentDoLekarzy.computeIfAbsent(visits.getIdPacjenta(), k -> new HashSet<>()).add(visits.getIdLekarza());
        }


       // pacjentDoLekarzy.forEach((idPacjenta, lekarze) -> System.out.println("Pacjent ID: " + idPacjenta + ", liczba odwiedzonych lekarzy: " + lekarze.size()));

        List<Patient> wynik = new ArrayList<>();
        for (Patient patient : pacjenci) {
            Set<Integer> lekarze = pacjentDoLekarzy.get(patient.getId());
            if (lekarze != null && lekarze.size() >= 5) {
                wynik.add(patient);
            }
        }

        return wynik;
    }

}








