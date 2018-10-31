import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        VehicleFacade ve = new VehicleFacade();

        ve.showVehiclesList();

        ve.showEndDateForVehicle("ION-5564");

        Date endDate = ve.getEndDateForVehicle("ION-5564");

        System.out.println("days are: " + ve.checkDateDiff(endDate));

        ve.getVehicleStatus("ION-5564");

        System.out.println(ve.getVehicleStatus("ION-5564"));

    }
}
