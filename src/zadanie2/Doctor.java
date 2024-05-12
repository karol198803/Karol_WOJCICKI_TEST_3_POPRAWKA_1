package zadanie2;

import java.util.*;

public class Doctor {
    private int id;
    private String surname;
    private String name;
    private String speciality;
    private Date birthDate;
    private String nip;
    private String pesel;
    private List<Visit> visits = new ArrayList<>();

    public Doctor(int id, String surname, String name, String speciality, Date birthDate, String nip, String pesel) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.speciality = speciality;
        this.birthDate = birthDate;
        this.nip = nip;
        this.pesel = pesel;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setDoctor(this);
    }

    public static Doctor findDoctorById(List<Doctor> doctors, int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    public static List<Doctor> top5OldestDoc(List<Doctor> lekarze) {

        List<Doctor> doctorsCopy = new ArrayList<>(lekarze);

        Collections.sort(doctorsCopy, (Doctor l1, Doctor l2) -> l1.getBirthDate().compareTo(l2.getBirthDate()));


        return doctorsCopy.subList(0, Math.min(5, doctorsCopy.size()));
    }

    public static Doctor findDoctorWithTheMostVisits(List<Doctor> doctors) {
        Doctor doctorWithTheMostVisit = doctors.get(0);
        for (Doctor doctor : doctors) {
            if (doctor.getVisits().size() > doctorWithTheMostVisit.getVisits().size())
                doctorWithTheMostVisit = doctor;
        }

        return doctorWithTheMostVisit;
    }

    public static List<Doctor> top5DocWithTheMostVisitst(List<Doctor> doctors, List<Visit> visits) {
        Map<Integer, Integer> doctorNumberOfVisits = new HashMap<>();
        for (Visit visit : visits) {
            doctorNumberOfVisits.put(visit.getDoctor_id(), doctorNumberOfVisits.getOrDefault(visit.getDoctor_id(), 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> lista = new ArrayList<>(doctorNumberOfVisits.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<Doctor> top5 = new ArrayList<>();
        for (int i = 0; i < Math.min(5, lista.size()); i++) {
            Map.Entry<Integer, Integer> wpis = lista.get(i);
            for (Doctor doctor : doctors) {
                if (doctor.getId() == wpis.getKey()) {
                    top5.add(doctor);
                    break;
                }
            }
        }
        return top5;
    }

    public static String findTheMostPopularSpeciality(List<Doctor> doctors, List<Visit> visits) {
        Map<String, Integer> numberOfVisitPerSpeciality = new HashMap<>();

        for (Visit visit : visits) {
            int idLekarza = visit.getDoctor().getId();
            Doctor doctor = doctors.stream().filter(l -> l.getId() == idLekarza).findFirst().orElse(null);

            if (doctor != null) {
                String speciality = doctor.getSpeciality();
                numberOfVisitPerSpeciality.put(speciality, numberOfVisitPerSpeciality.getOrDefault(speciality, 0) + 1);
            }
        }

        int maxVisitNumber = 0;
        String mostPopularSpeciality = null;

        for (Map.Entry<String, Integer> entry : numberOfVisitPerSpeciality.entrySet()) {
            String speciality = entry.getKey();
            int visitCount = entry.getValue();

            if (visitCount > maxVisitNumber) {
                maxVisitNumber = visitCount;
                mostPopularSpeciality = speciality;
            }
        }

        return mostPopularSpeciality;
    }
    public static List<Integer> findDocWith1Patient(List<Visit> visits) {
        Map<Integer, Integer> mapDoctor = new HashMap<>();
        for (Visit visit : visits) {
            int doctor_id = visit.getDoctor_id();
            mapDoctor.put(doctor_id, mapDoctor.getOrDefault(doctor_id, 0) + 1);
        }

//        System.out.println("Mapa lekarzy po zliczeniu wizyt: " + mapDoctor);
//
//        for (Map.Entry<Integer, Integer> entry : mapDoctor.entrySet()) {
//            System.out.println("Klucz: " + entry.getKey() + ", Wartość: " + entry.getValue());
//        }
        List<Integer> doctorWith1patient = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mapDoctor.entrySet()) {
            if (entry.getValue() == 1) {
                doctorWith1patient.add(entry.getKey());
            }
        }

//        System.out.println("Lista lekarzy z jednym pacjentem: " + doctorWith1patient);

        return doctorWith1patient;
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

    public String getSpeciality() {
        return speciality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getNip() {
        return nip;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", birthDate=" + birthDate +
                ", nip='" + nip + '\'' +
                ", pesel='" + pesel + '\'' +
                ", visits=" + visits +
                '}';
    }
}

