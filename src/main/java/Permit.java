import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Permit {

    int id;
    String boatId;
    int ownerId;

    private LocalDate issue;
    private LocalDate expiry;


    public Permit(int id, String boatId, int ownerId, LocalDate is, LocalDate exp){

        this.id = id;
        this.boatId = boatId;
        this.ownerId = ownerId;
        this.issue = is;
        this.expiry = exp;
    }

    public Permit(ResultSet r) throws SQLException {
        this.id = r.getInt(1);
        this.boatId = r.getString(2);
        this.ownerId = r.getInt(3);
        this.issue = r.getDate(4).toLocalDate();
        this.expiry = r.getDate(5).toLocalDate();
    }

    public boolean isExpired(){
        //Date d = new Date(); //current DATE
        LocalDate d = LocalDate.now();
        return d.isAfter(expiry);
    }

    private String formatDate(java.sql.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // Function to return formatted string
    public String getString() {
        return String.format("ID: %d\n" +
                        "Boat ID: %s\n" +
                        "Owner ID: %d\n" +
                        "Issue Date: %s\n" +
                        "Expiry Date: %s\n",
                id, boatId, ownerId, issue, expiry);
    }

    // Helper method to format date
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


}
