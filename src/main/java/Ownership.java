import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Ownership {

    int id;
    String boatId;
    int ownerId;

    LocalDate ownedFrom;
    LocalDate ownedTo;

    public Ownership(ResultSet r) throws SQLException {
        //r.next();
        this.id = r.getInt(1);
        this.boatId = r.getString(2);
        this.ownerId = r.getInt(3);

        this.ownedFrom = null;
        this.ownedTo = null;

        if(r.getDate(4) != null)this.ownedFrom = r.getDate(4).toLocalDate();
        if(r.getDate(5) != null)this.ownedTo = r.getDate(5).toLocalDate();



    }

    public Ownership(String bID, int ownID, LocalDate ownedFrom, LocalDate ownedTo){
        this.boatId = bID;
        this.ownerId = ownID;
        this.ownedFrom = ownedFrom;
        this.ownedTo = ownedTo;

    }





    public String getInfoString() {


        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String ownedFromDate = dateFormat.format(ownedFrom);
        //String ownedToDate = (ownedTo != null) ? dateFormat.format(ownedTo) : "Present";
        String ownedFromDate = "NA";
        String ownedToDate = "Present";
        if(ownedFrom != null)ownedFromDate = ownedFrom.toString();
         if(ownedTo != null)ownedToDate = ownedTo.toString();


        return String.format("ID: %d\n" +
                        "Boat ID: %s\n" +
                        "Owner ID: %d\n" +
                        "Owned From: %s\n" +
                        "Owned To: %s\n",
                id, boatId, ownerId, ownedFromDate, ownedToDate);
    }



    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for boatId
    public String getBoatId() {
        return boatId;
    }

    // Getter for ownerId
    public int getOwnerId() {
        return ownerId;
    }

    // Getter for ownedFrom
    public LocalDate getOwnedFrom() {
        return ownedFrom;
    }
    // Getter for ownedTo
    public LocalDate getOwnedTo() {
        return ownedTo;
    }

}
