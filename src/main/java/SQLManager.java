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


    public ResultSet getBoat(String HIN) {
        try {
            String f = String.format("SELECT * FROM boat WHERE HIN = %s", HIN);
            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            r.next();
            return r;
        }
        catch (Exception e) {
            System.out.println("Problem in getBoat: " + e);
            return null;
        }
    }

    public ResultSet getBoat(String model, String len, String type, String primColor, String detailColor, String tarpColor, String numSails, String numMasts, String desc, String add1, String add2){
        try{
            String f = "SELECT * FROM boatinfo WHERE ";
            if (!model.isEmpty()) f += String.format("model = '%s' AND ", model);
            if (!len.isEmpty()) f += String.format("length = '%s' AND ", len);
            if (!type.isEmpty()) f += String.format("type = '%s' AND ", type);
            if (!primColor.isEmpty()) f += String.format("primaryColor = '%s' AND ", primColor);
            if (!detailColor.isEmpty()) f += String.format("detailColor = '%s' AND ", detailColor);
            if (!tarpColor.isEmpty()) f += String.format("tarpColor = '%s' AND ", tarpColor);
            if (!numSails.isEmpty()) f += String.format("numSails = '%s' AND ", numSails);
            if (!numMasts.isEmpty()) f += String.format("numMasts = '%s' AND ", numMasts);
            if (!desc.isEmpty()) f += String.format("description = '%s' AND ", desc);
            if (!add1.isEmpty()) f += String.format("additional1 = '%s' AND ", add1);
            if (!add2.isEmpty()) f += String.format("additional2 = '%s' AND ", add2);

            // Remove the trailing " AND " if it exists
            if (f.endsWith(" AND ")) {
                f = f.substring(0, f.length() - 5);
            }

            Statement s = con.createStatement();
            s.executeQuery(f);
            ResultSet r = s.getResultSet();
            r.next();
            return r;
        }
        catch(Exception e){
            System.out.println("Problem in getBoat: " + e);
            return null;
        }
    }

}
