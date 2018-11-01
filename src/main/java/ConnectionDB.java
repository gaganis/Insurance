import java.sql.*;

class ConnectionDB {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/insurance?useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";
    private static Connection connection;

    public static Connection getDBConnection() throws Exception {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }

    public static void CloseDBConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public ConnectionDB() {
        try {
            connection = getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
