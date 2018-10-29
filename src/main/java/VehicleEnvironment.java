import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class VehicleEnvironment extends InsuranceDB{


    private ArrayList<Vehicle> getVehiclesList(String query) throws Exception{

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            ResultSet rs = preparedStatement.executeQuery(query);
            Vehicle vehicle;
            while (rs.next()){
                vehicle = new Vehicle(rs.getString("plate"), rs.getString("end_date"),rs.getInt("owner_id"));
                vehicleList.add(vehicle);
            }
        }catch (SQLException e){
        }
        return vehicleList;
    }

    public void showVehiclesList() throws Exception {
        String query = "SELECT * FROM vehicle";
        ArrayList<Vehicle> list = getVehiclesList(query);

        System.out.println("All the plates are: ");
        list.stream().map(Vehicle::getPlate).forEach(System.out::println);
    }



    //todo: check if the Plate exist?
    //todo: the calculate datediff in DB or in here?

    public void selectInsuranceStatus() throws Exception {
        String status;
        ResultSet rs = null;
        String query = "select DATEDIFF((select end_date from vehicle where plate=?), curdate()) as DateDiff;";
        try  {

            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, "ION-5564");         //todo: from the user get a String
            rs = preparedStatement.executeQuery(query);

            while (rs.next()) {
                String dateDiffReturned = rs.getString("DateDiff");
                if (dateDiffReturned == null) {
                    System.out.println("This plate doesn't exist in the database");
                } else {
                    int datediffR = Integer.parseInt(dateDiffReturned);
                    if (datediffR < 0) {
                        status = VEHICLETATUS.INSURED.toString();
                    } else {
                        status = VEHICLETATUS.UNINSURED.toString();
                    }
                    System.out.println("The vehicle with plate ION-5564 is " + status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }


    private String getEndDateForVehicle(String plate) throws Exception {
        String query = "SELECT end_date FROM vehicle WHERE plate=?";

        String endDate= "";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1,plate);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                endDate = rs.getString("end_date");
            }
        }catch (SQLException e){
        }
        return endDate;
    }

    public int checkDateDiff() throws ParseException {
        int dateDiff = -1;

        String endDate = "2018-10-28";
        Date oldDate = new SimpleDateFormat("yyyy-mm-dd").parse(endDate);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date nowDate = new Date();
        System.out.println(dateFormat.format(nowDate));

        //todo: calculate date diff

        return dateDiff;
    }

    public void showEndDateForVehicle(String plate) throws Exception {
        if (getEndDateForVehicle(plate)==""){
            System.out.println(plate + " does not exist");
        }else {
            System.out.println(plate + " has end date " + getEndDateForVehicle(plate));
        }
    }


    public void getVehicleStatus(String plates) throws Exception {
        //TODO: given plates -> get the vehicle status (INSURED or UNINSURED). F1
    }

    public void  getVehiclesThatWillBeExpiredIn(String dateDiff){
        // TODO: given days -> get a list of the vehicles that will be expired in givenDays. F2
        // TODO: selectable export format.
    }

    public void getOwnersVehiclesThatAreUninsured(){
        // TODO: given owner -> return a list his vehicles that are UNINSURED. F4 (1/2)
        // TODO: send this list to be sorted in Function.
    }
}
