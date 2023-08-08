import GUI.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        primaryStage.setTitle("Electrical Bills System");
        primaryStage.setScene(new Scene(LoginController.getInstance().getAnchorPane()));
        LoginController.getInstance().setStage(primaryStage);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}