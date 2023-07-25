package gui;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import logic.Student;

public class GraphicsPane extends Pane {

    public GraphicsPane(StateModel stateModel) {
        CheckBox sortGrade = new CheckBox("Sort by Grade");
        sortGrade.setIndeterminate(false);

        sortGrade.setOnAction(event -> {
            if (sortGrade.isSelected()) {
                sortGrade.setIndeterminate(true);
                stateModel.setSortByGrade(true);
            } else {
                stateModel.setSortByGrade(false);
            }
        });

        stateModel.addObserver(() -> drawGraphics(stateModel, sortGrade));
    }

    private void drawGraphics(StateModel stateModel, CheckBox sortGrade) {
        getChildren().clear();
        getChildren().add(sortGrade);
        double barWidth = getWidth() / stateModel.getCourse().getStudents().size();

        for (int i = 0; i < stateModel.getCourse().getStudents().size(); i++) {
            Student student = stateModel.getCourse().getStudents().get(i);
            double barHeight = student.getFinalGrade(stateModel.preGradeFactor()) * ((getHeight() - 10) / 6.0);
            double x = i * barWidth;
            double y = getHeight() + 10 - barHeight;
            final double GRADE_THRESHOLD = 4.0;

            Rectangle gradeBar = new Rectangle(x, y, barWidth, barHeight);

            if (student.getFinalGrade(stateModel.preGradeFactor()) >= GRADE_THRESHOLD) {
                gradeBar.setFill(Color.GREEN);
                getChildren().add(gradeBar);
            } else {
                gradeBar.setFill(Color.RED);
                getChildren().add(gradeBar);
            }

            Text nameText = new Text(student.getName());
            nameText.getTransforms().add(new Translate(x + barWidth / 2, getHeight() - 20));
            nameText.getTransforms().add(new Rotate(-90));
            getChildren().add(nameText);

            for (int j = 0; j < 7; j++) {
                Line line = new Line(0, getHeight() + 10 - (j * ((getHeight() - 10) / 6.0)), getWidth(), getHeight() + 10 - (j * ((getHeight() - 10) / 6.0)));
                if (j != 4) {
                    line.setStroke(Color.BLACK);
                } else {
                    line.setStroke(Color.RED);
                }
                getChildren().add(line);
            }
        }
    }
}
