package io;

import logic.Course;
import logic.RegularStudent;
import logic.RepeatingStudent;
import logic.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CsvDataReader implements CourseDataReader {
    private final File courseDataFile;

    public CsvDataReader(File courseDataFile) {
        this.courseDataFile = courseDataFile;
    }

    @Override
    public Optional<Course> readData() {
        ArrayList<Student> students = new ArrayList<>();

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(courseDataFile);
        } catch (FileNotFoundException e){
            System.err.println("File not found: " + courseDataFile.getAbsolutePath());
            return Optional.empty();
        }

        String courseID = fileScanner.nextLine();
        String courseName = fileScanner.nextLine();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            String[] tokens = line.split(",");

            String name = tokens[0].trim();
            String major = tokens[1].trim();
            String isRepeating = tokens[2].trim();
            double examGrade = Double.parseDouble(tokens[3].trim());

            Student student;
            if (isRepeating.equals("r")){
                student = new RepeatingStudent(name, major, examGrade);
            } else {
                List<Double> studentGrades = new ArrayList<>();
                for (int i = 4; i < tokens.length; i++) {
                    try{
                        double grade = Double.parseDouble(tokens[i]);
                        studentGrades.add(grade);
                    } catch (NumberFormatException e) {
                        System.err.println("Unable to parse number: " + tokens[i]);
                        return Optional.empty();
                    }
                }
                student = new RegularStudent(name, major, studentGrades, examGrade);
            }
            students.add(student);
        }
        Course course = new Course(courseID, courseName, students);
        return Optional.of(course);
    }
}
