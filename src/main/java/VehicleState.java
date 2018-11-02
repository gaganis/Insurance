import java.util.Date;

public class VehicleState {





    public Enum getVehicleStatus(String plate) throws Exception {
        Enum status;

        DateDiffInDays dateDiffInDays = new DateDiffInDays();
        VehicleFacade vf = new VehicleFacade();

        Date endDate = vf.getEndDateForVehicle(plate);

        long dateDiff = dateDiffInDays.checkDateDiff(endDate);

        if (dateDiff<=0.0){
            status = VehicleStatus.UNINSURED;
        }else{
            status = VehicleStatus.INSURED;
        }
        return status;
    }

}
