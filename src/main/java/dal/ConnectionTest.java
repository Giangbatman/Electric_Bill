package dal;

import be.model.Receipt;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionTest {
    public static final String METER_ID = "MaSoCongTo";
    public static final String USER_NAME = "HoTenChuHo";
    public static final String ADDRESS = "DiaChi";
    public static final String PREV_NUMBER = "ChiSoCu";
    public static final String NEW_NUMBER = "ChiSoMoi";
    public static final String CASH = "ThanhTien";
    public static void main(String[] args) throws SQLException {
        try {
            String dbURL = "jdbc:sqlserver://MAY-36\\SQLEXPRESS:1433;databaseName=TEST;user=sa;password=123";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }

        DBHelper.getConnection();

        String query = "SELECT*FROM  LOGINDATA";
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
            System.out.println(receipt);
        }
        DBHelper.closeConnection();
    }
}