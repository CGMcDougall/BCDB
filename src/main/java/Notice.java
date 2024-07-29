import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Notice {
    int id;
    String boatId;
    int ownerId;
    Group g;
    //NoticeType

    String incidentNumber;
    String type;
    LocalDate dateOfIssue;



    public Notice(String boatId, int ownerId, Group g, String type, String incidentNumber, LocalDate d){
        this.boatId = boatId;
        this.ownerId = ownerId;
        this.g = g;
        this.type = type;
        this.incidentNumber = incidentNumber;
        this.dateOfIssue = d;
    }

    public Notice(ResultSet r) throws SQLException {
        //r.next();
        this.id = r.getInt(1);
        this.boatId = r.getString(2);
        this.ownerId = r.getInt(3);
        this.g = Group.valueOf(r.getString(4));
        this.type = r.getString(5);
        this.incidentNumber = r.getString(6);
        this.dateOfIssue = r.getDate(7).toLocalDate();
    }


    public String getString() {
        return String.format("Notice ID: %d\n" +
                        "Boat ID: %s\n" +
                        "Owner ID: %d\n" +
                        "Group: %s\n" +
                        "Type: %s\n" +
                        "Incident Number: %s\n" +
                        "Date of Issue: %s\n",
                id, boatId, ownerId, g,type, incidentNumber, dateOfIssue.toString());
    }

    // Helper method to format date
    private String formatDate(java.sql.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


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

    // Getter for g
    public Group getGroup() {
        return g;
    }

    // Getter for incidentNumber
    public String getIncidentNumber() {
        return incidentNumber;
    }

    // Getter for type
    public String getType() {
        return type;
    }

    // Getter for dateOfIssue
    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

}
