import java.sql.*;

public class InsuranceDB {
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

    public InsuranceDB() {
        try {
            connection = getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void selectInsuranceStatus() throws SQLException {
        String status = null;
        String selectSQL =
                "select DATEDIFF((select end_date from vehicle where plate=?), curdate()) as DateDiff;";
        ResultSet result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            // execute insert SQL stetement
            preparedStatement.setString(1, "ION-5564");
            result = preparedStatement.executeQuery();
            while (result.next()) {
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //if the plate doesn't exist return Secured!!!!!!!!
                //epistrefei datediff 0 --> secured
                int datediff = result.getInt("DateDiff");
                if (datediff < 0) {
                    status = "Insecured";
                } else {
                    status = "Secured";
                }
                System.out.println("The vehicle with plate ION-5564 is " + status);
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    public void selectInsecuredVehicle() throws SQLException {
        String selectSQL =
                "select plate,end_date from vehicle where (select DATEDIFF(end_date, curdate())) between 0 and ?;";
        ResultSet result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, 60);
            result = preparedStatement.executeQuery();
            System.out.println("Result: ");
            while (result.next()) {
                String plate = result.getString("plate");
                Date expirationDate = result.getDate("end_date");
                System.out.println("The insurance for plate " + plate + " will expire " + expirationDate);
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    public void selectOwnerInsecuredVehicles() throws SQLException {
        String selectSQL =
                "select plate,end_date from vehicle where owner_id=(select owner_id from vehicle where"
                        + "(plate=?)) and (select DATEDIFF(end_date, curdate()) as DateDiff)<0;";
        ResultSet result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            // execute insert SQL stetement
            preparedStatement.setString(1, "ION-5564");
            result = preparedStatement.executeQuery();
            while (result.next()) {
                String plate = result.getString("plate");
                Date expirationDate = result.getDate("end_date");
                System.out.println("The insurance for plate " + plate + " expired in " + expirationDate);
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }



}
