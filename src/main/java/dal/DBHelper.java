package dal;

import java.sql.*;
import java.util.List;

public class DBHelper {
    public static String server_name = "LAPTOP-AV4JT0P8\\SQLEXPRESS";
    public static int port = 1433;
    public static String dbname = "EMPDB";
    public static String user_name = "sa";
    public static String password = "sa";

    public static String url = "";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
//        if (connection != null) {
//            connection
//            System.out.println("Opened Connection");
//            return connection;
//        }
        //String url = "jdbc:mysql://localhost:3306/time_table";
        //"jdbc:sqlserver://LAPTOP-AV4JT0P8\\SQLEXPRESS:1433;databaseName=EMPDB;user=sa;password=sa"
//        String url = getSQLServerURL();
        String dbURL = "jdbc:sqlserver://MAY-37\\SQLEXPRESS:1433;databaseName=TEST;user=sa;password=sa";
        connection = DriverManager.getConnection(url);
//        connection = DriverManager.getConnection(url);
        System.out.println("Open Connection");
        return connection;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DBHelper.url = url;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Close connection");
        }
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        ResultSet resultSet;
        PreparedStatement statement = connection.prepareStatement(query);
        resultSet = statement.executeQuery();
        return resultSet;
    }
    public static void executeQuerySubject(String query, List<Object> objectList) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < objectList.size(); i++) {
            Object o = objectList.get(i);
            if (o instanceof String) {
                statement.setString(i+1, (String) o);
            }
            else statement.setInt(i+1, (Integer) o);
        }
        statement.executeQuery();
    }

    public static String getSQLServerURL() {
        //"jdbc:sqlserver://LAPTOP-AV4JT0P8\\SQLEXPRESS:1433;databaseName=EMPDB;user=sa;password=sa"
        return "jdbc:sqlserver://" + server_name +":" + String.valueOf(port) + ";databaseName=" + dbname + ";user=" + user_name + ";password=" + password;
    }

    public static void main(String[] args) throws SQLException {
        Connection connection1 = DBHelper.getConnection();
        String query = "SELECT * from subject where subject_id='MH01'";
//        Subject subject = new SubjectDAL().getSubjectById("MH01");
    }
}