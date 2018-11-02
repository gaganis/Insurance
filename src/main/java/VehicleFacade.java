import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VehicleFacade extends ConnectionDB {


    public ArrayList<Vehicle> getVehiclesList() throws Exception{
        String query = "SELECT * FROM vehicle";
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =getDBConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery(query);
            Vehicle vehicle;
            while (rs.next()){
                vehicle = new Vehicle(rs.getString("plate"),
                        rs.getString("end_date"),rs.getInt("owner_id"));
                vehicleList.add(vehicle);
            }
        }catch (SQLException e){
        }
        return vehicleList;
    }

    public Date getEndDateForVehicle(String plate) throws Exception {
        String query = "SELECT end_date FROM vehicle WHERE plate=?";
        String endDate = null;

        try {
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(query);
            preparedStatement.setString(1,plate);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                endDate = rs.getString("end_date");
            }
        }catch (SQLException e){
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date day = dateFormat.parse(endDate);
        return day;
    }

    public boolean checkIfThePlateExists(String plate) throws Exception {

        boolean existence = false;

        if (getEndDateForVehicle(plate)==null){
            existence = false;
        }else {
            existence = true;
        }
        return existence;
    }

    private void getOwnersVehiclesThatAreUninsured(){
        // TODO: given owner -> return a list his vehicles that are UNINSURED. F4 (1/2)
        // TODO: send this list to be sorted in Function.
    }
}
