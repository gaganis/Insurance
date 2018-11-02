import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlateValidation {

    public boolean checkIfPlateFormatIsRight(String plate){     // F1
        boolean validation = false;
        Matcher m = Pattern.compile("ˆ[A-Z][A-Z][A-Z](-)\\d\\d\\d\\d$").matcher(plate);
        if (m.find()) {
            validation = true;
        } else {
            validation = false;
            System.err.println("\nYou provide an unknown pattern!!");
        }
        return validation;
    }
}
