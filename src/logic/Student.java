package logic;

public class Student {
    private final String name;
    private final String major;

    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String toString() {
        return name;
    }

    public double getFinalGrade(double preGradeFactor) {
        return preGradeFactor;
    }
}
