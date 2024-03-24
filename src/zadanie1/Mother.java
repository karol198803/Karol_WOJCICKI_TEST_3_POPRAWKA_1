package zadanie1;

import java.util.ArrayList;
import java.util.List;

class Mother {
    private int id;
    private String name;
    private int age;
    private List<Child> children;

    public Mother(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        children.add(child);
    }
}