package io;

import logic.Course;

import java.util.Optional;

public interface CourseDataReader {
    Optional<Course> readData();
}
