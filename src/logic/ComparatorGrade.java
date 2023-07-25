package logic;

import java.util.Comparator;

public class ComparatorGrade implements Comparator<Student> {
    double preGradeFactor;

    public ComparatorGrade(double factor) {
        setPreGradeFactor(factor);
    }

    public double getPreGradeFactor() {
        return preGradeFactor;
    }

    public void setPreGradeFactor(double preGradeFactor) {
        this.preGradeFactor = preGradeFactor;
    }

    @Override
    public int compare(Student o1, Student o2) {

        if (o1.getFinalGrade(getPreGradeFactor()) < o2.getFinalGrade(getPreGradeFactor())) {
            return -1;
        } else if (o1.getFinalGrade(getPreGradeFactor()) > o2.getFinalGrade(getPreGradeFactor())) {
            return 1;
        }else {
            return 0;
        }
    }
}
