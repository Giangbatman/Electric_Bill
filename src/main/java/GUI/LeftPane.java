package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class LeftPane {

    private  AnchorPane anchorPane;
    private LeftAnchorPaneController controller;
    private static LeftPane instance;

    public  AnchorPane getAnchorPane() {
        if (anchorPane == null) {
            loadAnchorPane();
        }
        return anchorPane;
    }

    public LeftAnchorPaneController getController() {
        return controller;
    }

    public static LeftPane getInstance() {
        if (instance == null) {
            instance = new LeftPane();
            instance.loadAnchorPane();
        }
        return instance;
    }

    public void setController(LeftAnchorPaneController controller) {
        this.controller = controller;
    }

    public void loadAnchorPane() {
        try {
            FXMLLoader loader = new FXMLLoader(RightPane.class.getResource("/left_anchor.fxml"));
            this.anchorPane = loader.load();
            controller = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
