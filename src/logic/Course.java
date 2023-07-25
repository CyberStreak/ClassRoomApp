package logic;

import java.util.ArrayList;

public class Course {
    private final String id;
    private final String name;
    private final ArrayList<Student> students;


    public Course(String id, String name, ArrayList<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

}
