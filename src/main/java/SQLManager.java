import local.locInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SQLManager {

    String dbUname = locInfo.dbUname;
    String dbPass = locInfo.dbPass;

    //String url = "jdbc:postgresql://localhost:5432/3005_GP";

    static Connection con = null;

    public SQLManager() {
        String dbUname = "postgres";
        String dbPass = "admin";
        String url = "jdbc:postgresql://localhost:5432/BoatCopDB";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, dbUname, dbPass);
            System.out.println("Connection status: " + con);
        } catch (Exception e) {
            System.out.println("Connection Failed : " + e);
        }
    }


    //FUNCTION LIST

    //TODO Add a method to search the DATABASE based on ID, as well as other options based on how the boat lookss


    public ResultSet getBoatWithHin(String HIN) {
        try {
            String f = String.format("SELECT * FROM boat WHERE HIN = %s", HIN);
            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;
        }
        catch (Exception e) {
            System.out.println("Problem in getBoat: " + e);
            return null;
        }
    }

    public ResultSet getOwnershipInfo(String BoatID, int OwnerID){
        try {
            String f = "SELECT * FROM ownership WHERE ";
            if(!BoatID.isEmpty()) f += String.format("boatid = '%s' AND ", BoatID);
            if(OwnerID >= 0)f += String.format("ownerid = '%s' AND ", OwnerID);

            if (f.endsWith(" AND ")) {
                f = f.substring(0, f.length() - 5);
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet getNoticeInfo(int ID, String BoatID, int OwnerID){
        try {
            String f = "SELECT * FROM notice WHERE ";
            if(ID >= 0) f += String.format("noticeid = '%s'", ID);

            else{
                if(!BoatID.isEmpty()) f += String.format("boatid = '%s' AND ", BoatID);
                if(OwnerID >= 0)f += String.format("ownerid = '%s' AND ", OwnerID);

                if (f.endsWith(" AND ")) {
                    f = f.substring(0, f.length() - 5);
                }
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }





    public ResultSet getOwner(String fn, String ln, String id, String contact, String Lic){
        try{
            String f = "SELECT * FROM owners WHERE ";
            if (!id.isEmpty()) f += String.format("ownerid = '%s' AND ", id);
            if (!fn.isEmpty()) f += String.format("LOWER(TRIM(firstname)) = LOWER('%s') AND ", fn);
            if (!ln.isEmpty()) f += String.format("LOWER(TRIM(lastname)) = LOWER('%s') AND ", ln);
            if (!contact.isEmpty()) f += String.format("LOWER(TRIM(contact)) = LOWER('%s') AND ", contact);
            if (!Lic.isEmpty()) f += String.format("LOWER(TRIM(license)) = LOWER('%s') AND ", Lic);

            // Remove the trailing " AND " if it exists
            if (f.endsWith(" AND ")) {
                f = f.substring(0, f.length() - 5);
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println("Problem in getOwner");
            return null;
        }
    }

    public ResultSet getBoat(String id){
        try{
            String f = String.format("SELECT * FROM boat WHERE boatid = '%s'",id);

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet getBoat(String model, String len, String type, String primColor, String detailColor, String tarpColor, String numSails, String numMasts, String desc, String add1, String add2){
        try{
            String f = "SELECT * FROM boatinfo WHERE ";
            if (!model.trim().isEmpty()) f += String.format("LOWER(TRIM(model)) = LOWER('%s') AND ", model.trim());
            if (!len.trim().isEmpty()) f += String.format("LOWER(TRIM(length::text)) = LOWER('%s') AND ", len.trim());
            if (!type.trim().isEmpty()) f += String.format("LOWER(TRIM(type)) = LOWER('%s') AND ", type.trim());
            if (!primColor.trim().isEmpty()) f += String.format("LOWER(TRIM(primaryColor)) = LOWER('%s') AND ", primColor.trim());
            if (!detailColor.trim().isEmpty()) f += String.format("LOWER(TRIM(detailColor)) = LOWER('%s') AND ", detailColor.trim());
            if (!tarpColor.trim().isEmpty()) f += String.format("LOWER(TRIM(tarpColor)) = LOWER('%s') AND ", tarpColor.trim());
            if (!numSails.trim().isEmpty()) f += String.format("LOWER(TRIM(numSails::text)) = LOWER('%s') AND ", numSails.trim());
            if (!numMasts.trim().isEmpty()) f += String.format("LOWER(TRIM(numMasts::text)) = LOWER('%s') AND ", numMasts.trim());
            if (!desc.trim().isEmpty()) f += String.format("LOWER(TRIM(description)) = LOWER('%s') AND ", desc.trim());
            if (!add1.trim().isEmpty()) f += String.format("LOWER(TRIM(additional1)) = LOWER('%s') AND ", add1.trim());
            if (!add2.trim().isEmpty()) f += String.format("LOWER(TRIM(additional2)) = LOWER('%s') AND ", add2.trim());

            // Remove the trailing " AND " if it exists
            if (f.endsWith(" AND ")) {
                f = f.substring(0, f.length() - 5);
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            //r.next();
            return r;
        }
        catch(Exception e){
            System.out.println("Problem in getBoat: " + e);
            return null;
        }
    }

//    public Boat getBoat(String id){
//        try{
//            String f = String.format("SELECT * FROM boat WHERE boatid = '%s' LIMIT 1", id);
//            Statement s = con.createStatement();
//            s.executeQuery(f);
//            ResultSet r = s.getResultSet();
//            r.next();
//            Boat b = new Boat(id, r.getString(2));
//            b.setInfo(getBoatInfo(id));
//
//            return null;
//        }
//        catch (Exception e){
//            System.out.println("Error in getBoat(Str id): "+e);
//            return null;
//        }
//    }

    private ResultSet getBoatInfo(String id){
        try {
            String f = String.format("SELECT * FROM boat WHERE boatid = '%s' LIMIT 1", id);
            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            return r;
        }
        catch (Exception e){
            System.out.println("Error in getBoatInfo (priv func): " +e);
            return null;
        }
    }

    public void printResultSet(ResultSet r) {
        try {
            int colCount = r.getMetaData().getColumnCount();
            System.out.println(colCount);
            while (r.next()) {
                //System.out.println("Got Here");
                String row = "";
                for (int i = 1; i < colCount; ++i) row += r.getString(i) + " , ";
                if(row.endsWith(" , ")) row = row.substring(0, row.length() - 3);
                System.out.println(row);
            }

        } catch (Exception e) {
            System.out.println("In Print Result Set : " + e);
        }


    }

}
