import java.util.Date;

public class Vehicle {

    private String plate;
    private Date endDate;
    private int ownerId;


    public Vehicle(String plate, Date endDate, int ownerId) {
        this.plate = plate;
        this.endDate = endDate;
        this.ownerId = ownerId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
