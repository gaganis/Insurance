import java.util.ArrayList;

public class Menu {

    public void choices() throws Exception {
        VehicleFacade vehicleFacade = new VehicleFacade();
        ForecomingExpires forecomingExpires = new ForecomingExpires();
        PlateValidation plateValidation = new PlateValidation();
        int choice = 0;
        String sortChoice = "";
        long timeframe = 0;
        String plate = "";
        Functions functions = new Functions();
        VehicleState vehicleState = new VehicleState();

        do {
            System.out.println("---Select Functionality to perform:");
            System.out.println("*1 Vehicle Isurance Status ");
            System.out.println("*2 Forecoming Expiries ");
            System.out.println("*3 Calculation of total fine cost ");
            choice = Integer.parseInt(functions.readFromTheKeyboard());
        } while (choice != 1 && choice != 2 && choice != 3);

        switch (choice) {
            case 1:

                do {
                System.out.println("Give the plate with format 'ABC-1234'");
                plate = functions.readFromTheKeyboard();
                } while (!plateValidation.checkIfPlateFormatIsRight(plate));

                System.out.println("The vehicle with the plate " + plate
                        + " is " + vehicleState.getVehicleStatus(plate));
                break;

            case 2:
                do {
                    System.out.println("Give the time frame in which the expired plates will be found");
                    timeframe = Long.parseLong(functions.readFromTheKeyboard());
                } while (timeframe < 0);

                ArrayList<Vehicle> vehicleList = forecomingExpires.getVehiclesThatWillBeExpired(timeframe);

                do {
                    System.out.print("Would you like to be sorted in alphanumerical order");
                    System.out.println(" Y or N ?");
                    sortChoice = functions.readFromTheKeyboard();
                }while(sortChoice == "Y" || sortChoice == "y"  || sortChoice == "N" || sortChoice == "n" );

                if (sortChoice=="Y" || sortChoice=="y"){
                    VehicleSorting vehicleSorting = new VehicleSorting();
                    vehicleSorting.getUninsuredVehiclesInOrder(vehicleList);
                }

                do {
                    System.out.println("---Enter Export Type:");
                    System.out.println("*1 File in .csv");
                    System.out.println("*2 Console ");
                    choice = Integer.parseInt(functions.readFromTheKeyboard());
                } while (choice != 1 && choice != 2);

                if (choice == 1) {
                    CsvFormat csv = new CsvFormat();
                    csv.writeToCsv(vehicleList);
                    //csv
                } else {
                    forecomingExpires.printForecomingExpires(vehicleList);
                }
                break;

            case 3:
                System.out.println("Give the owner's name");
//                String ownerName = bufferedReader.readLine();
                break;
        }

    }
}
