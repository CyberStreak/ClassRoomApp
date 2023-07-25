package logic;

import java.util.List;

public class RegularStudent extends Student {
    private final List<Double> grades;
    private final double examGrade;

    public RegularStudent(String name, String major, List<Double> grades, double examGrade) {
        super(name, major);
        this.grades = grades;
        this.examGrade = examGrade;
    }

    @Override
    public double getFinalGrade(double preGradeFactor) {
        double preGrade = computeAverageGrade();
        return (double) (Math.round(10 * (preGrade * preGradeFactor + examGrade * (1-preGradeFactor))) / 10);
    }

    private double computeAverageGrade() {
        double min = grades.get(0);
        double sum = 0;
        for (double grade : grades) {
            if (grade < min) min = grade;
            sum += grade;
        }
        return (sum-min) / (grades.size() - 1);
    }
}
