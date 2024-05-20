import java.util.Date;

public class Observation {

    Group g = Group.VPD; //default
    Date date;
    String desc;


    public Observation(Group g, Date d, String s){
        this.g = g;
        date = d;
        desc = s;
    }

    public String info(){
        String s = String.format("Group: %s \n Date of Observation: %t \n\n %s",g.toString(),date,desc);
        return s;

    }




}
