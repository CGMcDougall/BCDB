import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Permit {

    int id;
    String boatId;
    int ownerId;

    private java.sql.Date issue;
    private java.sql.Date expiry;


    public Permit(ResultSet r) throws SQLException {
        this.id = r.getInt(1);
        this.boatId = r.getString(2);
        this.ownerId = r.getInt(3);
        this.issue = r.getDate(4);
        this.expiry = r.getDate(5);
    }

    public boolean isExpired(){
        Date d = new Date(); //current DATE
        return d.after(expiry);
    }

    public String info(){
        return String.format("Issue Date: %t, Expiry Date: %t. Is expired: %b",issue,expiry,isExpired());
    }

    // Function to return formatted string
    public String getString() {
        return String.format("ID: %d\n" +
                        "Boat ID: %s\n" +
                        "Owner ID: %d\n" +
                        "Issue Date: %s\n" +
                        "Expiry Date: %s\n",
                id, boatId, ownerId, formatDate(issue), formatDate(expiry));
    }

    // Helper method to format date
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


}
