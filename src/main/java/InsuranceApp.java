import java.util.regex.Matcher;

public class InsuranceApp {
    public static void main(String[] args) throws Exception {
        InsuranceDB insuranceDB = new InsuranceDB();
//        insuranceDB.selectInsuranceStatus();
//        insuranceDB.selectInsecuredVehicle();
//        insuranceDB.selectOwnerInsecuredVehicles();

        int choice = 0;

        Matcher m;
        do {
            System.out.println("---Select Functionality to perform:");
            System.out.println("*1 Vehicle Isurance Status ");
            System.out.println("*2 Forecoming Expiries ");
            choice = Integer.parseInt(readFromTheKeyboard());
        }while (choice != 1 && choice != 2);
        switch (choice) {
            case 1:
                do {
                System.out.println("Give the plates with format 'ABC-1234'");
                plate = bufferedReader.readLine();
                //check format
                //} while ();
            //System.out.println("*3 Sorting of the plates-number ");
            //choice = Integer.parseInt(bufferedReader.readLine());


            VehicleEnvironment ve = new VehicleEnvironment();

            ve.showVehiclesList();

            ve.showEndDateForVehicle("\nION-5564");

            insuranceDB.CloseDBConnection();

        }
    }
