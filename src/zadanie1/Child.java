package zadanie1;

import java.time.LocalDate;
import java.util.Date;

class Child {
    private int id;
    private String gender;
    private String name;
    private Date birthDate;
    private int weight;
    private int height;
    private Mother mother;

    public Child(int id, String gender, String name, Date birthDate, int weight, int height, Mother mother) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.mother = mother;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Mother getMother() {
        return mother;
    }
}