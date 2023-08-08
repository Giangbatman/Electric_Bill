package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class RightPane {

    private  AnchorPane anchorPane;
    private RightAnchorPaneController controller;
    private static RightPane instance;

    public  AnchorPane getAnchorPane() {
        if (anchorPane == null) {
            loadAnchorPane();
        }
        return anchorPane;
    }

    public RightAnchorPaneController getController() {
        return controller;
    }

    public static RightPane getInstance() {
        if (instance == null) {
            instance = new RightPane();
            instance.loadAnchorPane();
        }
        return instance;
    }

    public void setController(RightAnchorPaneController controller) {
        this.controller = controller;
    }

    public void loadAnchorPane() {
        try {
            FXMLLoader loader = new FXMLLoader(RightPane.class.getResource("/right_anchor.fxml"));
            this.anchorPane = loader.load();
            controller = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
