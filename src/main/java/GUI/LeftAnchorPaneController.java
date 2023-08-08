package GUI;

import be.ElectricCenter;
import be.model.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftAnchorPaneController implements Initializable {

    @FXML
    private TextField tfMeterID;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfPrevious;
    @FXML
    private TextField tfNew;
    @FXML
    private TextField tfCash;

    @FXML
    private CheckBox cbID;
    @FXML
    private CheckBox cbName;
    @FXML
    private CheckBox cbAddress;
    @FXML
    private TextField tfsearch;
    @FXML
    private TextField tffilter;


    private Receipt onReceipt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Thêm biên lai
     * @param e
     */
    @FXML
    public void exportReceipt(ActionEvent e) {
        RightAnchorPaneController rightAnchorPaneController = RightPane.getInstance().getController();
        String id = tfMeterID.getText();
        String usrName = tfUserName.getText();
        String address = tfAddress.getText();
        int previous = Integer.parseInt(tfPrevious.getText());
        int newNum = Integer.parseInt(tfNew.getText());
        int cash = Integer.parseInt(tfCash.getText());

        Receipt receipt = new Receipt(id, usrName, address, previous, newNum, cash);
        if (receipt != null) {
            rightAnchorPaneController.addReceipt(receipt);
            clearTextFieldsAdd();
        }
    }


    /**
     * Xem biên lai
     * @param receipt
     */
    public void viewReceipt(Receipt receipt) {
        tfMeterID.setText(receipt.getMeterID());
        tfUserName.setText(receipt.getUserName());
        tfAddress.setText(receipt.getAddress());
        tfPrevious.setText(String.valueOf(receipt.getPreviousNum()));
        tfNew.setText(String.valueOf(receipt.getNewNum()));
        tfCash.setText(String.valueOf(receipt.getCash()));
        onReceipt = receipt;
    }

    /**
     * Sửa biên lai
     */
    public void editReceipt() {
        if (onReceipt == null) return;
        ObservableList<Receipt> list = RightPane.getInstance().getController().getListReceipt();
        int index = list.indexOf(onReceipt);
        onReceipt.setMeterID(tfMeterID.getText());
        onReceipt.setUserName(tfUserName.getText());
        onReceipt.setAddress(tfAddress.getText());
        onReceipt.setPreviousNum(Integer.parseInt(tfPrevious.getText()));
        onReceipt.setNewNum(Integer.parseInt(tfNew.getText()));
        onReceipt.setCash(Integer.parseInt(tfCash.getText()));
        list.set(index, onReceipt);

        onReceipt = null;
        clearTextFieldsAdd();
    }

    /**
     * Tìm kiếm biên lai theo X
     * @param e
     */
    public void search(ActionEvent e) {
        String content = tfsearch.getText();
        ObservableList<Receipt> listResult = FXCollections.observableArrayList();
        if (!content.equals("")) {
            ObservableList<Receipt> listInSys = RightPane.getInstance().getController().getListReceipt();
            /**
             * Tìm kiếm biên lai theo Mã công tơ
             */
            if (cbID.isSelected()){
                for (Receipt receipt : listInSys) {
                    if (receipt.getMeterID().equals(tfsearch.getText())) {
                        if (!listResult.contains(receipt)) listResult.add(receipt);
                    }
                }
            }
            /**
             * Tìm kiếm biên lai theo tên hộ
             */
            if (cbName.isSelected()){
                for (Receipt receipt : listInSys) {
                    if (receipt.getUserName().equals(tfsearch.getText())) {
                        if (!listResult.contains(receipt)) listResult.add(receipt);
                    }
                }
            }
            /**
             * Tìm kiếm biên lai theo địa chỉ
             */
            if (cbAddress.isSelected()){
                for (Receipt receipt : listInSys) {
                    if (receipt.getAddress().equals(tfsearch.getText())) {
                        if (!listResult.contains(receipt)) listResult.add(receipt);
                    }
                }
            }
        }
        /**
         * Hiển thị lên bảng
         */
        RightAnchorPaneController controller = RightPane.getInstance().getController();
        controller.setFilter(listResult);
        controller.viewFilter();
        clearTextFieldsSearchAndFilter();
    }

    /**
     * Lọc theo Thành tiền
     */
    @FXML
    public void filter() {
        String content = tffilter.getText();
        RightAnchorPaneController controller = RightPane.getInstance().getController();
        if (content.equals("")) {
            controller.viewAll();
            return;
        }

        ObservableList<Receipt> listResult = FXCollections.observableArrayList();
        ObservableList<Receipt> listInSys = controller.getListReceipt();
        for (Receipt receipt : listInSys) {
            if (receipt.getCash() == Integer.parseInt(tffilter.getText())) {
                if (!listResult.contains(receipt)) listResult.add(receipt);
            }
        }
        controller.setFilter(listResult);
        controller.viewFilter();
        clearTextFieldsSearchAndFilter();
    }


    public void clearTextFieldsAdd() {
        tfMeterID.clear();
        tfUserName.clear();
        tfAddress.clear();
        tfPrevious.clear();
        tfNew.clear();
        tfCash.clear();
    }

    public void clearTextFieldsSearchAndFilter() {
        tfsearch.clear();
        tffilter.clear();
    }

    /**
     * Thành tiền tự trừ
     */
    @FXML
    public void updateCash() {
        if (tfPrevious.getText().isEmpty() || tfNew.getText().isEmpty()) {
            tfCash.clear();
            return;
        }
        int prev = Integer.parseInt(tfPrevious.getText());
        int newNum = Integer.parseInt(tfNew.getText());
        tfCash.setText(String.valueOf(newNum - prev));
    }


}
