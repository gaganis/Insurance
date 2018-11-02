import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {

    public String readFromTheKeyboard() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String userInput = bufferedReader.readLine();
        return (userInput);
    }

    public boolean checkIfPlateFormatIsRight(String plate) throws IOException {


        System.out.println("Please enter your plate number: ");
        boolean format = false;

        Matcher m = Pattern.compile("[A-Z][A-Z][A-Z](-)\\d\\d\\d\\d").matcher(readFromTheKeyboard());
        if (m.find()) {
            format = true;

        } else {
            return false;
        }


    public void printInCsv(){
    }

    public void chooseExportFormat() {
        //TODO: ask the Export (Screen or CSV) file format. F2
    }

    public ArrayList<Vehicle> getUninsuredVehiclesInOrder(ArrayList<Vehicle> Vehicles) {
        VehicleCompare vehicleCompare = new VehicleCompare();
        Collections.sort(Vehicles, vehicleCompare);
        return (Vehicles);
    }

    public void calcFine() {
        //TODO: Take the number of UNINSURED vehicles of an owner -> calculate total fine. F4 (2/2)
    }
}
