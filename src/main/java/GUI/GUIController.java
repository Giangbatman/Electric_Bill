package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private static GUIController controller;
    private Stage stage = new Stage();
    private Scene scene;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane leftAncho;
    @FXML
    private AnchorPane rightAncho;
    @FXML
    private SplitPane splitPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            stage.setTitle("Electrical Bills System");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.setResizable(true);
//        stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        splitPane.getItems().set(0, LeftPane.getInstance().getAnchorPane());
        splitPane.getItems().set(1, RightPane.getInstance().getAnchorPane());
    }

    public static GUIController getInstance() {
        if (controller != null) return controller;
        FXMLLoader loader = new FXMLLoader(Object.class.getResource("/GUI.fxml"));
        try {
            loader.load();
            controller = loader.getController();
//            Parent root = FXMLLoader.load(GUIController.class.getResource("/GUI.fxml"));
            controller.stage.setTitle("Electrical Bills System");
            controller.stage.setScene(new Scene(controller.getAnchorPane()));
            controller.stage.setMaximized(true);
            controller.stage.setResizable(true);
            return controller;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public void show(boolean isShow) {
        if (isShow) {
            controller.stage.show();
        }
        else {
            if (controller.stage.isShowing()) {
                controller.stage.close();
            }
        }
    }
}
