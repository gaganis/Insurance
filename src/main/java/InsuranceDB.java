import java.sql.*;
import java.util.ArrayList;

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


    public String selectInsuranceStatus(String plateToCheck) throws SQLException {
        String status = null;
        String selectSQL =
                "select DATEDIFF((select end_date from vehicle where plate=?), curdate()) as DateDiff;";
        ResultSet result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            // execute insert SQL stetement
            preparedStatement.setString(1, plateToCheck);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                String datediffReturned = result.getString("DateDiff");
                if (datediffReturned == null) {
                    status="doesn't exist";
                } else {
                    int datediffR = Integer.parseInt(datediffReturned);
                    if (datediffR < 0) {
                        status = "Uninsured";
                    } else {
                        status = "Insured";
                    }
                }
            }
            return(status);
        } finally {
            if (result != null) {
                result.close();
            }
        }

    }

    public ArrayList<Vehicle> selectUninsuredVehicle(int timeFrame) throws SQLException {
        ArrayList<Vehicle> vehicleList = new ArrayList();
        String selectSQL =
                "select plate,end_date,owner_id from vehicle where (select DATEDIFF(end_date, curdate())) between 0 and ?;";
        ResultSet result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, timeFrame);
            result = preparedStatement.executeQuery();
            returnVehicle(vehicleList, result);
            return(vehicleList);
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    public ArrayList<Vehicle> selectOwnerUninsuredVehicles(String plateToCheck) throws SQLException {
        ArrayList<Vehicle> vehicleList = new ArrayList();
        String selectSQL =
                "select plate,end_date,owner_id from vehicle where owner_id=(select owner_id from vehicle where"
                        + "(plate=?)) and (select DATEDIFF(end_date, curdate()) as DateDiff)<0;";
        ResultSet result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            // execute insert SQL stetement
            preparedStatement.setString(1, plateToCheck);
            result = preparedStatement.executeQuery();
            returnVehicle(vehicleList, result);
            return(vehicleList);
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }
    public ArrayList<String> selectOrderVehicle() throws SQLException {
        ArrayList<String> vehicleList = new ArrayList();
        String selectSQL =
                "select * from vehicle;";
        ResultSet result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            result = preparedStatement.executeQuery();
            while (result.next()) {
                String plate = result.getString("plate");
                vehicleList.add(plate);
            }
            return(vehicleList);
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }


    private void returnVehicle(ArrayList<Vehicle> vehicleList, ResultSet result) throws SQLException {
        while (result.next()) {
            String plate = result.getString("plate");
            Date expirationDate = result.getDate("end_date");
            int ownerId = result.getInt("owner_id");
            Vehicle vehicle=new Vehicle(plate,expirationDate,ownerId);
            vehicleList.add(vehicle);
        }
    }
}
