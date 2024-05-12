package zadanie2;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visit {
    private Doctor doctor;
    private Patient patient;
    private LocalDate visitDate;

    public Visit(Doctor doctor, Patient patient, LocalDate visitDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;

        doctor.addVisit(this);
        patient.addVisit(this);
    }

    public Doctor getDoctor() {
        return doctor;
    }

    //      krotsza metoda jest w klasie doctor
//    public static Doctor findDoctorWithTheMostVisit(List<Visit> visits) {
//        Map<Doctor, Integer> doctorVisits = new HashMap<>();
//
//        for (Visit visit : visits) {
//            Doctor doctor = visit.getDoctor();
//            doctorVisits.put(doctor, doctorVisits.getOrDefault(doctor, 0) + 1);
//        }
//
//        Map.Entry<Doctor, Integer> maxEntry = null;
//        for (Map.Entry<Doctor, Integer> entry : doctorVisits.entrySet()) {
//            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
//                maxEntry = entry;
//            }
//        }
//
//        return maxEntry != null ? maxEntry.getKey() : null;
//    }


    public static int getYearWithMostVisits(List<Visit> visits) {
        Map<Integer, Integer> yearCount = new HashMap<>();

        for (Visit visit : visits) {
            int year = visit.getVisitDate().getYear();
            yearCount.put(year, yearCount.getOrDefault(year, 0) + 1);
        }

        int maxYear = 0;
        int maxVisits = 0;

        for (Map.Entry<Integer, Integer> entry : yearCount.entrySet()) {
            if (entry.getValue() > maxVisits) {
                maxVisits = entry.getValue();
                maxYear = entry.getKey();
            }
        }

        return maxYear;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public int getDoctor_id() {
        return doctor.getId();
    }

    @Override
    public String toString() {
        return "Visit{" +
                "doctorName=" + doctor.getName() +
                ", patientName=" + patient.getName() +
                ", visitDate=" + visitDate +
                '}';
    }
}
