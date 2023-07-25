package gui;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;

public class MainPane extends StackPane {
    public MainPane() {
        StateModel stateModel = new StateModel();

        GraphicsPane graphicsPane = new GraphicsPane(stateModel);
        ControlPane controlPane = new ControlPane(stateModel);

        final SplitPane verticalSplitter = new SplitPane();
        verticalSplitter.setOrientation(Orientation.HORIZONTAL);
        verticalSplitter.setDividerPosition(0, 0.2);
        verticalSplitter.getItems().addAll(controlPane, graphicsPane);

        getChildren().add(verticalSplitter);
    }
}
