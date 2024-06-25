import java.sql.ResultSet;
import java.sql.SQLException;

public class Information {

    private String id;
    private String model;
    private float len;
    private String type;
    private String primColor;
    private String detailColor;
    private String tarpColor;
    private int numSails;
    private int numMasts;
    private String desc;
    private String add1;
    private String add2;

    public Information(String model, float len, String type, String primColor, String detailColor,
                String tarpColor, int numSails, int numMasts, String desc,
                String add1, String add2) {
        this.model = model;
        this.len = len;
        this.type = type;
        this.primColor = primColor;
        this.detailColor = detailColor;
        this.tarpColor = tarpColor;
        this.numSails = numSails;
        this.numMasts = numMasts;
        this.desc = desc;
        this.add1 = add1;
        this.add2 = add2;
    }
    public Information(ResultSet r) throws SQLException {
        //r.next();
        this.id = r.getString(1);
        this.model = r.getString(2);
        this.len = r.getFloat(3);
        this.type = r.getString(4);
        this.primColor = r.getString(5);
        this.detailColor = r.getString(6);
        this.tarpColor = r.getString(7);
        this.numSails = r.getInt(8);
        this.numMasts = r.getInt(9);
        this.desc = r.getString(10);
        //this.add1 = r.getString(11);
        //this.add2 = r.getString(12);

    }



    // Getters and Setters


    public String getId() {
        return id;
    }

    public String getmodel() {
        return model;
    }

    public void setmodel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimColor() {
        return primColor;
    }

    public void setPrimColor(String primColor) {
        this.primColor = primColor;
    }

    public String getDetailColor() {
        return detailColor;
    }

    public void setDetailColor(String detailColor) {
        this.detailColor = detailColor;
    }

    public String getTarpColor() {
        return tarpColor;
    }

    public void setTarpColor(String tarpColor) {
        this.tarpColor = tarpColor;
    }

    public int getNumSails() {
        return numSails;
    }

    public void setNumSails(int numSails) {
        this.numSails = numSails;
    }

    public int getNumMasts() {
        return numMasts;
    }

    public void setNumMasts(int numMasts) {
        this.numMasts = numMasts;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String printBoatDetails() {
        String details = "Model: " + this.model + "\n" +
                "Length: " + this.len + "\n" +
                "Type: " + this.type + "\n" +
                "Primary Color: " + this.primColor + "\n" +
                "Detail Color: " + this.detailColor + "\n" +
                "Tarp Color: " + this.tarpColor + "\n" +
                "Number of Sails: " + this.numSails + "\n" +
                "Number of Masts: " + this.numMasts + "\n" +
                "Description: " + this.desc + "\n" +
                "Additional Info 1: " + this.add1 + "\n" +
                "Additional Info 2: " + this.add2;
        return details;

    }

}


