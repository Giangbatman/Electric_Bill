package be;

import be.login.Account;
import be.model.Receipt;
import dal.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ElectricCenter {
    private static ElectricCenter instance;
    private ObservableList<Receipt> listReceipt = FXCollections.observableArrayList();
    private List<Account> listAccount = new ArrayList<>();

    public static final String METER_ID = "MaSoCongTo";
    public static final String USER_NAME = "HoTenChuHo";
    public static final String ADDRESS = "DiaChi";
    public static final String PREV_NUMBER = "ChiSoCu";
    public static final String NEW_NUMBER = "ChiSoMoi";
    public static final String CASH = "ThanhTien";

    /**
     * Server config
     */
    private static String SERVER = "MAY-36\\SQLEXPRESS";
    private static int PORT = 1433;
    private static String DATABASE_NAME = "TEST";
    private static String USER_NAME_SERVER = "sa";
    private static String PASSWORD = "123";

    public static ElectricCenter getInstance() {
        if (instance == null) {
            instance = new ElectricCenter();
            try {
                instance.readDatabase(SERVER, PORT, DATABASE_NAME, USER_NAME_SERVER, PASSWORD);
                instance.setListAccount(instance.getAccountFromDatabase());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public List<Account> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public void setListReceipt(ObservableList<Receipt> listReceipt) {
        this.listReceipt = listReceipt;
    }

    public ObservableList<Receipt> getListReceipt() {
        if (listReceipt == null){
            listReceipt = FXCollections.observableArrayList();
            //jdbc:sqlserver://MAY-37\SQLEXPRESS:1433;databaseName=TEST;user=sa;password=sa
            try {
                readDatabase(SERVER, PORT, DATABASE_NAME, USER_NAME_SERVER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return listReceipt;
    }

    /**
     * Đọc dữ liệu biên lai từ database
     * @param serverName
     * @param port
     * @param databaseName
     * @param user
     * @param password
     * @throws SQLException
     */
    public void readDatabase(String serverName, int port, String databaseName, String user, String password) throws SQLException {
        String url = "jdbc:sqlserver://" + serverName + ":" + String.valueOf(port)
                + ";databaseName=" + databaseName + ";user=" + user + ";password=" + password;
        DBHelper.setUrl(url);
        DBHelper.getConnection();

        String query = "SELECT*FROM  Electrical_Bill";
        ResultSet resultSet = DBHelper.executeQuery(query);
        Receipt receipt = null;
        while (resultSet.next()) {
            String id = resultSet.getString(METER_ID);
            String name = resultSet.getString(USER_NAME);
            String address = resultSet.getString(ADDRESS);
            int prev = resultSet.getInt(PREV_NUMBER);
            int new_num = resultSet.getInt(NEW_NUMBER);
            int cash = resultSet.getInt(CASH);
            receipt = new Receipt(id, name, address, prev, new_num, cash);
            listReceipt.add(receipt);
            System.out.println(receipt);
        }
        DBHelper.closeConnection();
    }

    /**
     * Xuất 1 biên lai ra database
     * @param receipt
     * @throws SQLException
     */
    public void exportToReceipt(Receipt receipt) throws SQLException {
        //INSERT INTO Electrical_Bill VALUES ('01',N'Nguyễn Trường Giang',N'Xuân Phương',5,8,3)
        String id = receipt.getMeterID();
        String name = receipt.getUserName();
        String adrress = receipt.getAddress();
        int prev = receipt.getPreviousNum();
        int newNum = receipt.getNewNum();
        int cash = receipt.getCash();
        String query = "INSERT INTO Electrical_Bill VALUES (\'" + id + "\',N\'" + name + "\',N\'" + adrress + "\',"
                + prev + "," + newNum + "," + cash + ")";
        DBHelper.getConnection();
        DBHelper.executeQuery(query);
        DBHelper.closeConnection();
    }

    /**
     * Xóa toàn bộ dữ liệu biên lai khỏi database -> database trống
     * @throws SQLException
     */
    public void clearDatabase() throws SQLException {
        String query ="DELETE FROM Electrical_Bill";
        DBHelper.getConnection();
        DBHelper.executeQuery(query);
        DBHelper.closeConnection();
    }

    /**
     * khởi tạo bảng Electrical Bill
     * @throws SQLException
     */
    public void createElectricalBillTable() throws SQLException {
        String query = "CREATE TABLE Electrical_Bill ( MaSoCongTo varchar(20) PRIMARY KEY , HoTenChuHo nvarchar(30) NOT NULL, DiaChi nvarchar(30) NOT NULL, ChiSoCu int ,ChiSoMoi int  , ThanhTien int )";
        DBHelper.getConnection();
        DBHelper.executeQuery(query);
        DBHelper.closeConnection();
    }

    /**
     * Khởi tạo bảng  Login
     * @throws SQLException
     */
    public void createLoginTable() throws SQLException {
        String query = "CREATE TABLE LOGINDATA ( Username varchar(20) NOT NULL, Pass varchar(20) NOT NULL)";
        DBHelper.getConnection();
        DBHelper.executeQuery(query);
        DBHelper.closeConnection();
    }

    /**
     * Lấy ra danh dách Account từ database
     * @return
     * @throws SQLException
     */
    public List<Account> getAccountFromDatabase() throws SQLException {
        //SELECT*FROM  LOGINDATA
        String query = "SELECT*FROM  LOGINDATA";
        DBHelper.getConnection();
        DBHelper.executeQuery(query);

        ResultSet resultSet = DBHelper.executeQuery(query);
        List<Account> accountList = new ArrayList<>();
        Account account = null;
        while (resultSet.next()) {
            String username = resultSet.getString("Username");
            String password = resultSet.getString("Pass");

            account = new Account(username, password);
            accountList.add(account);
            System.out.println(account);
        }
        DBHelper.closeConnection();
        return accountList;
    }

    public static void main(String[] args) {
        ElectricCenter center = getInstance();
        Receipt receipt = new Receipt("d", "b", "c", 1, 2, 1);
    }

}
