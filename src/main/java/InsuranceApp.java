import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

public class InsuranceApp {
    public static void main(String[] args) throws Exception {
        InsuranceDB insuranceDB = new InsuranceDB();

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        int choice = 0;
        String plate = null;
        int timeFrame = 0;
        Matcher m;
        do {
            System.out.println("---Select Functionality to perform:");
            System.out.println("*1 Vehicle Isurance Status ");
            System.out.println("*2 Forecoming Expiries ");
            System.out.println("*3 Sorting of the plates-number ");
            choice = Integer.parseInt(bufferedReader.readLine());
        } while (choice != 1 && choice != 2 && choice != 3);

        switch (choice) {
            case 1:
                //do {
                System.out.println("Give the plates with format 'ABC-1234'");
                plate = bufferedReader.readLine();
                //check format
                //} while ();

                String returnedStatus = insuranceDB.selectInsuranceStatus(plate);
                if (returnedStatus == "doesn't exist"){
                    System.out.println("This plate doesn't exist in the database");
                }else {
                    System.out.println("The vehicle with plate " + plate + " is " + returnedStatus);
                    ArrayList<Vehicle> OtherUninsuredVehicle;
                    OtherUninsuredVehicle = insuranceDB.selectOwnerUninsuredVehicles(plate);
                    if (OtherUninsuredVehicle.isEmpty()) {
                        System.out.println("This owner has no uninsured vehicles");
                    } else {
                        int count = 0;
                        for (Vehicle element : OtherUninsuredVehicle) {
                            System.out.println("The insurance for plate " + element.getPlate() + " expired in " + element.getExpirationDate());
                            count++;

                        }
                        if (count > 0) {
                            System.out.println("Give the fine per uninsured vehicle");
                            double fine = Double.parseDouble(bufferedReader.readLine());
                            double total_fine = fine * count;
                            System.out.println("The total fine is " + total_fine);
                        } else {
                            System.out.println("This owner has no uninsured vehicles");
                        }
                    }
                }
                insuranceDB.CloseDBConnection();
                break;
            case 2:
                do {
                    System.out.println("Give the time frame in which the expired plates will be found");
                    timeFrame = Integer.parseInt(bufferedReader.readLine());
                } while (timeFrame < 0);
                ArrayList<Vehicle> platesToExpire;
                platesToExpire = insuranceDB.selectUninsuredVehicle(timeFrame);
                insuranceDB.CloseDBConnection();
                do {
                    System.out.println("---Enter Export Type:");
                    System.out.println("*1 File ");
                    System.out.println("*2 Console ");
                    choice = Integer.parseInt(bufferedReader.readLine());
                } while (choice != 1 && choice != 2);
                if (choice == 1) {
                    //create class for exporting
                } else {
                    for (Vehicle elements : platesToExpire) {
                        System.out.println(elements.getPlate() + " " + elements.getExpirationDate() + " " + elements.getOwnerId());
                    }
                }
                break;
            case 3:
                ArrayList<String> Vehicles;
                Vehicles = insuranceDB.selectOrderVehicle();
                insuranceDB.CloseDBConnection();
                Collections.sort(Vehicles);
                for (String element : Vehicles) {
                    System.out.println(element);
                }
                break;
        }
    }
}
