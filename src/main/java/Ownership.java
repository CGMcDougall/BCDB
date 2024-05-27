import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Ownership {

    int id;
    String boatId;
    int ownerId;

    java.sql.Date ownedFrom;
    java.sql.Date ownedTo;

    public Ownership(ResultSet r) throws SQLException {
        this.id = r.getInt(1);
        this.boatId = r.getString(2);
        this.ownerId = r.getInt(3);
        this.ownedFrom = r.getDate(4);
        this.ownedTo = r.getDate(5);
    }


    public String getInfoString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ownedFromDate = dateFormat.format(ownedFrom);
        String ownedToDate = (ownedTo != null) ? dateFormat.format(ownedTo) : "Present";

        return String.format("ID: %d\n" +
                        "Boat ID: %s\n" +
                        "Owner ID: %d\n" +
                        "Owned From: %s\n" +
                        "Owned To: %s\n",
                id, boatId, ownerId, ownedFromDate, ownedToDate);
    }

}
