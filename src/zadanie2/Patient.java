package zadanie2;

import java.util.*;

public class Patient {
    private int id;
    private String surname;
    private String name;
    private String pesel;
    private Date dayOfBirth;
    private List<Visit> visits = new ArrayList<>();


    public Patient(int id, String surname, String name, String pesel, Date dayOfBirth) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.pesel = pesel;
        this.dayOfBirth = dayOfBirth;

    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setPatient(this);
    }

    public static Patient findPatientByID(List<Patient> patients, int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientWithTheMostVisits(List<Patient> patients) {
        Patient patientWithTheMostVisit = patients.get(0);

        for (Patient patient : patients) {
            if (patient.getVisits().size() > patientWithTheMostVisit.getVisits().size())
                patientWithTheMostVisit = patient;
        }

        return patientWithTheMostVisit;
    }

    public static List<Patient> findPatientsVisitedMultipleDoctors(List<Patient> patients, int minimumDoctors) {
        List<Patient> patientsWithMultipleDoctors = new ArrayList<>();
        for (Patient patient : patients) {
            Set<Integer> uniqueDoctorIds = new HashSet<>();
            for (Visit visit : patient.getVisits()) {
                uniqueDoctorIds.add(visit.getDoctor_id());
            }
            if (uniqueDoctorIds.size() >= minimumDoctors) {
                patientsWithMultipleDoctors.add(patient);
            }
        }
        return patientsWithMultipleDoctors;
    }


    public int getId() {
        return id;
    }

    public List<Visit> getVisits() {
        return visits;
    }


    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPesel() {
        return pesel;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                ", visits=" + visits +
                '}';
    }
}
