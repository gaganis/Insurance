public class Main {
    public static void main(String[] args) throws Exception {
        VehicleFacade ve = new VehicleFacade();

        Menu menu = new Menu();
        menu.choices();

//        vehicleState.getVehicleStatus("ION-5564");
//
//
//        VehicleSorting vehicleSorting = new VehicleSorting();
//        vehicleSorting.getUninsuredVehiclesInOrder(ve.getVehiclesList());
//
//        ArrayList<Vehicle> orderedVe = vehicleSorting.getUninsuredVehiclesInOrder(ve.getVehiclesList());
//        for (Vehicle veh : orderedVe) {
//            System.out.println(veh.getPlate());
//        }




        ve.CloseDBConnection();

    }

}
