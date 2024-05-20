import java.util.Date;

//direction Notice
public class DN {
    int ID;
    String dn;
    Date date;

    public DN(int id, String dn, Date d){
        ID = id;
        this.dn = dn;
        this.date = d;

    }

    public String info(){
        String s = String.format("Direction notice: %s \n\n Date of DN: %t",dn,date);
        return s;
    }




}
