package GUI;

import be.ElectricCenter;
import be.login.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage stage;
    @FXML
    private AnchorPane anchorPane;

    private static LoginController controller;

    @FXML
    private TextField tfname;
    @FXML
    private TextField tfpass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public static LoginController getInstance() {
        if (controller != null) return controller;
        FXMLLoader loader = new FXMLLoader(Object.class.getResource("/login.fxml"));
        try {
            loader.load();
            controller = loader.getController();

            return controller;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void login() {
        String name = tfname.getText();
        String pass = tfpass.getText();
        ElectricCenter center = ElectricCenter.getInstance();
        List<Account> accountList = center.getListAccount();
        for (Account account : accountList) {
            if (account.getName().equals(name) && account.getPassword().equals(pass)) {
                GUIController guiController = GUIController.getInstance();
                guiController.show(true);
                clear();
                close();
                break;
            }
            else GUIController.getInstance().show(false);
        }
    }

    public void clear() {
        tfname.clear();
        tfpass.clear();
    }

    public void close() {
        stage.close();
    }
}
