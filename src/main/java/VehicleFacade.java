import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class VehicleFacade extends InsConnectionDB {


    private ArrayList<Vehicle> getVehiclesList(String query) throws Exception{
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

    public void showVehiclesList() throws Exception {
        String query = "SELECT * FROM vehicle";
        ArrayList<Vehicle> list = getVehiclesList(query);

        System.out.println("All the plates are: ");
        list.stream().map(Vehicle::getPlate).forEach(System.out::println);
    }


    //TODO: check if the Plate exist?


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

    public long checkDateDiff(Date endDate) throws ParseException {
        long dateDiff;
        LocalDate expirationDay = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        dateDiff = DAYS.between(now, expirationDay);
        return (dateDiff);
    }

    public Enum getVehicleStatus(String plate) throws Exception {
        Enum status;
        long dateDiff = checkDateDiff(getEndDateForVehicle(plate));

        System.out.println("dateDiff = " + dateDiff);
        if (dateDiff<=0.0){
            status = VehicleStatus.UNINSURED;
        }else{
            status = VehicleStatus.INSURED;
        }
        return status;
    }


    public void showEndDateForVehicle(String plate) throws Exception {

        if (getEndDateForVehicle(plate)==null){
            System.out.println(plate + " does not exist");      //TODO: check the out to be err !!!
        }else {
            System.out.println(plate + " has end date " + getEndDateForVehicle(plate));
        }
    }

    private void  getVehiclesThatWillBeExpiredIn(String dateDiff){
        // TODO: given days -> get a list of the vehicles that will be expired in givenDays. F2
        // TODO: selectable export format.
    }

    private void getOwnersVehiclesThatAreUninsured(){
        // TODO: given owner -> return a list his vehicles that are UNINSURED. F4 (1/2)
        // TODO: send this list to be sorted in Function.
    }
}
