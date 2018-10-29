import java.sql.*;
import java.util.ArrayList;

public class VehicleEnvironment extends InsuranceDB{

    private ArrayList<Vehicle> getVehiclesList() throws Exception{
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        try {
            ResultSet rs = getPreparedStatement("SELECT * FROM vehicle");
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

    public void showVehiclesList() throws Exception {   // F3 (1/2)
        ArrayList<Vehicle> list = getVehiclesList();
        System.out.println("All the plates are: ");
        list.stream().map(Vehicle::getPlate).forEach(System.out::println);
    }

    private ArrayList<String> getVehicleForTheOwner(int id) throws Exception {
        ArrayList<Vehicle> list = getVehiclesList();
        ArrayList<String> ownersVehiclesList = new ArrayList<>();

        for (Vehicle aList : list) {
            if (aList.getOwnerId() == id) {
                ownersVehiclesList.add(aList.getPlate());
            }
        }
        return ownersVehiclesList;
    }

    public void showVehiclesBelongsToOwner(int id) throws Exception {
        System.out.println("Owner with id " + id + " has: " + getVehicleForTheOwner(id));
    }

    public void getVehicleStatus(String plates){
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
