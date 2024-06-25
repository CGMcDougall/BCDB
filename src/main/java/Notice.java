import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Notice {
    int id;
    String boatId;
    int ownerId;
    Group g;
    //NoticeType

    String incidentNumber;
    String type;
    java.sql.Date dateOfIssue;


    public Notice(ResultSet r) throws SQLException {
        r.next();
        this.id = r.getInt(1);
        this.boatId = r.getString(2);
        this.ownerId = r.getInt(3);
        this.g = Group.valueOf(r.getString(4));
        this.type = r.getString(5);
        this.incidentNumber = r.getString(6);
        this.dateOfIssue = r.getDate(7);
    }


    public String getString() {
        return String.format("Notice ID: %d\n" +
                        "Boat ID: %s\n" +
                        "Owner ID: %d\n" +
                        "Group: %s\n" +
                        "Type: %s\n" +
                        "Incident Number: %s\n" +
                        "Date of Issue: %s\n",
                id, boatId, ownerId, g,type, incidentNumber, formatDate(dateOfIssue));
    }

    // Helper method to format date
    private String formatDate(java.sql.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}
