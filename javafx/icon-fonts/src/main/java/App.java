import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/12
 * Time: 23:01
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root=FXMLLoader.load(getClass().getResource("/fxml/app.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("FontAwesomeFX Icons-Overview");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
