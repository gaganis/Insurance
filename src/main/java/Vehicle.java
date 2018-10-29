import java.util.Date;

public class Vehicle {
    private String plate;
    private Date expirationDate;
    private int ownerId;

    public  Vehicle(String p,Date eD,int owId){
        plate=p;
        expirationDate=eD;
        ownerId=owId;
    }
    public String getPlate() {
        return plate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getOwnerId() {
        return ownerId;
    }

}


