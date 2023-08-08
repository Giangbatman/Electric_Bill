package GUI;

import be.ElectricCenter;
import be.Utils;
import be.model.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RightAnchorPaneController implements Initializable{

    @FXML
    private TableView<Receipt> table;
    @FXML
    private TableColumn<Receipt, String> meterID;
    @FXML
    private TableColumn<Receipt, String> userName;
    @FXML
    private TableColumn<Receipt, String> address;
    @FXML
    private TableColumn<Receipt, Integer> previousNum;
    @FXML
    private TableColumn<Receipt, Integer> newNum;
    @FXML
    private TableColumn<Receipt, Integer> cash;
//    @FXML
//    private TableColumn<Cadres, Integer> serveCol;

    private ObservableList<Receipt> listReceipt = FXCollections.observableArrayList();
    private ObservableList<Receipt> filter;

    public ObservableList<Receipt> getListReceipt() {
        return listReceipt;
    }

    public void setListReceipt(ObservableList<Receipt> listReceipt) {
        this.listReceipt = listReceipt;
    }

    public ObservableList<Receipt> getFilter() {
        return filter;
    }

    public void setFilter(ObservableList<Receipt> filter) {
        this.filter = filter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        meterID.setCellValueFactory(new PropertyValueFactory<Receipt, String>("meterID"));
        userName.setCellValueFactory(new PropertyValueFactory<Receipt, String>("userName"));
        address.setCellValueFactory(new PropertyValueFactory<Receipt, String>("address"));
        previousNum.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("previousNum"));
        newNum.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("newNum"));
        cash.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("cash"));
//        serveCol.setCellValueFactory(new PropertyValueFactory<Cadres, Integer>("serve"));
//        listCadres = ElectricCenter.getInstance().getListCadres(); todo: change here
        ElectricCenter center = ElectricCenter.getInstance();
        listReceipt = center.getListReceipt();
        table.setItems(listReceipt);
        table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        table.setOnMouseClicked(event -> {
            Receipt receiptOnClicked = table.getSelectionModel().getSelectedItem();
            if (receiptOnClicked != null) {
                LeftAnchorPaneController leftAnchorPaneController = LeftPane.getInstance().getController();
                leftAnchorPaneController.viewReceipt(receiptOnClicked);
            }
        });
    }

    /**
     * thêm 1 biên lai vào bảng
     * @param receipt
     */
    public void addReceipt(Receipt receipt) {
        if (!listReceipt.contains(receipt)) {
            listReceipt.add(receipt);
        }
    }

    /**
     * Xóa 1 hoặc nhiều biên lai cùng 1 lúc
     * @param event
     */
    @FXML
    public void delete(ActionEvent event) {
        ObservableList<Receipt> listSelected = table.getSelectionModel().getSelectedItems();
        int count = listSelected.size();
        listReceipt.removeAll(listSelected);
        Utils.showAlert("Xóa biên lai", "Đã xóa " + count + " biên lai thành công");
    }

    /**
     * Xóa hết
     * @param event
     */
    @FXML
    public void deleteAll(ActionEvent event) {
        listReceipt.clear();
        Utils.showAlert("Xóa biên lai", "Xóa toàn bộ biên lai thành công");
    }

    public void viewFilter() {
        table.setItems(getFilter());
    }

    public void viewAll() {
        table.setItems(getListReceipt());
    }

    /**
     * Xuất database
     * @param event
     */
    @FXML
    public void saveTable(ActionEvent event) {
        ObservableList<Receipt> listCadresOnTable = table.getItems();
        ElectricCenter center = ElectricCenter.getInstance();
        try {
            center.clearDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Receipt receipt : listCadresOnTable) {
            try {
                center.exportToReceipt(receipt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Utils.showAlert("Xuất dữ liệu", "Xuất dữ liệu ra database thành công");
    }


    @FXML
    public void viewDefault() {
        viewAll();
    }

}
