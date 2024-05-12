package zadanie2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Doctor> doctors = readLekarzeFromFile("src/zadanie2/lekarze.txt");
        List<Patient> patients = readPacjenciFromFile("src/zadanie2/pacjenci.txt");
        List<Visit> visitList = readVizFromFile("src/zadanie2/wizyty.txt", doctors, patients);

        // Znajdź lekarza, który miał najwięcej wizyt
        Doctor doctorWithTheMostVisits = Doctor.findDoctorWithTheMostVisits(doctors);
        System.out.println("Doctor with the most visits: " + doctorWithTheMostVisits.getName() + " " + doctorWithTheMostVisits.getSurname());
        // Znajdź pacjenta, który miał najwięcej wizyt
        Patient patientWithMostVisits = Patient.findPatientWithTheMostVisits(patients);
        System.out.println("Patient with the most visits: " + patientWithMostVisits.getName() + " " + patientWithMostVisits.getSurname());
        // Która specjalizacja cieszy się największym powodzeniem?
        String theMostPopularSpeciality = Doctor.findTheMostPopularSpeciality(doctors, visitList);
        System.out.println("Most popular speciality: " + theMostPopularSpeciality);

        // Którego roku było najwięcej wizyt?
        int yearWithMostVisits = Visit.getYearWithMostVisits(visitList);
        System.out.println("The year with the most visits was: " + yearWithMostVisits);
        // Wypisz top 5 najstarszych lekarzy
        List<Doctor> theOldestDoctors = Doctor.top5OldestDoc(doctors);
        System.out.println("Top 5 najstarszych lekarzy:");
        for (Doctor doctor : theOldestDoctors) {
            System.out.println(doctor.getName() + " " + doctor.getSurname() + ", data urodzenia: " + doctor.getBirthDate());
        }

        // Wypisz top 5 lekarzy, którzy mieli najwięcej wizyt
        List<Doctor> top5Doc = Doctor.top5DocWithTheMostVisitst(doctors, visitList);
        System.out.println("Top 5 doctors with the most visits:");
        for (Doctor doctor : top5Doc) {
            System.out.println(doctor.getName() + " " + doctor.getSurname());
        }
        // Zwróć pacjentów, którzy byli u minimum 5 różnych lekarzy
        System.out.println("Patient with at least 5 doctors");
        List<Patient> patientsWithMultipleDocs = Patient.findPatientsVisitedMultipleDoctors(patients, 5);
        for (Patient patient : patientsWithMultipleDocs) {
            System.out.println(patient.toString());
        }

        // Zwróć lekarzy, którzy przyjęli tylko jednego pacjenta


        System.out.println("Doctor with only one Patient: " + Doctor.findDocWith1Patient(visitList));
        List<Integer> docWith1Patient = Doctor.findDocWith1Patient(visitList);
        System.out.println("Doctor with only one Patient:");
        for (Integer idLekarza : docWith1Patient) {
            System.out.println("Doctors Id: " + idLekarza);
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
                    String surname = tokens[1];
                    String name = tokens[2];
                    String speciality = tokens[3];
                    Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[4]);
                    String nip = tokens[5];
                    String pesel = tokens[6];

                    Doctor doctor = new Doctor(id, surname, name, speciality, birthDate, nip, pesel);
                    lekarze.add(doctor);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return lekarze;
    }

    private static List<Patient> readPacjenciFromFile(String fileName) {
        List<Patient> patients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                if (tokens.length == 5) {
                    int id = Integer.parseInt(tokens[0].trim());
                    String surname = tokens[1];
                    String name = tokens[2];
                    String pesel = tokens[3];
                    Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[4]);

                    Patient patient = new Patient(id, surname, name, pesel, birthDate);
                    patients.add(patient);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return patients;
    }


    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Date parsing error for: " + dateStr);
            return null;
        }
    }

    private static List<Visit> readVizFromFile(String fileName, List<Doctor> doctors, List<Patient> patients) {
        List<Visit> visits = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                if (tokens.length == 3) {
                    int doctorId = Integer.parseInt(tokens[0]);
                    int patientId = Integer.parseInt(tokens[1]);
                    LocalDate visitDate = parseDate(tokens[2]);

                    Doctor doctor = Doctor.findDoctorById(doctors, doctorId);
                    Patient patient = Patient.findPatientByID(patients, patientId);

                    if (doctor == null) {
                        System.out.println("Skipping line: Doctor with ID " + doctorId + " not found.");
                    } else if (patient == null) {
                        System.out.println("Skipping line: Patient with ID " + patientId + " not found.");
                    } else if (visitDate == null) {
                        System.out.println("Skipping line: Invalid date " + tokens[2]);
                    } else {
                        Visit visit = new Visit(doctor, patient, visitDate);
                        visits.add(visit);
                        doctor.addVisit(visit);
                        patient.addVisit(visit);
                    }
                } else {
                    System.out.println("Incorrect token length on line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Skipping line due to number format error.");
        }

        return visits;
    }



}








