package gui;

import logic.ComparatorGrade;
import logic.Course;

import java.util.ArrayList;
import java.util.List;

public class StateModel {
    private Course course;
    private double preGradeFactor;
    private boolean sortByGrade;
    private final List<StateObserver> observers;

    public StateModel() {
        observers = new ArrayList<>();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        sendChange();
    }

    public double preGradeFactor() {
        return preGradeFactor;
    }

    public void setPreGradeFactor(double preGradeFactor) {
        this.preGradeFactor = preGradeFactor;
        sendChange();
    }

    public boolean isSortByGrade() {
        return sortByGrade;
    }

    public void setSortByGrade(boolean sortByGrade) {
        this.sortByGrade = sortByGrade;
        if (sortByGrade) {
            ComparatorGrade comparatorGrade = new ComparatorGrade(preGradeFactor);
            course.getStudents().sort(comparatorGrade);
            sendChange();
        } else {
            course.getStudents();
            sendChange();
        }
    }

    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }

    private void sendChange() {
        for (StateObserver observer : observers) {
            observer.stateChanged();
        }
    }
}
