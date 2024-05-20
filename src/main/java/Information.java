public class Information {

    private String color;
    private String make;
    private String type;
    private String primColor;
    private String detailColor;
    private String tarpColor;
    private String numSails;
    private String numMasts;
    private String desc;
    private String add1;
    private String add2;

    public Information(String color, String make, String type, String primColor, String detailColor,
                String tarpColor, String numSails, String numMasts, String desc,
                String add1, String add2) {
        this.color = color;
        this.make = make;
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


    // Getters and Setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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

    public String getNumSails() {
        return numSails;
    }

    public void setNumSails(String numSails) {
        this.numSails = numSails;
    }

    public String getNumMasts() {
        return numMasts;
    }

    public void setNumMasts(String numMasts) {
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
}


