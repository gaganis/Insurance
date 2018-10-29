import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OwnerEnvironment extends InsuranceDB {

    public ArrayList<Owner> getOwnersList() throws Exception{
        ArrayList<Owner> ownersList = new ArrayList<>();
        try {
            ResultSet rs = getPreparedStatement("SELECT * FROM owner");
            Owner owner;
            while (rs.next()){
                owner = new Owner(rs.getInt("owner_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("phone"),
                        rs.getString("address"));
                ownersList.add(owner);
            }
        }catch (SQLException e){
        }
        return ownersList;
    }

}
