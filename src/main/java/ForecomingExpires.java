import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForecomingExpires {

    public ArrayList<Vehicle> getVehiclesThatWillBeExpired(long timeframe) throws Exception {
        DateDiffInDays diffInDays = new DateDiffInDays();
        VehicleFacade vehicleFacade = new VehicleFacade();
        ArrayList<Vehicle> expiredVehicle = new ArrayList<>();

        ArrayList<Vehicle> vehicles = vehicleFacade.getVehiclesList();

        for (Vehicle vehicle: vehicles){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = dateFormat.parse(vehicle.getEndDate());
            long days = diffInDays.checkDateDiff(endDate);

            if (days<=timeframe && days>=0){
                expiredVehicle.add(vehicle);
            }
        }

        return expiredVehicle;
    }

    public void printForecomingExpires(ArrayList<Vehicle> vehicleList) throws Exception {
        for (Vehicle vehicle : vehicleList) {
            System.out.println("The vehicle " +vehicle.getPlate()+ " will be expired at " + vehicle.getEndDate());
        }
    }
}
