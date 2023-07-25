package gui;

import io.CourseDataReader;
import io.CsvDataReader;
import io.MajorMapReader;
import io.TagValueReader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import logic.Course;
import logic.Student;
import java.io.File;
import java.util.Map;
import java.util.Optional;

public class ControlPane extends StackPane {
    MajorMapReader mapReader = new MajorMapReader(new File("C:\\Users\\milic\\IdeaProjects\\ClassRoomApp09\\src\\daten\\major-map.txt"));
    private final Map<String, String> majorMap = mapReader.readMap();
    private Course course;

    public ControlPane(StateModel stateModel) {
        Button loadButton = new Button("-> Load Data <-");

        Label numberLabel = new Label("Number of students: ");
        Label gradeFactorValue = new Label("Pre Grade Factor[%]: ");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        Slider preGradeFactorSlider = new Slider(0, 100, 100);
        preGradeFactorSlider.setShowTickMarks(true);
        preGradeFactorSlider.setShowTickLabels(true);
        preGradeFactorSlider.setMajorTickUnit(20.0);
        preGradeFactorSlider.setSnapToTicks(true);

        loadButton.setOnAction (event -> {
            FileChooser fileChooser = new FileChooser();
            File dataFile = fileChooser.showOpenDialog(null);
            Optional<CourseDataReader> dataReader;
            if (dataFile != null) {
                dataReader = getReader(dataFile);
                if (dataReader.isPresent()) {
                    Optional<Course> courseData = dataReader.get().readData();
                    if (courseData.isPresent()) {
                        course = courseData.get();
                        stateModel.setCourse(course);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to read coursedata from file: " + dataFile);
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unknown file format from file: " + dataFile);
                    alert.showAndWait();
                }
            }
        });

        preGradeFactorSlider.valueProperty().addListener(observable -> {
            stateModel.setPreGradeFactor(preGradeFactorSlider.getValue()/100);
            if (stateModel.isSortByGrade()) {
                stateModel.setSortByGrade(true);
            }
        });

        stateModel.addObserver(() -> refreshText(course, numberLabel, gradeFactorValue, textArea, preGradeFactorSlider.getValue()));

        VBox controlPane = new VBox();
        controlPane.getChildren().addAll(loadButton, numberLabel, textArea, gradeFactorValue, preGradeFactorSlider);
        this.getChildren().add(controlPane);
    }

    private void refreshText (Course course, Label numberLabel, Label numberLabelValue, TextArea textArea, double preGradeFactorSlider) {
        textArea.clear();
        double preGradeFactor = preGradeFactorSlider/100;
        for (Student student : course.getStudents()) {
            String major;
            if (majorMap.containsKey(student.getMajor())){
                major = majorMap.get(student.getMajor());
            } else {
                major = student.getMajor();
            }
            String studentText = "The final grade for " + student + " (" + major + ") is: " + student.getFinalGrade(preGradeFactor) +"\n";
            textArea.appendText(studentText);
        }

        String studentLabel = "Number of students: " + course.getStudents().size();
        numberLabel.setText(studentLabel);

        String factorLabel = "Pre Grade Factor[%]: " + preGradeFactorSlider;
        numberLabelValue.setText(factorLabel);
    }

    private static Optional<CourseDataReader> getReader(File dataFile) {
        if (dataFile.toURI().getPath().endsWith(".csv")) {
            return Optional.of(new CsvDataReader(dataFile));
        } else if (dataFile.toURI().getPath().endsWith(".txt")) {
            return Optional.of(new TagValueReader(dataFile));
        }
        return Optional.empty();
    }
}
