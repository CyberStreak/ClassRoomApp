package logic;

public class RepeatingStudent extends Student {
    private final double examGrade;

    public String toString() {
        return getName() + "*";
    }

    public RepeatingStudent(String name, String major, double examGrade) {
        super(name, major);
        this.examGrade = examGrade;
    }

    @Override
    public double getFinalGrade(double preGradeFactor) {
        return Math.round(10 * examGrade) / 10.0;
    }
}
