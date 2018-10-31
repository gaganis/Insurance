import java.sql.*;
import java.text.ParseException;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import static java.time.temporal.ChronoUnit.DAYS;

public class VehicleEnvironment extends InsConnectionDB {


    private ArrayList<Vehicle> getVehiclesList(String query) throws Exception{
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            ResultSet rs = preparedStatement.executeQuery(query);
            Vehicle vehicle;
            while (rs.next()){
                vehicle = new Vehicle(rs.getString("plate"), rs.getDate("end_date"),rs.getInt("owner_id"));
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
    //TODO: the calculate datediff in DB or in here?

// The user provides a plate and this method responds with "when" the insurance ends (returns a date).
    private Date getEndDateForVehicle(String plate) throws Exception {
        String query = "SELECT end_date FROM vehicle WHERE plate=?";
        Date endDate = null;

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1,plate);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                endDate = rs.getDate("end_date");
            }
        }catch (SQLException e){
        }
        return endDate;
    }

    public long checkDateDiff(Date endDate) throws ParseException {
        long dateDiff;
        LocalDate expirationDay = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        dateDiff = DAYS.between(now, expirationDay);
        return (dateDiff);
    }


    public void showEndDateForVehicle(String plate) throws Exception {

        if (getEndDateForVehicle(plate)==null){
            System.out.println(plate + " does not exist");      //TODO: check the out to be err !!!
        }else {
            System.out.println(plate + " has end date " + getEndDateForVehicle(plate));
        }
    }

    public String getVehicleStatus(String plates) throws Exception {
        //TODO: given plates -> get the vehicle status (INSURED or UNINSURED). F1

        String status;
        int dateDiff = checkDateDiff();
        if (dateDiff>0){
            status = VEHICLESTATUS.INSURED.toString();
        }else{
            status = VEHICLESTATUS.UNINSURED.toString();
        }
        return status;
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
