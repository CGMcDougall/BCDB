import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Boat {

    String ID;
    String HIN;


    List<Permit> perm;
    List<Notice> notices;

    Information info;
    SQLManager sql = new SQLManager();


    public Boat(String id, String hin){
        ID = id;
        HIN = hin;
    }

    public Boat(ResultSet r) throws SQLException {
        r.next();
        ID = r.getString(1);
        HIN = r.getString(2);
    }

    public void setInfo(ResultSet r) {
        try{
            Information i = new Information(r);
        }
        catch (Exception e){
            System.out.println("");
        }
        //this.info = new Information(r);
    }

    public void setInfo(Information i){ info = i;}


    public boolean loadPermits(ResultSet r){
        try{
            while (r.next())perm.add(new Permit(r));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean loadNotices(ResultSet r){
        try{
            while (r.next())notices.add(new Notice(r));
            return false;
        }
        catch (Exception e){
            return false;
        }
    }


}
