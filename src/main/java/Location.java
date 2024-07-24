import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Location {

    int id;
    String boatId;
    String location;
    LocalDate arrDate;
    LocalDate deptDate;

    Group g;


    public Location(ResultSet r) throws  SQLException{
        r.next();
        this.id = r.getInt(1);
        this.boatId = r.getString(2);
        this.location = r.getString(3);
        this.arrDate = r.getDate(4).toLocalDate();
        this.deptDate = r.getDate(5).toLocalDate();
        this.g = Group.valueOf(r.getString(6));

    }

    public Location(String bID, String loc, LocalDate arr, LocalDate dept, Group g){
        this.boatId = bID;
        this.location = loc;
        this.arrDate = arr;
        this.deptDate = dept;
        this.g = g;

    }


    @Override
    public String toString() {
        return String.format("Location{id=%d, boatId='%s', location='%s', arrDate=%s, deptDate=%s, g=%s}",
                id, boatId, location, arrDate, deptDate, g);
    }



    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for boatId
    public String getBoatId() {
        return boatId;
    }

    public void setBoatId(String boatId) {
        this.boatId = boatId;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for arrDate
    public LocalDate getArrDate() {
        return arrDate;
    }

    public void setArrDate(LocalDate arrDate) {
        this.arrDate = arrDate;
    }

    // Getter and Setter for deptDate
    public LocalDate getDeptDate() {
        return deptDate;
    }

    public void setDeptDate(LocalDate deptDate) {
        this.deptDate = deptDate;
    }

    // Getter and Setter for g (Group)
    public Group getG() {
        return g;
    }

    public void setG(Group g) {
        this.g = g;
    }

}
