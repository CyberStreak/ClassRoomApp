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

public class TagValueReader implements CourseDataReader {
    private final File courseDataFile;

    public TagValueReader(File courseDataFile) {
        this.courseDataFile = courseDataFile;
    }

    @Override
    public Optional<Course> readData() {

        try (Scanner fileScanner = new Scanner(courseDataFile)) {
            // code to read data from the file
            String courseID = fileScanner.nextLine().split(":")[1].trim();
        String courseName = fileScanner.nextLine().split(":")[1].trim();

        ArrayList<Student> students = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String name = fileScanner.nextLine().split(":")[1].trim();
            String major = fileScanner.nextLine().split(":")[1].trim();
            String isRepeating = fileScanner.nextLine().split(":")[1].trim();
            double examGrade = Double.parseDouble(fileScanner.nextLine().split(":")[1].trim());

            Student student;
            if (isRepeating.equals("true")){
                student = new RepeatingStudent(name, major, examGrade);
            } else {
                String preGradesText = fileScanner.nextLine().split(":")[1].trim();
                String[] tokens = preGradesText.split(",");
                List<Double> studentGrades = new ArrayList<>();
                for (String token : tokens) {
                    try{
                        double grade = Double.parseDouble(token);
                        studentGrades.add(grade);
                    } catch (NumberFormatException e) {
                        System.err.println("Unable to parse number: " + token);
                        return Optional.empty();
                    }
                }
                student = new RegularStudent(name, major, studentGrades, examGrade);
            }
            students.add(student);
        }
        
        Course course = new Course(courseID, courseName, students);
        return Optional.of(course);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + courseDataFile.getAbsolutePath());
            return Optional.empty();
        }
    }
}
