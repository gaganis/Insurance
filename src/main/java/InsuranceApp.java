public class InsuranceApp {
    public static void main(String[] args) throws Exception {
        InsuranceDB insuranceDB = new InsuranceDB();
        insuranceDB.selectInsuranceStatus();
        //insuranceDB.selectUninsuredVehicle();
        //insuranceDB.selectOwnerUninsuredVehicles();
        insuranceDB.CloseDBConnection();
    }
}
