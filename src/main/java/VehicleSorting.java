import java.util.ArrayList;
import java.util.Collections;

public class VehicleSorting {
    public ArrayList<Vehicle> getUninsuredVehiclesInOrder(ArrayList<Vehicle> Vehicles){
        VehicleCompare vehicleCompare= new VehicleCompare();
        Collections.sort(Vehicles,vehicleCompare);
        return(Vehicles);
    }
}
