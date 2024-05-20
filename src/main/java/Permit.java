import java.util.Date;

public class Permit {

    private Date issue;
    private Date expiry;


    public Permit(Date is, Date ex){
        issue = is;
        expiry = ex;
    }

    public boolean isExpired(){
        Date d = new Date(); //current DATE
        return d.after(expiry);
    }

    public String info(){
        return String.format("Issue Date: %t, Expiry Date: %t. Is expired: %b",issue,expiry,isExpired());
    }



}
