import gui.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClassRoomFinal extends Application {

    @Override
    public void start(Stage stage) {
        Pane mainPane = new MainPane();

        StackPane rootPane = new StackPane(mainPane);
        Scene scene = new Scene(rootPane, 1000, 700);

        stage.setScene(scene);
        stage.setTitle("Classroom App");
        stage.getIcons().add(new Image("C:\\Users\\milic\\IdeaProjects\\ClassRoomApp09\\src\\daten\\FHNW-LOGO.jpeg"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
